package org.WebAutomation.tests;
import org.WebAutomation.basetests.CommonToAllTest;
import org.WebAutomation.pages.PageObjectModel.DashboardPage_POM;
import org.WebAutomation.pages.PageObjectModel.LoginPage_POM;
import org.WebAutomation.utils.PropertyReader;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;
public class TestVWOLoginPOMBaseTest extends CommonToAllTest {


    @Test(groups = {"smoke"})
    public void testLoginNegative() {

        LoginPage_POM loginPagePom = new LoginPage_POM();
        loginPagePom.openVWOLoginURL();
        String error_msg_text = loginPagePom.loginToVWOInvalidCreds();

        // TestNG
        Assert.assertEquals(error_msg_text, "Your email, password, IP address or location did not match");

        // AssertJ
        Assertions.assertThat(error_msg_text )
                .isNotNull()
                .isNotBlank()
                .contains(PropertyReader.readyKey("error_message"));
    }

    @Test()
    public void testLoginPositive() {
        LoginPage_POM loginPagePom = new LoginPage_POM();
        loginPagePom.openVWOLoginURL();
        loginPagePom.loginToVWOValidCreds();
        DashboardPage_POM dashboardPagePom = loginPagePom.afterLoginVWOValidCreds();
        String expected_username = dashboardPagePom.loggedInUserName();

        // TestNG
        Assert.assertEquals(expected_username, PropertyReader.readyKey("expected_username"));

        // AssertJ
        Assertions.assertThat(expected_username)
                .isNotNull()
                .isNotBlank()
                .contains(PropertyReader.readyKey("expected_username"));

    }

}
