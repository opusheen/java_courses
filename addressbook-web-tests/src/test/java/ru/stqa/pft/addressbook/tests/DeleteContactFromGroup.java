package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactFromGroup extends TestBase{
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0){
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("test1"));

        }
            if (app.db().contacts().size() == 0) {
                app.contact().create(new ContactData().withFirstName("Ivan").withLastname("Ivanov").withMobilephonenumber("0000000").withEmail("ivan@mail.ru").withPhoto(new File("addressbook-web-tests/src/test/resources/husk.png")), true);
        }
    }
     @Test
    public void testDeleteContactfromGroup() throws InterruptedException {
        GroupData group = app.db().groups().iterator().next();
        boolean GroupisNotAvailible = true;

         for (GroupData groups : app.db().groups()) {
             if (groups.getContacts().size() > 0) {
                 group = groups;
                 GroupisNotAvailible = false;
                 break;
             }
         }

         if (GroupisNotAvailible) {
             ContactData contact = app.db().contacts().iterator().next();
             app.goTo().homePage();
             app.contact().addToGroup(group,contact);
             app.goTo().homePage();
             assertThat(app.db().groups(group.getId()).getContacts(), equalTo(group.getContacts().withAdded(contact)));
         }

         ContactData contact = app.db().groups(group.getId()).getContacts().iterator().next();
         Contacts contactsBeforeRemoveFromGroup = app.db().groups(group.getId()).getContacts();
         app.goTo().homePage();
         app.contact().deleteFromGroup(group, contact);
         app.goTo().homePage();
         TimeUnit.SECONDS.sleep(10);
         Contacts afterDeleteFromGroup = app.db().groups(group.getId()).getContacts();
         assertThat(afterDeleteFromGroup, equalTo(contactsBeforeRemoveFromGroup.without(contact)));
    }
}
//        int s = groupsInContact.size();
//      if (s == 0) {
//           app.goTo().homePage();
//            app.contact().selectContactById(contactSelected.getId());
//        app.contact().addToGroup(group, contact);
//            app.goTo().homePage();