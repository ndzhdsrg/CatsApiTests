package clients;

import config.BaseCatSpec;
import constants.Endpoints;
import io.restassured.response.Response;
import model.CatCreateRequestDto;
import model.CatUpdateRequestDto;
import models.response.CatResponseDto;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Клиент для работы с Cats API.
 *
 * Инкапсулирует HTTP-вызовы (Rest Assured) и предоставляет удобные методы
 * для тестов. Позволяет скрыть детали запроса (baseSpec, endpoint, pathParam)
 * и переиспользовать код, уменьшая дублирование в тестах.
 */
public class CatApiClient {
    /**
     * Создание кота (POST /api/v1/cats).
     * @param body тело запроса (Map, который будет сериализован в JSON)
     * @return Response от сервера
     */
    public Response createCatWithMap(Map<String, Object> body) {
        return given()
                .spec(BaseCatSpec.baseSpec())
                .body(body)
                .when()
                .post(Endpoints.CATS)
                .then()
                .extract()
                .response();
    }

    public CatResponseDto createCat(CatCreateRequestDto body) {
        return given()
                .spec(BaseCatSpec.baseSpec())
                .body(body)
                .when()
                .post(Endpoints.CATS)
                .then()
                .extract()
                .as(models.response.CatResponseDto.class);
    }

    /**
     * Получение кота по id (GET /api/v1/cats/{id}).
     * @param id идентификатор кота
     * @return Response от сервера
     */
    public Response getCatById(Integer id) {
        return given()
                .spec(BaseCatSpec.baseSpec())
                .pathParam("id", id)
                .when()
                .get(Endpoints.CAT_BY_ID)
                .then()
                .extract()
                .response();
    }

    /**
     * Получение списка котов (GET /api/v1/cats).
     * @return Response от сервера
     */
    public Response getAllCats() {
        return given()
                .spec(BaseCatSpec.baseSpec())
                .when()
                .get(Endpoints.CATS)
                .then()
                .extract()
                .response();
    }

    /**
     * Удаление кота (DELETE /api/v1/cats/{id}).
     */

    public Response deleteCat(int id) {
        return given()
                .spec(BaseCatSpec.baseSpec())
                .pathParam("id", id)
                .when()
                .delete(Endpoints.CAT_BY_ID)
                .then()
                .extract()
                .response();
    }

    /**
     * Частичное обновление кота (PATCH /api/v1/cats/{id}).
     *
     * Обновляет только переданные поля.
     *
     * @param id идентификатор кота
     * @param body DTO с полями для обновления
     * @return Response от сервера
     */
    public Response updateCat(int id, CatUpdateRequestDto body) {
        return given()
                .spec(BaseCatSpec.baseSpec())
                .pathParam("id", id)
                .body(body)
                .when()
                .patch(Endpoints.CAT_BY_ID)
                .then()
                .extract()
                .response();
    }

    /**
     * Вакцинация кота (PATCH /api/v1/cats/{id}/vaccinate).
     */
    public Response vaccinateCat(int id) {
        return given()
                .spec(BaseCatSpec.baseSpec())
                .pathParam("id", id)
                .when()
                .patch(Endpoints.CAT_BY_ID + "/vaccinate")
                .then()
                .extract()
                .response();
    }

    /**
     * Архивация кота (PATCH /api/v1/cats/{id}/archive).
     */
    public Response archiveCat(int id) {
        return given()
                .spec(BaseCatSpec.baseSpec())
                .pathParam("id", id)
                .when()
                .patch(Endpoints.CAT_BY_ID + "/archive")
                .then()
                .extract()
                .response();
    }
}
