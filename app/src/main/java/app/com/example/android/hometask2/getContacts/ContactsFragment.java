package app.com.example.android.hometask2.getContacts;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.model.Contact;
import app.com.example.android.hometask2.util.ContactsDataLoader;

import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Contact>>{

    private static final int READ_CONTACTS_REQUEST = 1;
    private ContactsAdapter adapter;
    private RecyclerView recyclerView;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_get_contacts, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.contact_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        if (ActivityCompat.checkSelfPermission(getActivity(), READ_CONTACTS) == PERMISSION_GRANTED) {
            getActivity().getSupportLoaderManager().initLoader(1, null, this).forceLoad();
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
}
