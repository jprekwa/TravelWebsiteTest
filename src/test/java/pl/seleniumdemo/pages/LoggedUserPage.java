package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.utils.SeleniumHelper;

public class LoggedUserPage {

    @FindBy(xpath = "//h3[@class='RTL']")
    private WebElement header;

    private WebDriver driver;

    public LoggedUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getHeaderText() {
        SeleniumHelper.waitForElementToBeVisible(driver, header);
        return header.getText();
    }

}
