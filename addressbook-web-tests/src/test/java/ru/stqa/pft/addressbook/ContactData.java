package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String mobilephonenumber;
    private final String email;

    public ContactData(String firstname, String lastname, String mobilephonenumber, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobilephonenumber = mobilephonenumber;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMobilephonenumber() {
        return mobilephonenumber;
    }

    public String getEmail() {
        return email;
    }
}
