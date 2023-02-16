package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void hotelSearchTest() {

        List<String> hotelNames = new HotelSearchPage(driver)
                .setCityName("Dubai")
                .setDates("16/02/2023", "17/02/2023")
                .setTravelers(1, 2)
                .performSearch().getHotelNames();

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
    }

    @Test
    public void searchHotelWithoutNameTest() {

        ResultsPage resultsPage = new HotelSearchPage(driver)
                .setDates("16/02/2023", "17/02/2023")
                .setTravelers(0, 1)
                .performSearch();

        Assert.assertTrue(resultsPage.getResultsHeader().isDisplayed());
        Assert.assertEquals(resultsPage.getResultsText(), "No Results Found");
    }
}