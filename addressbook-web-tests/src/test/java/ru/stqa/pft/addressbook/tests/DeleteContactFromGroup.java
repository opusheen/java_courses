package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

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
    public void testDeleteContactfromGroup() {
        Groups groupsOverall = app.db().groups();
        Contacts contatctsOverall = app.db().contacts();
        ContactData contactSelected = contatctsOverall.iterator().next();
        GroupData groupSelected = groupsOverall.iterator().next();
        Groups groupsInContact = contactSelected.getGroups();
        int s = groupsInContact.size();
        Contacts contactsBeforeRemoveFromGroup = app.db().groups(groupSelected.getId()).getContacts();
        app.goTo().homePage();
      if (s == 0) {
            app.goTo().homePage();
            app.contact().selectContactById(contactSelected.getId());
            app.contact().addToGroup(groupSelected);
            app.goTo().homePage();
        }
        app.contact().selectGroup(groupSelected);
        app.contact().selectContactById(contactSelected.getId());
        app.contact().deleteFromGroup(groupSelected);
        app.goTo().homePage();
        Contacts contactsAterRemoveFromGroup = app.db().groups(groupSelected.getId()).getContacts();
        assertThat(contactsAterRemoveFromGroup, equalTo(contactsBeforeRemoveFromGroup.without(contactSelected)));
    }
}
