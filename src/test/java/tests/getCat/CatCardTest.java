package tests.getCat;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CatCardTest {

    public static final String API_CATS = "/api/v1/cats/";
    public static String baseUrl = "http://localhost:8081";

    @Test
    void getCatById_shouldReturnCorrectCat() {

        given()
                .baseUri(baseUrl)

                .when()
                .get(API_CATS + "5")

                .then()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(5))
                .body("name", equalTo("Oliver"))
                .body("age", equalTo(1))
                .body("breed", equalTo("Ragdoll"))
                .body("color", equalTo("White"))
                .body("weight", equalTo(3.0f))
                .body("status", equalTo("PENDING"))
                .body("ownerName", equalTo(null))
                .body("createdAt", notNullValue())
                .body("updatedAt", notNullValue());
    }

    @Test
    void getCatById_shouldReturnAllFieldsNotNullExceptOptional() {

        given()
                .baseUri(baseUrl)

                .when()
                .get(API_CATS + "5")

                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", notNullValue())
                .body("age", notNullValue())
                .body("breed", notNullValue())
                .body("color", notNullValue())
                .body("weight", notNullValue())
                .body("status", notNullValue())
                .body("createdAt", notNullValue())
                .body("updatedAt", notNullValue());
    }

    @Test
    void getCatById_shouldReturnCorrectDataTypes() {

        given()
                .baseUri(baseUrl)

                .when()
                .get(API_CATS + "5")

                .then()
                .statusCode(200)
                .body("id", equalTo(5))
                .body("age", equalTo(1));
    }

    @Test
    void getCatById_shouldReturn404ForNonExistingCat() {


        given()
                .baseUri(baseUrl)

                .when()
                .get(API_CATS + "999999")

                .then()
                .statusCode(404);
    }

    @Test
    void getCatById_shouldReturn400ForInvalidId() {

        given()
                .baseUri(baseUrl)

                .when()
                .get(API_CATS + "invalid")

                .then()
                .statusCode(400);
    }

    @Test
    void getCatById_shouldBeFast() {

        given()
                .baseUri(baseUrl)

                .when()
                .get(API_CATS + "5")

                .then()
                .statusCode(200)
                .time(org.hamcrest.Matchers.lessThan(2000L));
    }
    
}
