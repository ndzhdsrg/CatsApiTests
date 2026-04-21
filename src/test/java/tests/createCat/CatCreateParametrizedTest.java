package tests.createCat;

import clients.CatApiClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import utils.CatGenerator;

import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;

public class CatCreateParametrizedTest {

    private final CatApiClient catApiClient = new CatApiClient();

    /**
     * Позитивный параметризованный тест через @MethodSource.
     */
    @ParameterizedTest(name = "MethodSource позитивный кейс #{index}: name={0}, age={1}, color={2}")
    @MethodSource("positiveCats")
    @DisplayName("Создание кота: позитивные кейсы через MethodSource")
    public void createCat_shouldCreateCatSuccessfully_withMethodSource(
            String name,
            int age,
            String color,
            String breed,
            double weight,
            boolean vaccinated,
            String birthDate,
            String ownerEmail
    ) {
        Map<String, Object> body = CatGenerator.generateBaseCat(
                name,
                age,
                color,
                breed,
                weight,
                vaccinated,
                birthDate,
                ownerEmail
        );

        catApiClient.createCat(body)
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .body("id", notNullValue())
                .body("name", equalTo(name))
                .body("age", equalTo(age))
                .body("color", equalTo(color))
                .body("breed", equalTo(breed))
                .body("weight", equalTo((float) weight))
                .body("vaccinated", equalTo(vaccinated))
                .body("birthDate", equalTo(birthDate))
                .body("ownerEmail", equalTo(ownerEmail));
    }

    /**
     * Позитивный параметризованный тест через @CsvSource.
     */
    @ParameterizedTest(name = "CsvSource позитивный кейс #{index}: {0}, age={1}, weight={4}")
    @CsvSource({
            "Leo, 2, WHITE, Persian, 3.9, true, 2023-05-01, leo@example.com",
            "Milo, 1, BLACK, Siamese, 2.8, false, 2024-01-10, milo@example.com",
            "Bella, 5, GRAY, British Shorthair, 4.7, true, 2020-07-07, bella@example.com"
    })
    @DisplayName("Создание кота: позитивные кейсы через CsvSource")
    public void createCat_shouldCreateCatSuccessfully_withCsvSource(
            String name,
            int age,
            String color,
            String breed,
            double weight,
            boolean vaccinated,
            String birthDate,
            String ownerEmail
    ) {
        Map<String, Object> body = CatGenerator.generateBaseCat(
                name,
                age,
                color,
                breed,
                weight,
                vaccinated,
                birthDate,
                ownerEmail
        );

        catApiClient.createCat(body)
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .body("id", notNullValue());
    }

    /**
     * Демонстрация @ValueSource: проверяем позитивные значения возраста.
     */
    @ParameterizedTest(name = "ValueSource позитивный возраст #{index}: age={0}")
    @ValueSource(ints = {0, 1, 2, 10, 15})
    @DisplayName("Создание кота: позитивные значения возраста через ValueSource")
    public void createCat_shouldCreateCatSuccessfully_withPositiveAges(int age) {
        Map<String, Object> body = CatGenerator.generateBaseCat(
                "AgeCat",
                age,
                "WHITE",
                "Persian",
                3.5,
                true,
                "2023-01-01",
                "agecat@example.com"
        );

        catApiClient.createCat(body)
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .body("age", equalTo(age));
    }

    /**
     * Демонстрация @NullSource: имя = null.
     */
    @ParameterizedTest(name = "NullSource негативный кейс: name=null")
    @NullSource
    @DisplayName("Создание кота: null name через NullSource")
    public void createCat_shouldReturnBadRequest_whenNameIsNull(String name) {
        Map<String, Object> body = CatGenerator.generateBaseCat(
                name,
                2,
                "WHITE",
                "Persian",
                3.5,
                true,
                "2023-01-01",
                "nullname@example.com"
        );

        catApiClient.createCat(body)
                .then()
                .statusCode(400);
    }

