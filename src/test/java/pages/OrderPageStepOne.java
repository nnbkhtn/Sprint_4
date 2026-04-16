package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPageStepOne {
    private final WebDriver driver;

    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroInput = By.className("select-search__input");
    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    public OrderPageStepOne(WebDriver driver) {
        this.driver = driver;
    }

    public void fillStepOne(String name, String surname, String address, String metro, String phone) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));

        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);

        // Метро - выпадающий список
        WebElement metroField = driver.findElement(metroInput);
        metroField.click();
        metroField.clear();
        metroField.sendKeys(metro);

        // Ждать появления опций и выбрать первую из них
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("select-search__option")));
        metroField.sendKeys(Keys.ARROW_DOWN);
        metroField.sendKeys(Keys.ENTER);

        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickNext() {
        WebElement button = driver.findElement(nextButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }
}