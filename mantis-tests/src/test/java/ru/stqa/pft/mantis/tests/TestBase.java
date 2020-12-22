package ru.stqa.pft.mantis.tests;


import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestBase {

    protected final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php","config_inc.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config_inc.bak", "config_inc.php");
        app.stop();










}

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issue =mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
        ObjectRef status = issue.getStatus();
        return !status.getName().equals("closed");
    }
    private MantisConnectPortType getMantisConnect() throws MalformedURLException, ServiceException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("mantis.SoapConnectUrl")));
    }
}