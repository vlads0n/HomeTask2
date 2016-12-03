package app.com.example.android.hometask2.getContacts;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import app.com.example.android.hometask2.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 25.11.2016.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {
    private final List<Contact> contacts = new ArrayList<>();
    private LayoutInflater inflater;


    public ContactsAdapter(@Nullable List<Contact> contactList) {
        updateAdapter(contactList);
    }

    public void updateAdapter(@Nullable List<Contact> contactList) {
        contacts.clear();
        if (contactList != null) {
            contacts.addAll(contactList);
        }
        notifyDataSetChanged();
    }

    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return ContactsViewHolder.create(inflater, parent);
    }


    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        holder.bind(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


}
