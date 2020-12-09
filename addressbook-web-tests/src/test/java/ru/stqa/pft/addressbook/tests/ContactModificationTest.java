package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase{

   @BeforeMethod

   public void ensurePreconditions() {
      if (  app.contact().list().size() == 0) {
         app.contact().create(new ContactData().withFirstName("Ivan").withLastname("Ivanov").withMobilephonenumber("0000000").withEmail("ivan@mail.ru").withGroup("test1"), true);
         app.goTo().homePage();
      }
   }
   @Test
   public void testContactModification(){
      Contacts before = app.contact().all();
      ContactData modifiedContact = before.iterator().next();
      ContactData contact =  new ContactData()
              .withId(modifiedContact.getId()).withFirstName("Ivan").withLastname("Ivanov").withMobilephonenumber("0000000").withEmail("ivan@mail.ru").withGroup("test1");
      app.contact().modify(contact);
      app.goTo().homePage();
      Contacts after = app.contact().all();
      assertEquals(after.size(), before.size());
      assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
   }


}
