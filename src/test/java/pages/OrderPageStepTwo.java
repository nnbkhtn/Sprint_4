package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPageStepTwo {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By periodDropdown = By.xpath(".//div[text()='* Срок аренды']");
    private final By periodOption = By.className("Dropdown-option");
    private final By colorBlack = By.xpath("//label[@for='black']");
    private final By colorGrey = By.xpath("//label[@for='grey']");
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    private final By orderButton = By.xpath("//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']");
    private final By confirmButton = By.xpath(".//button[text()='Да']");
    private final By cookieButton = By.xpath("//button[text()='да все привыкли']");

    public OrderPageStepTwo(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectColor(String color) {
        if (color.equals("black")) {
            driver.findElement(colorBlack).click();
        } else {
            driver.findElement(colorGrey).click();
        }
    }

    public void fillStepTwo(String date, String period, String comment) {
        driver.findElement(dateInput).sendKeys(date);
        driver.findElement(By.tagName("body")).click();

        driver.findElement(periodDropdown).click();
        driver.findElements(periodOption).get(Integer.parseInt(period) - 1).click();

        driver.findElement(commentInput).sendKeys(comment);
    }

    public void submitOrder() {
        if (!driver.findElements(cookieButton).isEmpty()) {
            WebElement cookie = driver.findElement(cookieButton);
            if (cookie.isDisplayed()) {
                cookie.click();
            }
        }

        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }
}
