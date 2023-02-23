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
    private WebElement hotelsSearchHotelSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement hotelsSearchHotelInput;

    @FindBy(name = "checkin")
    private WebElement hotelsCheckInInput;

    @FindBy(name = "checkout")
    private WebElement hotelsCheckOutInput;

    @FindBy(id = "travellersInput")
    private WebElement hotelsTravelersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement hotelsAdultPlusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement hotelsChildPlusBtn;

    @FindBy(xpath = "//button[@class='btn btn-lg btn-block btn-primary pfb0 loader']")
    private WebElement hotelsSearchButton;

    @FindBy(xpath = "//li[@id='li_myaccount']")
    private List<WebElement> myAccountLink;

    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> signUpLink;

    @FindBy(xpath = "//a[@title='Flights']")
    private WebElement flightsTab;

    @FindBy(xpath = "//label[@for='oneway']")
    private WebElement flightsOneWayButton;

    @FindBy(xpath = "//label[@for='round']")
    private WebElement flightsRoundTripButton;

    @FindBy(name = "cabinclass")
    private WebElement flightsSelectCabinClass;

    @FindBy(xpath = "//option[@value='economy']")
    private WebElement flightsEconomyClass;

    @FindBy(xpath = "//option[@value='business']")
    private WebElement flightsBusinessClass;

    @FindBy(xpath = "//option[@value='first']")
    private WebElement flightsFirstClass;

    @FindBy(xpath = "//span[text()='Enter City Or Airport']")
    private List<WebElement> flightsSearchFlightInputs;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement flightsSearchFlightSpan;

    @FindBy(name = "departure")
    private WebElement flightsDepartureDateInput;

    @FindBy(name = "arrival")
    private WebElement flightsReturnDateInput;

    @FindBy(name = "totalManualPassenger")
    private WebElement flightsTotalPassengers;

    @FindBy(name = "madult")
    private WebElement flightsTotalAdultPassengers;

    @FindBy(name = "mchildren")
    private WebElement flightsTotalChildrenPassengers;

    @FindBy(name = "minfant")
    private WebElement flightsTotalInfantPassengers;

    @FindBy(id = "sumManualPassenger")
    private WebElement flightsPassengersSetDoneButton;

    @FindBy(xpath = "//button[@class='close']")
    private WebElement flightsPassengersSetCloseButton;

    @FindBy(xpath = "//div[@class='bgfade col-md-3 col-xs-12 search-button']/button")
    private WebElement flightsSearchFlightButton;

    @FindBy(xpath = "//a[@title='Tours']")
    private WebElement toursTab;

    @FindBy(xpath = "//span[text()='Search by Listing or City Name']")
    private WebElement toursSearchToursSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement toursSearchToursInput;

    @FindBy(name = "date")
    private WebElement toursDate;

    @FindBy(id = "adults")
    private WebElement toursGuestsSelect;

    @FindBy(id = "s2id_tourtype")
    private WebElement toursTourTypeSelect;

    @FindBy(xpath = "//div[@class='col-md-2 form-group go-right col-xs-12 search-button']/button[@class='btn-primary btn btn-lg btn-block pfb0 loader']")
    private WebElement toursSearchButton;

    @FindBy(xpath = "//a[@title='Cars']")
    private WebElement carsTab;

    @FindBy(id = "s2id_carlocations")
    private WebElement carsPickUpInput;

    @FindBy(id = "s2id_carlocations2")
    private WebElement carsDropOffInput;

    @FindBy(id = "departcar")
    private WebElement carsPickUpDate;

    @FindBy(name = "pickupTime")
    private WebElement carsPickUpTimeSelect;

    @FindBy(id = "returncar")
    private WebElement carsDropOffDate;

    @FindBy(name = "dropoffTime")
    private WebElement carsDropOffTimeSelect;

    @FindBy(xpath = "//div[@class='bgfade col-md-2 form-group go-right col-xs-12 search-button']/button")
    private WebElement carsSearchButton;

    @FindBy(xpath = "//a[@title='Ivisa']")
    private WebElement visaTab;

    @FindBy(id = "s2id_autogen4")
    private WebElement visaCountryFromSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement visaCountryFromInput;

    @FindBy(id = "s2id_autogen6")
    private WebElement visaCountryToSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement visaCountryToInput;

    @FindBy(xpath = "//div[@class='col-md-4 form-group go-right col-xs-12 search-button']/button")
    private WebElement visaSearchButton;

    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger();

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setCityName(String cityName) {
        logger.info("Setting city[" + cityName + "]");
        hotelsSearchHotelSpan.click();
        hotelsSearchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        SeleniumHelper.waitForElementToExist(driver, By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
        logger.info("Setting city[" + cityName + "] - done\n");
    }

    public void setDates(String checkInDate, String checkOutDate) {
        logger.info("Setting dates[check-in date: " + checkInDate + ", check-out date: " + checkOutDate + "]");
        hotelsCheckInInput.sendKeys(checkInDate);
        hotelsCheckOutInput.sendKeys(checkOutDate);
        logger.info("Setting dates[check-in date: " + checkInDate + ", check-out date: " + checkOutDate + "] - done\n");
    }

    public void setTravelers(int adultsToAdd, int childToAdd) {
        logger.info("Setting travelers[adults: " + adultsToAdd + ", child: " + childToAdd + "]");
        SeleniumHelper.waitForElementToBeVisible(driver, hotelsTravelersInput);
        hotelsTravelersInput.click();
        addTraveler(hotelsAdultPlusBtn, adultsToAdd);
        addTraveler(hotelsChildPlusBtn, childToAdd);
        logger.info("Setting travelers[adults: " + adultsToAdd + ", child: " + childToAdd + "] - done");
    }

    public void performSearch() {
        logger.info("Perform search");
        hotelsSearchButton.click();
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