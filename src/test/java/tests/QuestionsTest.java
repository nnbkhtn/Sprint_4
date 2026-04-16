package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import java.time.Duration;

public class QuestionsTest extends BaseTest {

    @Test
    public void testQuestionsAccordion() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);

        // Ждать появления вопросов
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("accordion__heading")));

        // Кликнуть на первый вопрос
        mainPage.clickQuestion(0);

        // Ждать появления ответа
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("accordion__panel")));

        // Проверить, что ответ виден
        assert mainPage.isAnswerVisible(0) : "Ответ на вопрос 0 не появился";
    }
}