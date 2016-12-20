package app.com.example.android.hometask2.getContacts;


import android.content.ContentProviderOperation;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.model.Contact;
import app.com.example.android.hometask2.util.ContactsDataLoader;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Contact>>{

    private static final int LOADER_ID = 13;
    private static final int READ_CONTACTS_REQUEST = 1;
    private RecyclerView recyclerView;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_get_contacts, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.contact_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        if (ActivityCompat.checkSelfPermission(getActivity(), READ_CONTACTS) == PERMISSION_GRANTED) {
            getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), READ_CONTACTS)) {
                showExplanationDialog();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{READ_CONTACTS}, READ_CONTACTS_REQUEST);
            }
        }

        Button button = (Button) rootView.findViewById(R.id.add_contact_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.my_dialog_message, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.create_new_contact)
                        .setView(dialogView)
                        .setPositiveButton(R.string.create_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText contactName = (EditText) dialogView.findViewById(R.id.edit_contact_name);
                                EditText contactPhoneNumber = (EditText) dialogView.findViewById(R.id.edit_contact_phone_number);
                                String name = contactName.getText().toString();
                                String phoneNumber = contactPhoneNumber.getText().toString();
                                if (!name.equals("") && !phoneNumber.equals("")) {
                                    addContact(name, phoneNumber);
                                    restartLoader();
                                }
                            }
                        })
                        .setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                dialog.show();
            }
        });
        return rootView;
    }

    @Override
    public Loader<List<Contact>> onCreateLoader(int id, Bundle args) {
        return new ContactsDataLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Contact>> loader, List<Contact> data) {
        updateUI(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Contact>> loader) {
        updateUI(null);
    }

    public void updateUI(List<Contact> contacts) {
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(new ContactsAdapter(contacts));
        } else {
            ContactsAdapter adapter = (ContactsAdapter) recyclerView.getAdapter();
            adapter.updateAdapter(contacts);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == READ_CONTACTS_REQUEST) {
            if (grantResults[0] == PERMISSION_GRANTED) {
                getActivity().getSupportLoaderManager().initLoader(1, null, this).forceLoad();
            } else {
                showExplanationDialog();
            }
        }
    }

    private void showExplanationDialog() {
        new AlertDialog.Builder(getActivity())
                .setMessage("Access to READ contacts is dismiss")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{READ_CONTACTS}, READ_CONTACTS_REQUEST);
                    }
                })
                .setNegativeButton("Go to settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        openPermissionSettings();
                    }
                })
                .create()
                .show();
    }

    private void openPermissionSettings() {
        final Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(intent);
    }

    private void addContact(String name, String number){
        ArrayList<ContentProviderOperation> operations = new ArrayList<>();
        int rawContactInsertIndex = operations.size();

        operations.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME,null )
                .build());
        operations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                .build());
        operations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                .build());
        try {
            getContext().getContentResolver().applyBatch(ContactsContract.AUTHORITY, operations);
        } catch (RemoteException | OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    private void restartLoader() {
        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
    }
}
