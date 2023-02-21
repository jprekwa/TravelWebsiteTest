package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() throws IOException {

        ExtentTest test = extentReports.createTest("Sign Up Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        String firstName = "John";
        String lastName = "Doe";
        int random = (int) (Math.random() * 1000);
        String email = firstName + "." + lastName + random + "@email.com";

        hotelSearchPage.openSignUpForm();
        test.log(Status.PASS, "Open sign up form done", SeleniumHelper.getScreenshot(driver));
        signUpPage.setFirstName(firstName);
        test.log(Status.PASS, "Setting first name done", SeleniumHelper.getScreenshot(driver));
        signUpPage.setLastName(lastName);
        test.log(Status.PASS, "Setting last name done", SeleniumHelper.getScreenshot(driver));
        signUpPage.setPhoneNumber("12345678");
        test.log(Status.PASS, "Setting phone number done", SeleniumHelper.getScreenshot(driver));
        signUpPage.setEmail(email);
        test.log(Status.PASS, "Setting email done", SeleniumHelper.getScreenshot(driver));
        signUpPage.setPassword("topSecret");
        test.log(Status.PASS, "Setting password done", SeleniumHelper.getScreenshot(driver));
        signUpPage.confirmPassword("topSecret");
        test.log(Status.PASS, "Setting confirmed password done", SeleniumHelper.getScreenshot(driver));
        signUpPage.signUp();
        test.log(Status.PASS, "Perform sign up done", SeleniumHelper.getScreenshot(driver));

        Assert.assertTrue(loggedUserPage.getHeaderText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeaderText(), "Hi, " + firstName + " " + lastName);
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    public void signUpEmptyFormTest() throws IOException {

        ExtentTest test = extentReports.createTest("Sign Up Empty Form Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);

        hotelSearchPage.openSignUpForm();
        test.log(Status.PASS, "Open sign up form done", SeleniumHelper.getScreenshot(driver));
        signUpPage.signUp();
        test.log(Status.PASS, "Perform sign up done", SeleniumHelper.getScreenshot(driver));

        List<String> errorMessagesList = signUpPage.getErrorMessagesList();

        Assert.assertTrue(errorMessagesList.contains("The Email field is required."));
        Assert.assertTrue(errorMessagesList.contains("The Password field is required."));
        Assert.assertTrue(errorMessagesList.contains("The Password field is required."));
        Assert.assertTrue(errorMessagesList.contains("The First name field is required."));
        Assert.assertTrue(errorMessagesList.contains("The Last Name field is required."));
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    public void invalidEmailFormatTest() throws IOException {

        ExtentTest test = extentReports.createTest("Invalid Email Format Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);

        hotelSearchPage.openSignUpForm();
        test.log(Status.PASS, "Open sign up form done", SeleniumHelper.getScreenshot(driver));
        signUpPage.setFirstName("John");
        test.log(Status.PASS, "Setting first name done", SeleniumHelper.getScreenshot(driver));
        signUpPage.setLastName("Doe");
        test.log(Status.PASS, "Setting last name done", SeleniumHelper.getScreenshot(driver));
        signUpPage.setPhoneNumber("12345678");
        test.log(Status.PASS, "Setting phone number done", SeleniumHelper.getScreenshot(driver));
        signUpPage.setEmail("Invalid Email");
        test.log(Status.PASS, "Setting invalid email done", SeleniumHelper.getScreenshot(driver));
        signUpPage.setPassword("topSecret");
        test.log(Status.PASS, "Setting password done", SeleniumHelper.getScreenshot(driver));
        signUpPage.confirmPassword("topSecret");
        test.log(Status.PASS, "Setting confirmed password done", SeleniumHelper.getScreenshot(driver));
        signUpPage.signUp();
        test.log(Status.PASS, "Perform sign up done", SeleniumHelper.getScreenshot(driver));

        Assert.assertTrue(signUpPage.getErrorMessagesList().contains("The Email field must contain a valid email address."));
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }
}