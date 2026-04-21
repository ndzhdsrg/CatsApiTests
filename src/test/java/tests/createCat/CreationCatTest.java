package tests.createCat;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreationCatTest {

    public static final String API_CATS = "/api/v1/cats";
    public static final String BASE_URL = "http://localhost:8081";

    // 1) Самый простой вариант: JSON как строка → ответ как строка
    @Test
    public void createCat_withRawJsonString() {
        String json = "{\n" +
                "  \"name\": \"Whiskers\",\n" +
                "  \"age\": 4,\n" +
                "  \"color\": \"TABBY\",\n" +
                "  \"breed\": \"Maine Coon\",\n" +
                "  \"weight\": 4.5,\n" +
                "  \"vaccinated\": true,\n" +
                "  \"birthDate\": \"2021-03-15\",\n" +
                "  \"ownerEmail\": \"owner@example.com\"\n" +
                "}";

        String response = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(json)
        .when()
                .post(API_CATS)
        .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .extract()
                .asString();

        System.out.println(response);
    }

    // 2) JSON как Map → ответ как строка
    @Test
    public void createCat_withMap() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Tom");
        body.put("age", 3);
        body.put("color", "BLACK");
        body.put("breed", "Siamese");
        body.put("weight", 3.2);
        body.put("vaccinated", true);
        body.put("birthDate", "2022-01-10");
        body.put("ownerEmail", "tom@example.com");

        String response = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(body)
        .when()
                .post(API_CATS)
        .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .extract()
                .asString();

        System.out.println(response);
    }

    // 3) JSON через многострочную строку (text block, Java 15+) → ответ как строка
    @Test
    public void createCat_withTextBlockJson() {
        String json = """
                {
                  "name": "Leo",
                  "age": 2,
                  "color": "WHITE",
                  "breed": "Persian",
                  "weight": 3.9,
                  "vaccinated": false,
                  "birthDate": "2023-05-01",
                  "ownerEmail": "leo@example.com"
                }
                """;

        String response = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(json)
        .when()
                .post(API_CATS)
        .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .extract()
                .asString();

        System.out.println(response);
    }

    // 4) Разные способы получения ответа и парсинга

    // 4.1 Получение Response и чтение полей через path()
    @Test
    public void createCat_parseWithResponsePath() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Bella");
        body.put("age", 5);
        body.put("color", "GRAY");
        body.put("breed", "British Shorthair");
        body.put("weight", 4.8);
        body.put("vaccinated", true);
        body.put("birthDate", "2020-07-07");
        body.put("ownerEmail", "bella@example.com");

        Response response = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(body)
        .when()
                .post(API_CATS)
        .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .extract()
                .response();

        Integer id = response.path("id");
        String name = response.path("name");

        assertThat(id, notNullValue());
        assertThat(name, equalTo("Bella"));
    }

    // 4.2 Парсинг через JsonPath из строки
    @Test
    public void createCat_parseWithJsonPath() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Max");
        body.put("age", 1);
        body.put("color", "ORANGE");
        body.put("breed", "Ragdoll");
        body.put("weight", 2.9);
        body.put("vaccinated", false);
        body.put("birthDate", "2024-02-02");
        body.put("ownerEmail", "max@example.com");

        String response = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(body)
        .when()
                .post(API_CATS)
        .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .extract()
                .asString();

        JsonPath jsonPath = new JsonPath(response);
        Integer id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");

        assertThat(id, notNullValue());
        assertThat(name, equalTo("Max"));
    }

    // 4.3 Извлечение конкретного поля напрямую
    @Test
    public void createCat_extractSingleField() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Oscar");
        body.put("age", 6);
        body.put("color", "BROWN");
        body.put("breed", "Bengal");
        body.put("weight", 5.1);
        body.put("vaccinated", true);
        body.put("birthDate", "2019-11-11");
        body.put("ownerEmail", "oscar@example.com");

        Integer id = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(body)
        .when()
                .post(API_CATS)
        .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .extract()
                .path("id");

        assertThat(id, notNullValue());
    }

    // 5) Проверка: после создания кот есть в списке по id
    @Test
    public void createCat_shouldAppearInListById() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", "CheckListCat");
        body.put("age", 2);
        body.put("color", "WHITE");
        body.put("breed", "Persian");
        body.put("weight", 3.3);
        body.put("vaccinated", true);
        body.put("birthDate", "2023-01-01");
        body.put("ownerEmail", "check@example.com");

        // создаем кота и получаем id
        Integer createdId = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(body)
                .when()
                .post(API_CATS)
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .extract()
                .path("id");

        assertThat(createdId, notNullValue());

        // проверяем, что этот id есть в списке котов
        given()
                .baseUri(BASE_URL)
                .when()
                .get(API_CATS)
                .then()
                .statusCode(200)
                .body("content.id", hasItem(createdId));
    }

}


