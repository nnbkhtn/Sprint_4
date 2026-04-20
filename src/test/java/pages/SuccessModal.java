package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SuccessModal {
    private final WebDriver driver;

    // Локатор для сообщения об успешном заказе
    private final By successMessage = By.xpath("//*[contains(text(), 'Заказ оформлен')]");

    public SuccessModal(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для ожидания появления сообщения
    public void waitForSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }

    // Метод для проверки видимости сообщения
    public boolean isSuccessMessageVisible() {
        return driver.findElement(successMessage).isDisplayed();
    }
}