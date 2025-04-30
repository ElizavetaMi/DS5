import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@UtilityClass
public class DataGenerator {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");  // Сделано статическим
    private static final String[] validCities = {  // Сделано статическим
            "Москва", "Санкт-Петербург", "Казань", "Новосибирск", "Екатеринбург", "Челябинск", "Нижний Новгород"
    };
    private static String phone;

    public static String generateDate(int daysFromToday) {
        return LocalDate.now().plusDays(daysFromToday).format(formatter);  // Использует статический formatter
    }

    public static UserInfo generateUser(String ru) {
        // Обеспечим правильную локализацию для русского языка
        Faker faker = new Faker(new Locale("ru"));
        String city = validCities[new Random().nextInt(validCities.length)];
        String name = faker.name().lastName() + " " + faker.name().firstName();  // Формат: Фамилия Имя
        return new UserInfo(city, name, phone);  // Создаем объект UserInfo
    }
}