package app.com.example.android.hometask2.getContacts;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.model.Contact;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GetContactsFragment extends Fragment {
    private static Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
    private static String _ID = ContactsContract.Contacts._ID;
    private static String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    private static String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
    private static String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
    private static Uri PHONE_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private static String PHONE_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
    private Cursor cursor;
    private String name;
    private String number;
    private GetContactsAdapter adapter;

    public GetContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_get_contacts, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.contact_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new GetContactsAdapter(getContacts());
        recyclerView.setAdapter(adapter);

        Button button = (Button) rootView.findViewById(R.id.add_contact_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }

    public ArrayList<Contact> getContacts() {
        ArrayList<Contact> listContacts = new ArrayList<>();
        ContentResolver contentResolver = getContext().getContentResolver();
        cursor = contentResolver.query(CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String contactId = cursor.getString(cursor.getColumnIndex(_ID));
            name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
            int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
            if (hasPhoneNumber > 0) {
                Cursor phoneCursor = contentResolver.query(
                        PHONE_CONTENT_URI,
                        null,
                        PHONE_CONTACT_ID + " = ?",
                        new String[] {contactId},
                        null);
                while (phoneCursor.moveToNext()) {
                    number = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                }
                phoneCursor.close();
            }
            listContacts.add(new Contact(name, number));
        }
        cursor.close();
        return listContacts;
    }

    public void insertContact() {

    }
}
