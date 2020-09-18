package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{
   @Test
    public void testContactModification(){
       app.getContactHelper().editContact();
       app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "+79", "ivan@mail.ru"));
       app.getContactHelper().submitContactModification();
       app.getNavigationHelper().goToHomePage();


   }



}
