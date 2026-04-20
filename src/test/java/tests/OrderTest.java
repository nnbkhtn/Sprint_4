package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.OrderPageStepOne;
import pages.OrderPageStepTwo;
import pages.SuccessModal;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    // Константа для URL сайта
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    // Новый параметр: какую кнопку нажимать (true - верхняя, false - нижняя)
    private final boolean clickTopButton;

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String color;
    private final String date;
    private final String period;
    private final String comment;

    public OrderTest(boolean clickTopButton, String name, String surname, String address, String metro,
                     String phone, String color, String date, String period, String comment) {
        this.clickTopButton = clickTopButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.color = color;
        this.date = date;
        this.period = period;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                // Верхняя кнопка + Набор данных 1
                {true, "Федор", "Серов", "Москва, ул. Ленина 1", "Третьяковская",
                        "+79991234567", "black", "20.05.2026", "1", "Срочный заказ"},

                // Нижняя кнопка + Набор данных 1
                {false, "Федор", "Серов", "Москва, ул. Ленина 1", "Третьяковская",
                        "+79991234567", "black", "20.05.2026", "1", "Срочный заказ"},

                // Верхняя кнопка + Набор данных 2
                {true, "Василий", "Скворцов", "Санкт-Петербург, Невский 10", "Невский проспект",
                        "+79997654321", "grey", "25.05.2026", "3", "Позвоните за полчаса"},

                // Нижняя кнопка + Набор данных 2
                {false, "Василий", "Скворцов", "Санкт-Петербург, Невский 10", "Невский проспект",
                        "+79997654321", "grey", "25.05.2026", "3", "Позвоните за полчаса"}
        });
    }

    @Test
    public void testOrder() {
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);

        // Выбираем кнопку в зависимости от параметра
        if (clickTopButton) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonBottom();
        }

        fillOrderAndCheck();
    }

    private void fillOrderAndCheck() {
        // Шаг 1
        OrderPageStepOne stepOne = new OrderPageStepOne(driver);
        stepOne.fillStepOne(name, surname, address, metro, phone);
        stepOne.clickNext();

        // Шаг 2
        OrderPageStepTwo stepTwo = new OrderPageStepTwo(driver);
        stepTwo.selectColor(color);
        stepTwo.fillStepTwo(date, period, comment);
        stepTwo.submitOrder();

        // Проверка успешного заказа
        SuccessModal successModal = new SuccessModal(driver);
        successModal.waitForSuccessMessage();
        assertTrue(successModal.isSuccessMessageVisible());

        assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Заказ оформлен')]")).isDisplayed());
    }
}