package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void create(ContactData contact, boolean creation) {
        addNewContactPage();
        fillContactForm(contact, true);
        submitContactCreation();
        contactsCache = null;
    }


    public void addNewContactPage() {
        click(By.linkText("add new"));
    }


    public void fillContactForm ( ContactData contactData, boolean creation ) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobilephonenumber());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("mobile"), contactData.getMobilephonenumber());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("work"), contactData.getWorkphone());
        attach(By.name("photo"),contactData.getPhoto());
        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);

                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }



    public void submitContactCreation () {
        click(By.name("submit"));
    }


    public void selectContact(int index) {

        wd.findElements(By.name("selected[]")).get(index).click();
    }
    public void selectContactById(int id) {

        wd.findElement(By.cssSelector("input[value= '" + id + "']")).click();
    }

    public void deleteContact() {

        click(By.xpath("//input[@value='Delete']"));
        contactsCache = null;
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void editContact(int id) {
            wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }


    public void acceptDeletionAllert(){

        wd.switchTo().alert().accept();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
    public void modify( ContactData contact) {
        editContact(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactsCache = null;
    }

     public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element:elements) {
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstName(firstname)
                    .withLastname(lastname));
        }
        return contacts;
    }
 public Contacts all() {
        if (contactsCache != null) {
                return new Contacts(contactsCache);}
        contactsCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element:elements) {
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactsCache.add(new ContactData().withId(id).withFirstName(firstname).withLastname(lastname).withAllPhones(allPhones).withallEmails(allEmails).withAddress(address));
        }
        return new Contacts(contactsCache) ;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        editContact(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilephonenumber = wd.findElement(By.name("mobile")).getAttribute("value");
        String workphone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastname(lastname).withAddress(address)
                .withHomephone(homephone).withMobilephonenumber(mobilephonenumber).withWorkphone(workphone)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }


//delete contacts
    public void delete(int index) {
        selectContact(index);
        deleteContact();
        acceptDeletionAllert();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        acceptDeletionAllert();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    Contacts contactsCache = null;

    public void addToGroup(GroupData group){

        new Select(wd.findElement(By.cssSelector("select[name='to_group']"))).selectByVisibleText(group.getName());
        click(By.name("add"));

    }


    public void deleteFromGroup(GroupData group) {
        click(By.name("remove"));
    }
    public void selectGroup(GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    }

}
