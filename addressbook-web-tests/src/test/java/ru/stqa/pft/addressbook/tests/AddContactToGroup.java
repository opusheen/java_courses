package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddContactToGroup extends TestBase{
    @Test
    public void testAddContactToGroup() {
        Groups beforeG = app.db().groups();
        Contacts beforeC = app.db().contacts();
        ContactData contact = beforeC.iterator().next();
        GroupData group = beforeG.iterator().next();
        Groups groupsInContact = contact.getGroups();
        app.goTo().homePage();
        app.contact().selectContactById(contact.getId());
        app.contact().addToGroup(group);
        app.goTo().homePage();
        assertThat(app.db().contacts(contact.getId()).getGroups(), equalTo(groupsInContact.withAdded(group)));

    }
}
