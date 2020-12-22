package ru.stqa.pft.mantis.appmanager;

import static org.openqa.selenium.By.cssSelector;

public class UserManagerHelper extends HelperBase {


    public UserManagerHelper(ApplicationManager app) {
        super(app);
    }
    public void manageUsers() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }
    public void selectUser(int id) {
    click(cssSelector("a[href*='manage_user_edit_page.php?user_id='"+ id + "']")); }

   public void resetPassword (int id) {
    click(cssSelector("[href$='user_id=" + id + "']"));
    click(cssSelector("input[value='Reset Password']"));

    }
}
