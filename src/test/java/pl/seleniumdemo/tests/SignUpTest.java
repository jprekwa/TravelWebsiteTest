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

        String firstName = "John";
        String lastName = "Doe";
        int random = (int) (Math.random() * 1000);
        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber("12345678")
                .setEmail("john.doe" + random + "@email.com")
                .setPassword("topSecret")
                .confirmPassword("topSecret")
                .signUp();

        Assert.assertTrue(loggedUserPage.getHeaderText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeaderText(), "Hi, " + firstName + " " + lastName);
    }

    @Test
    public void signUpEmptyFormTest() {

        SignUpPage signUpPage = new HotelSearchPage(driver).openSignUpForm();
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

        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("John")
                .setLastName("Doe")
                .setPhoneNumber("12345678")
                .setEmail("Invalid Email")
                .setPassword("topSecret")
                .confirmPassword("topSecret");
        signUpPage.signUp();

        Assert.assertTrue(signUpPage.getErrorMessagesList().contains("The Email field must contain a valid email address."));
    }
}