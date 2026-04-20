package tests;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import pages.MainPage;
import pages.OrderPageStepOne;
import pages.OrderPageStepTwo;
import pages.SuccessModal;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

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

        if (clickTopButton) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonBottom();
        }

        fillOrderAndCheck();
    }

    private void fillOrderAndCheck() {
        OrderPageStepOne stepOne = new OrderPageStepOne(driver);
        stepOne.fillStepOne(name, surname, address, metro, phone);
        stepOne.clickNext();

        OrderPageStepTwo stepTwo = new OrderPageStepTwo(driver);
        stepTwo.selectColor(color);
        stepTwo.fillStepTwo(date, period, comment);
        stepTwo.submitOrder();

        SuccessModal successModal = new SuccessModal(driver);
        successModal.waitForSuccessMessage();
        assertTrue(successModal.isSuccessMessageVisible());
    }
}
