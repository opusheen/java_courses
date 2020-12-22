package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends  TestBase  {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }



@Test
    public void testChangePassword() throws IOException, MessagingException {
    app.registration().login(new User().withUsername("administrator").withPassword("root1"));
        User userToChangePassword = app.db().users().iterator().next();
        app.user().manageUsers();
        app.user().resetPassword(userToChangePassword.getId());
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, userToChangePassword.getEmail());
        String newPassword = "passwordNEW";
        app.registration().finish(confirmationLink,newPassword);
        assertTrue(app.newSession().login(userToChangePassword.getUsername() , newPassword));
        }


    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

    }

