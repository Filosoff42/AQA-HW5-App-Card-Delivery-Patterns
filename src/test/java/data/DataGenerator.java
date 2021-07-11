package data;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DataGenerator {

    private DataGenerator() {}

    public static RegistrationInfo generateData() {
        Faker faker = new Faker(new Locale("ru"));
        return new RegistrationInfo(
                faker.address().cityName(),
                faker.name().lastName()+" "+faker.name().firstName(),
                faker.phoneNumber().cellPhone()
        );
    }

    public static String generateDate() {
        Faker faker = new Faker(new Locale("ru"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = faker.date().future(365, 3, TimeUnit.DAYS);
        String stringDate = dateFormat.format(date);
        return stringDate;
    }
}