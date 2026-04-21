package tests.e2e;

import clients.CatApiClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.CatGenerator;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class VaccinatedCatTest {

    private final CatApiClient catApiClient = new CatApiClient();
    private Integer createdCatId = null;

    @BeforeEach
    void setUp() {
        // создаем кота
        Map<String, Object> body = CatGenerator.generateCat();

        Response response = catApiClient.createCat(body)
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .extract()
                .response();

        createdCatId = response.jsonPath().getInt("id");
        assertThat(createdCatId, notNullValue());

        // проверяем, что кот создался корректно
        response.then()
                .body("name", notNullValue())
                .body("age", greaterThanOrEqualTo(0))
                .body("color", notNullValue())
                .body("breed", notNullValue())
                .body("weight", greaterThan(0f))
                .body("vaccinated", notNullValue());
    }

    @Test
    @DisplayName("Вакцинация кота должна менять статус vaccinated на true")
    void vaccinateCat_shouldSetVaccinatedTrue() {

        // вакцинируем кота
        Response vaccinateResponse = catApiClient.vaccinateCat(createdCatId)
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(204)))
                .extract()
                .response();

        // после вакцинации получаем кота и проверяем поле vaccinated
        Response getResponse = catApiClient.getCatById(createdCatId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        Boolean vaccinated = getResponse.path("vaccinated");
        assertThat(vaccinated, equalTo(true));
    }

    @AfterEach
    void tearDown() {
        if (createdCatId != null) {
            catApiClient.deleteCat(createdCatId)
                    .then()
                    .statusCode(anyOf(equalTo(200), equalTo(204)));
        }
    }
}
