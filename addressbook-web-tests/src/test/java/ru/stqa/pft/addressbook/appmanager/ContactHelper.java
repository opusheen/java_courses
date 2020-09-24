package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm (ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobilephonenumber());
        type(By.name("email"), contactData.getEmail());

        if (isElementPresent(By.name("new_group"))) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }
    }



    public void submitContactCreation () {

        click(By.name("submit"));
    }


    public void selectContact() {

            click(By.name("selected[]"));

    }

    public void deleteContact(){

        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void editContact() {
        click(By.xpath("//img[@alt='Edit']")) ;
                 }



    public void acceptDeletionAllert(){

        wd.switchTo().alert().accept();
                }

}
