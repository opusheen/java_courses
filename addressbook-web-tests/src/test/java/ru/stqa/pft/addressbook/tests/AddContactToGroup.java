package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.concurrent.TimeUnit;

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
    public void testAddContactToGroup() throws InterruptedException {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData contact = contacts.iterator().next();
        GroupData pairGroup;
        Groups groupsInContact = contact.getGroups();
        app.goTo().homePage();
       if  (groups.size() == groupsInContact.size()) {
           app.goTo().GroupPage();
           app.group().create(new GroupData().withName("test100"));
           Groups groupNew   = app.db().groups();
           groupNew.removeAll(groups);
           pairGroup = groupNew.iterator().next();
        } else {
           groups.removeAll(groupsInContact);
           pairGroup = groups.iterator().next();
       }
       app.goTo().homePage();
       app.contact().addToGroup(pairGroup,contact);
       app.goTo().homePage();
       TimeUnit.SECONDS.sleep(10);
       assertThat(app.db().contacts(contact.getId()).getGroups(), equalTo(groupsInContact.withAdded(pairGroup)));

    }

}
