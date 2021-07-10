import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

class AppCardDeliveryPatternsTest {

    @BeforeEach
    void goToURL() {
        open("http://localhost:9999");
    }


    RegistrationInfo data = DataGenerator.generateData();
    Date date = data.getDate();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    String stringDate = dateFormat.format(date);
    Date dateNew = new Faker().date().future(365, 3, TimeUnit.DAYS);
    String stringDateNew = dateFormat.format(dateNew);



    @Test
    void shouldRescheduleSuccess() {
        $("[data-test-id=city] input").setValue(data.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id=date] input").setValue(stringDate);
        $("[data-test-id=name] input").setValue(data.getName());
        $("[data-test-id=phone] input").setValue(data.getPhone());
        $("[data-test-id=agreement] span").click();
        $$("button").find(exactText("Запланировать")).click();
        $(".notification__content").should(visible, Duration.ofSeconds(15)).shouldHave(text(stringDate));
        $("[data-test-id=date] input").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id=date] input").setValue(stringDateNew);
        $$("button").find(exactText("Запланировать")).click();
        $(".notification_status_error").should(visible, Duration.ofSeconds(15));
        $$("button").find(exactText("Перепланировать")).click();
        $(".notification__content").should(visible, Duration.ofSeconds(15)).shouldHave(text(stringDateNew));
    }


}
