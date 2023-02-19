package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        String firstName = "John";
        String lastName = "Doe";
        int random = (int) (Math.random() * 1000);
        String email = firstName + "." + lastName + random + "@email.com";

        hotelSearchPage.openSignUpForm();
        signUpPage.setFirstName(firstName);
        signUpPage.setLastName(lastName);
        signUpPage.setPhoneNumber("12345678");
        signUpPage.setEmail(email);
        signUpPage.setPassword("topSecret");
        signUpPage.confirmPassword("topSecret");
        signUpPage.signUp();

        Assert.assertTrue(loggedUserPage.getHeaderText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeaderText(), "Hi, " + firstName + " " + lastName);
    }

    @Test
    public void signUpEmptyFormTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);

        hotelSearchPage.openSignUpForm();
        signUpPage.signUp();

        List<String> errorMessagesList = signUpPage.getErrorMessagesList();

        Assert.assertTrue(errorMessagesList.contains("The Email field is required."));
        Assert.assertTrue(errorMessagesList.contains("The Password field is required."));
        Assert.assertTrue(errorMessagesList.contains("The Password field is required."));
        Assert.assertTrue(errorMessagesList.contains("The First name field is required."));
        Assert.assertTrue(errorMessagesList.contains("The Last Name field is required."));
    }

    @Test
    public void invalidEmailFormatTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);

        hotelSearchPage.openSignUpForm();
        signUpPage.setFirstName("John");
        signUpPage.setLastName("Doe");
        signUpPage.setPhoneNumber("12345678");
        signUpPage.setEmail("Invalid Email");
        signUpPage.setPassword("topSecret");
        signUpPage.confirmPassword("topSecret");
        signUpPage.signUp();

        Assert.assertTrue(signUpPage.getErrorMessagesList().contains("The Email field must contain a valid email address."));
    }
}