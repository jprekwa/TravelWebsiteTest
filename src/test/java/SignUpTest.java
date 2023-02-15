import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SignUpTest extends BaseTest{

    @Test
    public void signUpTest(){

        //finding sign up page
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElements(By.xpath("//a[text()='  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        //filling sign up form
        String firstName = "John";
        String lastName = "Doe";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "john.doe" + randomNumber + "@email.com";
        driver.findElement(By.name("firstname")).sendKeys(firstName);
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("12345678");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("topSecret");
        driver.findElement(By.name("confirmpassword")).sendKeys("topSecret");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        WebElement header = driver.findElement(By.xpath(("//h3[@class='RTL']")));

        Assert.assertEquals(header.getText(), "Hi, " + firstName + " " + lastName);
    }

    @Test
    public void signUpEmptyFormTest(){

        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElements(By.xpath("//a[text()='  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        //verifying error window and its messages
        WebElement errorWindow = driver.findElement(By.xpath("//div[@class='alert alert-danger']"));
        List<String> errorMessagesList = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                .stream()
                .map(WebElement::getText)
                .toList();
        
        Assert.assertTrue(errorMessagesList.contains("The Email field is required."));
        Assert.assertTrue(errorMessagesList.contains("The Password field is required."));
        Assert.assertTrue(errorMessagesList.contains("The Password field is required."));
        Assert.assertTrue(errorMessagesList.contains("The First name field is required."));
        Assert.assertTrue(errorMessagesList.contains("The Last Name field is required."));
    }

    @Test
    public void invalidEmailFormatTest(){

        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElements(By.xpath("//a[text()='  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElement(By.name("firstname")).sendKeys("firstName");
        driver.findElement(By.name("lastname")).sendKeys("lastName");
        driver.findElement(By.name("phone")).sendKeys("12345678");
        driver.findElement(By.name("email")).sendKeys("someInvalidEmail");
        driver.findElement(By.name("password")).sendKeys("topSecret");
        driver.findElement(By.name("confirmpassword")).sendKeys("topSecret");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        //verifying error window and its message
        WebElement errorWindow = driver.findElement(By.xpath("//div[@class='alert alert-danger']"));
        String errorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger']//p")).getText();

        Assert.assertTrue(errorWindow.isDisplayed());
        Assert.assertEquals(errorMessage, "The Email field must contain a valid email address.");
    }
}