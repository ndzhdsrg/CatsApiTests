import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class GetCatsBasicTest {

    @Test
    public void getCatsPositiveTest() {
        given()
                .when()
                .get("http://localhost:8080/api/cats")

                .then()
                .statusCode(200);
    }
    @Test
    public void getCatsPositiveWithLogTest() {

        given()

                .when()
                .get("http://localhost:8080/api/cats")

                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void shouldReturnListOfCats() {

        given()
                .baseUri("http://localhost:8080")

                .when()
                .get("/api/cats")

                .then()
                .log().all()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void getCatsWrongEndpointTest() {
        given()
                .baseUri("http://localhost:8080")

                .when()
                .get("/api/ccats")

                .then()
                .statusCode(500);
    }

    @Test
    void getAllCats() {

        given()
                .baseUri("http://localhost:8080")

                .when()
                .get("/api/cats")

                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    void getCatById() {

        given()
                .baseUri("http://localhost:8080")

                .when()
                .get("/api/cats/5")

                .then()
                .statusCode(200)
                .body("id", equalTo(5));
    }

    @Test
    public void getCatsListResponse() {
        Response response =
                given()
                        .baseUri("http://localhost:8080")

                        .when()
                        .get("/api/cats")

                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        System.out.println(response.asString());
    }
}

