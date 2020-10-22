package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;

import java.util.List;

public class ContactModificationTest extends TestBase{
   @Test
   public void testContactModification(){
      if ( ! app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "+79112223344", "ivan@mail.ru","test1"), true);
         app.getNavigationHelper().goToHomePage();
      }
      List<ContactData> before = app.getContactHelper().getContactList();
      ContactData contact = new ContactData (before.get(before.size() - 1).getId(),"Ivan", "Ivanov", "+79112223344", "ivan@mail.ru","test1");
      app.getContactHelper().editContact(before.size() - 1);
      app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "+79", "ivan@mail.ru", null), false);
      app.getContactHelper().submitContactModification();
      app.getNavigationHelper().goToHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size());

      before.remove(before.size() - 1);
      before.add(contact);
      Comparator<? super ContactData> byId = (c1,c2)-> Integer.compare(c1.getId(), c2.getId()) ;
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before,after);

   }
}
