package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import org.openqa.selenium.*;

import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{


    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToNewContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "+79112223344", "ivan@mail.ru","test1"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().goToHomePage();

    }


}
