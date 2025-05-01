import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessfullyRescheduleMeeting() {
        // Генерация пользователя
        UserInfo user = DataGenerator.generateUser("ru");
        String firstMeetingDate = DataGenerator.generateDate(4);
        String secondMeetingDate = DataGenerator.generateDate(7);

        // Заполнение формы впервые
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(user.getName());
        $("[data-test-id=phone] input").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();

        // Нажатие на кнопку "Запланировать"
        $$("button span.button__text")
                .findBy(Condition.exactText("Запланировать"))
                .closest("button")
                .shouldBe(Condition.enabled, Duration.ofSeconds(10))
                .click();

        // Проверка успешного планирования
        $("[data-test-id=success-notification]")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Успешно! Встреча успешно запланирована на " + firstMeetingDate));


        // Перепланировка: ввод новой даты
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(secondMeetingDate);

        // Снова нажимаем "Запланировать"
        $$("button span.button__text")
                .findBy(Condition.exactText("Запланировать"))
                .closest("button")
                .shouldBe(Condition.enabled, Duration.ofSeconds(10))
                .click();

        // Проверка уведомления о перепланировке
        $("[data-test-id=replan-notification]")
                .shouldBe(Condition.visible, Duration.ofSeconds(15));

        // Клик на кнопку "Перепланировать"
        $$("button span.button__text")
                .findBy(Condition.exactText("Перепланировать"))
                .closest("button")
                .shouldBe(Condition.enabled, Duration.ofSeconds(10))
                .click();

        // Проверка успешной перепланировки
        $("[data-test-id=success-notification]")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Успешно! Встреча успешно запланирована на " + secondMeetingDate));

    }
}