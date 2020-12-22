package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.User;

import static org.openqa.selenium.By.cssSelector;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(cssSelector("input[value='Signup']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(cssSelector("button[type = 'submit']"));
    }

    public void login(User user) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), user.getUsername());
        click(cssSelector("input[value='Login']"));
        type(By.name("password"), user.getPassword());
        click(cssSelector("input[value='Login']"));
    }



}