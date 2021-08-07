import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataGenerator;
import data.RegistrationInfo;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

class AppCardDeliveryPatternsTest {


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @BeforeEach
    void goToURL() {
        open("http://localhost:9999");
    }

    RegistrationInfo data = DataGenerator.generateData();



    @Test
    void shouldRescheduleSuccess() {
        String date = DataGenerator.generateDate();
        String dateNew = DataGenerator.generateDate();
        $("[data-test-id=city] input").setValue(data.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue(data.getName());
        $("[data-test-id=phone] input").setValue(data.getPhone());
        $("[data-test-id=agreement] span").click();
        $$("button").find(exactText("Запланировать")).click();
        $(".notification__content").should(visible, Duration.ofSeconds(15)).shouldHave(text(date));
        $("[data-test-id=date] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateNew);
        $$("button").find(exactText("Запланировать")).click();
        $(".notification_status_error").should(visible, Duration.ofSeconds(15));
        $$("button").find(exactText("Перепланировать")).click();
        $(".notification__content").should(visible, Duration.ofSeconds(15)).shouldHave(text(dateNew));
    }


}
