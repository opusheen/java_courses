package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

    private  int  id;
    private final String firstname;
    private final String lastname;
    private final String mobilephonenumber;
    private final String email;
    private String group;

    public ContactData( String firstname, String lastname, String mobilephonenumber, String email, String group ) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobilephonenumber = mobilephonenumber;
        this.email = email;
        this.group = group;
    }

    public ContactData(int id, String firstname, String lastname, String mobilephonenumber, String email, String group ) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobilephonenumber = mobilephonenumber;
        this.email = email;
        this.group = group;
    }


    public int getId() { return id; }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public String getMobilephonenumber() {
        return mobilephonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }

    public void setId(int id) {
        this.id = id;
    }

}
