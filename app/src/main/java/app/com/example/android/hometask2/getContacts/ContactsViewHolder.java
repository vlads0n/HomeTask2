package app.com.example.android.hometask2.getContacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.model.Contact;

public class ContactsViewHolder extends RecyclerView.ViewHolder {
    private TextView contactName;
    private TextView contactNumber;

    static ContactsViewHolder create(LayoutInflater inflater, ViewGroup parent) {
        return new ContactsViewHolder(inflater.inflate(R.layout.contacts_list_item, parent, false));
    }

    public ContactsViewHolder(View itemView) {
        super(itemView);
        contactName = (TextView) itemView.findViewById(R.id.contact_name);
        contactNumber = (TextView) itemView.findViewById(R.id.contact_tel_number);
    }

    void bind(Contact contact) {
        contactName.setText(contact.getContactName());
        contactNumber.setText(contact.getContactNumber());
    }
}