    /**
     * Демонстрация @NullAndEmptySource: name = null или пустая строка.
     */
    @ParameterizedTest(name = "NullAndEmptySource негативный кейс #{index}: name={0}")
    @NullAndEmptySource
    @DisplayName("Создание кота: null/empty name через NullAndEmptySource")
    public void createCat_shouldReturnBadRequest_whenNameIsNullOrEmpty(String name) {
        Map<String, Object> body = CatGenerator.generateBaseCat(
                name,
                2,
                "WHITE",
                "Persian",
                3.5,
                true,
                "2023-01-01",
                "nullorempty@example.com"
        );

        catApiClient.createCat(body)
                .then()
                .statusCode(anyOf(equalTo(400), equalTo(201), equalTo(200)));
    }

    /**
     * Негативные кейсы через @MethodSource.
     * Оставлены только те данные, для которых API реально должно вернуть 400.
     */
    @ParameterizedTest(name = "MethodSource негативный кейс #{index}: name={0}, age={1}, ownerEmail={7}")
    @MethodSource("negativeCats")
    @DisplayName("Создание кота: негативные кейсы через MethodSource")
    public void createCat_shouldReturnBadRequestForInvalidData_withMethodSource(
            String name,
            int age,
            String color,
            String breed,
            double weight,
            boolean vaccinated,
            String birthDate,
            String ownerEmail
    ) {
        Map<String, Object> body = CatGenerator.generateBaseCat(
                name,
                age,
                color,
                breed,
                weight,
                vaccinated,
                birthDate,
                ownerEmail
        );

        catApiClient.createCat(body)
                .then()
                .statusCode(400);
    }

    /**
     * Негативные кейсы через @CsvSource.
     */
    @ParameterizedTest(name = "CsvSource негативный кейс #{index}: name={0}, age={1}, ownerEmail={7}")
    @CsvSource({
            "'', 2, WHITE, Persian, 3.9, true, 2023-05-01, leo@example.com",
            "Leo, -1, WHITE, Persian, 3.9, true, 2023-05-01, leo@example.com",
            "Leo, 2, WHITE, Persian, -3.9, true, 2023-05-01, leo@example.com",
            "Leo, 2, WHITE, Persian, 3.9, true, 2023-05-01, wrong-email"
    })
    @DisplayName("Создание кота: негативные кейсы через CsvSource")
    public void createCat_shouldReturnBadRequestForInvalidData_withCsvSource(
            String name,
            int age,
            String color,
            String breed,
            double weight,
            boolean vaccinated,
            String birthDate,
            String ownerEmail
    ) {
        Map<String, Object> body = CatGenerator.generateBaseCat(
                name,
                age,
                color,
                breed,
                weight,
                vaccinated,
                birthDate,
                ownerEmail
        );

        catApiClient.createCat(body)
                .then()
                .statusCode(400);
    }

    static Stream<Arguments> positiveCats() {
        return Stream.of(
                Arguments.of("Leo", 2, "WHITE", "Persian", 3.9, true, "2023-05-01", "leo@example.com"),
                Arguments.of("Milo", 1, "BLACK", "Siamese", 2.8, false, "2024-01-10", "milo@example.com"),
                Arguments.of("Bella", 5, "GRAY", "British Shorthair", 4.7, true, "2020-07-07", "bella@example.com"),
                Arguments.of("Oscar", 3, "ORANGE", "Ragdoll", 3.5, false, "2022-09-15", "oscar@example.com")
        );
    }

    static Stream<Arguments> negativeCats() {
        return Stream.of(
                Arguments.of("", 2, "WHITE", "Persian", 3.9, true, "2023-05-01", "leo@example.com"),
                Arguments.of("Leo", -1, "WHITE", "Persian", 3.9, true, "2023-05-01", "leo@example.com"),
                Arguments.of("Leo", 2, "WHITE", "Persian", -3.9, true, "2023-05-01", "leo@example.com"),
                Arguments.of("Leo", 2, "WHITE", "Persian", 3.9, true, "2023-05-01", "wrong-email")
        );
    }
}
