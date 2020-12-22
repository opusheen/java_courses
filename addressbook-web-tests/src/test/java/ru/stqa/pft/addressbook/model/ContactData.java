package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name ="addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private  int  id  = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private  String firstname;
    @Expose
    @Column(name = "lastname")
    private  String lastname;
    @Expose
    @Type(type="text")
    private  String address;
    @Expose
    @Type(type="text")
    private  String email;
    @Expose
    @Type(type="text")
    private  String email2;
    @Expose
    @Type(type="text")
    private  String email3;
    @Expose
    @Column(name = "mobile")
    @Type(type="text")
    private  String mobilephonenumber;
    @Expose
    @Column(name = "home")
    @Type(type="text")
    private  String homephone;
    @Expose
    @Column(name = "work")
    @Type(type="text")
    private  String workphone;

    @Transient
    private  String allPhones;
    @Transient
    private  String allEmails;
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="address_in_groups", joinColumns = @JoinColumn(name="id"), inverseJoinColumns = @JoinColumn (name="group_id") )
    private Set<GroupData> groups = new HashSet<GroupData>();




    // Here are setters for ContactData
    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    public ContactData withFirstName(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
    public ContactData withAddress (String address) {
        this.address = address;
        return this;
    }


    public ContactData withMobilephonenumber(String mobilephonenumber) {
        this.mobilephonenumber = mobilephonenumber;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }
    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }
    public ContactData withHomephone (String homephone) {
        this.homephone = homephone;
        return this;
    }
    public ContactData withWorkphone (String workphone) {
        this.workphone = workphone;
        return this;
    }
    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
    public ContactData withallEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }


    public ContactData withPhoto(File photo) {
       this.photo = photo.getPath();
       return this;
    }
 ///  Getters for ContactData

    public int getId() { return id; }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {return address;}
    //множество превращается в объект типа Groups, при этом создается копия
   public Groups getGroups() {
        return new Groups(groups);
   }
    public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
   }


    public String getMobilephonenumber() {
        return mobilephonenumber;
    }
    public String getHomephone() {return homephone; }
    public String getWorkphone() { return workphone; }
    public String getEmail() { return email;}
    public String getEmail2() {
        return email2;
    }
    public String getEmail3() {
        return email3;
    }
    public String getAllPhones() {
        return allPhones;
    }
    public String getallEmails() {
        return allEmails;
    }
    public File getPhoto() {return new File(photo);
   }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return
                id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(address, that.address)&&
               Objects.equals(email, that.email) &&
               Objects.equals(email2, that.email2) &&
                Objects.equals(email3, that.email3) &&
               Objects.equals(mobilephonenumber, that.mobilephonenumber) &&
               Objects.equals(homephone, that.homephone) &&
                Objects.equals(workphone, that.workphone) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, address, email, email2, email3, mobilephonenumber, homephone, workphone);
    }
}
