package app.com.example.android.hometask2.model;

/**
 * Created by Влад on 25.11.2016.
 */
public class Contact {
    private String contactName;
    private String contactNumber;

    public String getContactName() {
        return contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "Name = '" + contactName + '\'' +
                ", Number = '" + contactNumber + '\'' +
                '}';
    }
}
