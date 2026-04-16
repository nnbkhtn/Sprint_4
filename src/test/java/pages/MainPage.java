package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class MainPage {
    private final WebDriver driver;

    // Локаторы для кнопок "Заказать"
    private final By orderButtonTop = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and not(contains(@class, 'Button_Middle_1CSJM')) and text()='Заказать']");
    private final By orderButtonBottom = By.xpath("//button[contains(@class, 'Button_Middle_1CSJM') and text()='Заказать']");

    // Локаторы для вопросов о важном
    private final By questionHeading = By.className("accordion__heading");
    private final By questionPanel = By.className("accordion__panel");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // --- Методы для заказа ---
    public void clickOrderButtonTop() {
        WebElement button = driver.findElement(orderButtonTop);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    public void clickOrderButtonBottom() {
        WebElement button = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    // --- Методы для вопросов ---
    public void clickQuestion(int index) {
        // Найти все заголовки вопросов
        List<WebElement> headings = driver.findElements(questionHeading);
        // Взять нужный по индексу
        WebElement heading = headings.get(index);

        // Скроллить к нему и кликать через JS
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", heading);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", heading);
    }

    public boolean isAnswerVisible(int index) {
        // Найти все панели с ответами
        List<WebElement> panels = driver.findElements(questionPanel);
        // Проверить видимость нужной панели
        return panels.get(index).isDisplayed();
    }
}