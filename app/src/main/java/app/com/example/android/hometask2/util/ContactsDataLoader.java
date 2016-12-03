package app.com.example.android.hometask2.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.AsyncTaskLoader;
import app.com.example.android.hometask2.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 30.11.2016.
 */
public class ContactsDataLoader extends AsyncTaskLoader<List<Contact>> {
    private static Uri CONTACTS_URI = ContactsContract.Contacts.CONTENT_URI;
    private static final String[] CONTACTS_PROJECTION = {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
    };
    private static final String CONTACTS_SORT = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " ASC";
    private static final String CONTACTS_SELECTION = ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER + " = ?";
    private static final String[] SELECTION_ARGS = {"1"};
    private static final String[] PHONES_PROJECTION = {
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };
    private static final String PHONE_SELECTION = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";

    public ContactsDataLoader(Context context) {
        super(context);
    }

    @Override
    public List<Contact> loadInBackground() {
        Context context = getContext();
        List<Contact> contacts = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(
                CONTACTS_URI,
                CONTACTS_PROJECTION,
                CONTACTS_SELECTION,
                SELECTION_ARGS,
                CONTACTS_SORT,
                null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                long contactId = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor cursorPhone = resolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        PHONES_PROJECTION,
                        PHONE_SELECTION,
                        new String[]{String.valueOf(contactId)},
                        null
                );
                String phone = null;
                if (cursorPhone != null) {
                    if (cursorPhone.moveToFirst()) {
                        phone = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    cursorPhone.close();
                }
                Contact contact = new Contact();
                contact.setContactName(name);
                contact.setContactNumber(phone);
                contacts.add(contact);
            }
            cursor.close();
        }
        return contacts;
    }
}
