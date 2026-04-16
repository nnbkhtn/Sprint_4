package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessModal {
    private final WebDriver driver;

    // Модальное окно заказа
    private final By orderModal = By.className("Order_Modal__YZ-d3");
    // Заголовок (Заказ оформлен)
    private final By modalHeader = By.className("Order_ModalHeader__3FDaJ");

    public SuccessModal(WebDriver driver) {
        this.driver = driver;
    }

    public String getSuccessMessage() {
        return driver.findElement(modalHeader).getText();
    }

    public boolean isSuccessModalVisible() {
        return driver.findElement(orderModal).isDisplayed();
    }
}