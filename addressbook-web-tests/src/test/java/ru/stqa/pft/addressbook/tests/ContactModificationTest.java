package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase{

   @BeforeMethod

   public void ensurePreconditions() {
      if (app.db().contacts().size() == 0) {
         app.contact().create(new ContactData().withFirstName("Ivan").withLastname("Ivanov").withMobilephonenumber("0000000").withEmail("ivan@mail.ru").withPhoto(new File("addressbook-web-tests/src/test/resources/husk.png")), true);
         app.goTo().homePage();
      }
   }
   @Test
   public void testContactModification() throws InterruptedException {
      Contacts before = app.db().contacts();
      ContactData modifiedContact = before.iterator().next();
      File photo = new File("src/test/resources/husk.png");
      ContactData contact =  new ContactData()
              .withId(modifiedContact.getId()).withFirstName("Ivan").withLastname("Ivanov").withMobilephonenumber("0000000").withEmail("ivan@mail.ru").withPhoto(new File("addressbook-web-tests/src/test/resources/husk.png"));
      app.goTo().homePage();
      app.contact().modify(contact);
      app.goTo().homePage();
      TimeUnit.SECONDS.sleep(20);
      Contacts after = app.db().contacts();
      assertThat(app.contact().count(), equalTo(before.size()));
      assertThat(after, CoreMatchers.equalTo(((Contacts) before).without(modifiedContact).withAdded(contact)));
      verifyContactListInUI();
   }


}
