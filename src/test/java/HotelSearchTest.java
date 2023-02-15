import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HotelSearchTest extends BaseTest{

    @Test
    public void hotelSearchTest(){

        //looking for hotel
        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();

        //setting check in and check out dates using sendKeys and calendar
        driver.findElement(By.name("checkin")).sendKeys("20/02/2023");
        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='21']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        //setting number of adults and children using '+' button
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();

        //clicking on 'Search' button
        driver.findElement
                (By.xpath("//button[@class='btn btn-lg btn-block btn-primary pfb0 loader']")).click();

        //getting names of hotels and verifying expected values
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class, 'list_title')]//b"))
                .stream()
                .map(webElement -> webElement.getAttribute("textContent"))
                .toList();

        hotelNames.forEach(System.out::println);

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
    }

    @Test
    public void searchHotelWithoutNameTest(){

        driver.findElement(By.name("checkin")).sendKeys("15/02/2023");
        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='16']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        //finding no results header and verifying its message
        WebElement noResultsHeader = driver.findElement(By.xpath("//h2[@class='text-center']"));
        String noResultMessage = noResultsHeader.getText();

        Assert.assertTrue(noResultsHeader.isDisplayed());
        Assert.assertEquals(noResultMessage, "No Results Found");
    }
}