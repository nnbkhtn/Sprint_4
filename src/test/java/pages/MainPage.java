package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    // Локаторы для кнопок "Заказать"
    private final By orderButtonTop = By.xpath("//div[contains(@class, 'Header_Nav')]//button[contains(text(), 'Заказать')]");
    private final By orderButtonBottom = By.xpath("//div[contains(@class, 'FinishButton')]//button[contains(text(), 'Заказать')]");

    // Локаторы для вопросов о важном
    private final By questionHeading = By.cssSelector("[data-accordion-component='AccordionItemHeading']");
    private final By questionPanel = By.cssSelector("[data-accordion-component='AccordionItemPanel']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // --- Методы для заказа ---
    public void clickOrderButtonTop() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    public void clickOrderButtonBottom() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom));
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