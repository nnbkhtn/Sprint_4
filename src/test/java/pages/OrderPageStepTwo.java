package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPageStepTwo {
    private final WebDriver driver;

    private final By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By periodDropdown = By.xpath(".//div[text()='* Срок аренды']");
    private final By periodOption = By.className("Dropdown-option");
    private final By colorBlack = By.xpath("//label[@for='black']");
    private final By colorGrey = By.xpath("//label[@for='grey']");
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    private final By orderButton = By.xpath("//button[contains(@class, 'Button_Middle_1CSJM') and text()='Заказать']");

    private final By confirmButton = By.xpath(".//button[text()='Да']");
    private final By cookieButton = By.xpath("//button[text()='да все привыкли']");

    public OrderPageStepTwo(WebDriver driver) {
        this.driver = driver;
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
        // Закрыть куки
        if (driver.findElements(cookieButton).size() > 0) {
            driver.findElement(cookieButton).click();
        }

        // Найти кнопку "Заказать" и кликнуть
        driver.findElement(orderButton).click();

        // Кликнуть "Да" в модальном окне
        driver.findElement(confirmButton).click();
    }
}