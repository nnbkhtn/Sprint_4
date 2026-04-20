package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы для кнопок "Заказать"
    private final By orderButtonTop = By.xpath("//div[contains(@class, 'Header_Nav')]//button[contains(text(), 'Заказать')]");
    private final By orderButtonBottom = By.xpath("//div[contains(@class, 'FinishButton')]//button[contains(text(), 'Заказать')]");

    // Локаторы для вопросов о важном
    private final By questionHeading = By.cssSelector("[data-accordion-component='AccordionItemHeading']");
    private final By questionButton = By.cssSelector("[data-accordion-component='AccordionItemButton']");
    private final By questionPanel = By.cssSelector("[data-accordion-component='AccordionItemPanel']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Методы для заказа ---
    public void clickOrderButtonTop() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    public void clickOrderButtonBottom() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    // --- Методы для вопросов ---
    public void waitForQuestions() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(questionHeading, 0));
    }

    public void clickQuestion(int index) {
        List<WebElement> buttons = driver.findElements(questionButton);
        WebElement button = buttons.get(index);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    public String getAnswerText(int index) {
        return wait.until(driver -> {
            List<WebElement> panels = driver.findElements(questionPanel);
            if (panels.size() <= index) {
                return null;
            }

            WebElement panel = panels.get(index);
            String answerText = panel.getText();
            return panel.isDisplayed() && !answerText.isEmpty() ? answerText : null;
        });
    }
}
