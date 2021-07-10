import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DataGenerator {

    private DataGenerator() {}

    public static RegistrationInfo generateData() {
        Faker faker = new Faker(new Locale("ru"));
        return new RegistrationInfo(
                faker.address().cityName(),
                faker.date().future(365, 3, TimeUnit.DAYS),
                faker.name().lastName()+" "+faker.name().firstName(),
                faker.phoneNumber().cellPhone()
        );
    }
}