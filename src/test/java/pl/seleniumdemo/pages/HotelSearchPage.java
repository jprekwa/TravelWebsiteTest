package pl.seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.util.List;

public class HotelSearchPage {

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

    @FindBy(name = "checkin")
    private WebElement checkInInput;

    @FindBy(name = "checkout")
    private WebElement checkOutInput;

    @FindBy(id = "travellersInput")
    private WebElement travelersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(xpath = "//button[@class='btn btn-lg btn-block btn-primary pfb0 loader']")
    private WebElement searchButton;

    @FindBy(xpath = "//li[@id='li_myaccount']")
    private List<WebElement> myAccountLink;

    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> signUpLink;

    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger();

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setCityName(String cityName) {
        logger.info("Setting city[" + cityName + "]");
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        SeleniumHelper.waitForElementToExist(driver, By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
        logger.info("Setting city[" + cityName + "] - done\n");
    }

    public void setDates(String checkInDate, String checkOutDate) {
        logger.info("Setting dates[check-in date: " + checkInDate + ", check-out date: " + checkOutDate + "]");
        checkInInput.sendKeys(checkInDate);
        checkOutInput.sendKeys(checkOutDate);
        logger.info("Setting dates[check-in date: " + checkInDate + ", check-out date: " + checkOutDate + "] - done\n");
    }

    public void setTravelers(int adultsToAdd, int childToAdd) {
        logger.info("Setting travelers[adults: " + adultsToAdd + ", child: " + childToAdd + "]");
        SeleniumHelper.waitForElementToBeVisible(driver, travelersInput);
        travelersInput.click();
        addTraveler(adultPlusBtn, adultsToAdd);
        addTraveler(childPlusBtn, childToAdd);
        logger.info("Setting travelers[adults: " + adultsToAdd + ", child: " + childToAdd + "] - done");
    }

    public void performSearch() {
        logger.info("Perform search");
        searchButton.click();
        logger.info("Perform search - done");
    }

    private void addTraveler(WebElement travelerButton, int numberOfTravelers) {
        SeleniumHelper.waitForElementToBeVisible(driver, travelerButton);
        for (int i = 0; i < numberOfTravelers; i++) {
            travelerButton.click();
        }
    }

    public void openSignUpForm() {
        myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        signUpLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
    }

}