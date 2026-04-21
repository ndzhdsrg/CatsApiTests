package tests.createCat;

import clients.CatApiClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import utils.CatGenerator;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreationCatRefactorTest {

    private final CatApiClient catApiClient = new CatApiClient();
    private Integer lastCreatedId;


    @AfterEach
    public void verifyCatExistsInList() {
        if (lastCreatedId != null) {
            catApiClient.getAllCats()
                    .then()
                    .statusCode(200)
                    .body("content.id", hasItem(lastCreatedId));
        }
    }

    // 1) Самый простой вариант: JSON как строка → ответ как строка
    @Test
    public void createCat_withRawJsonString() {
        String response = catApiClient.createCat(CatGenerator.generateCat())
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .extract()
                .asString();

        System.out.println(response);
    }

    // 2) JSON как Map → ответ как строка
    @Test
    public void createCat_withMap() {
        Map<String, Object> body = CatGenerator.generateCat();

        String response = catApiClient.createCat(body)
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .extract()
                .asString();

        System.out.println(response);
    }

    // 3) JSON через многострочную строку (text block, Java 15+) → ответ как строка
    @Test
    public void createCat_withTextBlockJson() {

        String response = catApiClient.createCat(CatGenerator.generateCat())
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
        Map<String, Object> body = CatGenerator.generateCat();
        body.put("name", "Bella");

        Response response = catApiClient.createCat(body)
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
        Map<String, Object> body = CatGenerator.generateCat();
        body.put("name", "Max");

        String response = catApiClient.createCat(body)
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
        Map<String, Object> body = CatGenerator.generateCat();
        body.put("name", "Oscar");

        Integer id = catApiClient.createCat(body)
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .extract()
                .path("id");

        assertThat(id, notNullValue());
    }

    // 5) Проверка: после создания кот есть в списке по id
    @Test
    public void createCat_shouldAppearInListById() {
        Map<String, Object> body = CatGenerator.generateCat();

        lastCreatedId = catApiClient.createCat(body)
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .extract()
                .path("id");

        assertThat(lastCreatedId, notNullValue());
    }

}
