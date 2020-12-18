package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddContactToGroup extends TestBase{

    @BeforeMethod

    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Ivan").withLastname("Ivanov").withMobilephonenumber("0000000").withEmail("ivan@mail.ru").withPhoto(new File("addressbook-web-tests/src/test/resources/husk.png")), true);
        } if (app.db().groups().size() == 0){
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }
    @Test
    public void testAddContactToGroup() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData contact = contacts.iterator().next();
        GroupData group = groups.iterator().next();
        Groups groupsInContact = contact.getGroups();
        app.goTo().homePage();
        app.contact().selectContactById(contact.getId());
       if  (groups == groupsInContact) {
            app.group().create(new GroupData().withName("test100"));
            app.goTo().homePage();
            app.contact().selectContactById(contact.getId());
        } else {
           app.contact().addToGroup(group);
           app.goTo().homePage();
       }
        assertThat(app.db().contacts(contact.getId()).getGroups(), equalTo(groupsInContact.withAdded(group)));

    }

}
