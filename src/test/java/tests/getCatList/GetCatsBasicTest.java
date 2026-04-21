package tests.getCatList;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

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
                .body("content", hasSize(greaterThan(0)))
                .body("number", equalTo(0))
                .body("size", equalTo(10))
                .body("numberOfElements", equalTo(10))
                .body("totalElements", equalTo(10))
                .body("totalPages", equalTo(1))
                .body("first", equalTo(true))
                .body("last", equalTo(true))
                .body("empty", equalTo(false));
    }

    @Test
    public void shouldReturnPaginationMetadata() {

        given()
                .baseUri("http://localhost:8080")

                .when()
                .get("/api/cats")

                .then()
                .statusCode(200)
                .body("pageable.pageNumber", equalTo(0))
                .body("pageable.pageSize", equalTo(10))
                .body("pageable.paged", equalTo(true))
                .body("pageable.unpaged", equalTo(false))
                .body("pageable.offset", equalTo(0))
                .body("pageable.sort.sorted", equalTo(true))
                .body("pageable.sort.unsorted", equalTo(false))
                .body("pageable.sort.empty", equalTo(false));
    }

    @Test
    public void shouldReturnCatsWithRequiredFields() {

        given()
                .baseUri("http://localhost:8080")

                .when()
                .get("/api/cats")

                .then()
                .statusCode(200)
                .body("content", hasSize(10))
                .body("content.id", hasItems(3, 4, 5, 6, 7, 8, 9, 10, 11, 12))
                .body("content.name", hasItems("Luna", "Mittens", "Oliver", "Bella", "Leo", "Nala", "Max", "Cleo", "Anusha", "Anusha1"))
                .body("content.breed", hasItems("Siamese", "Maine Coon", "Ragdoll", "Persian", "Bengal", "British Shorthair"))
                .body("content.status", hasItems("AVAILABLE", "ADOPTED", "PENDING", "QUARANTINE"));
    }

    @Test
    public void shouldReturnCorrectFirstCat() {

        given()
                .baseUri("http://localhost:8080")

                .when()
                .get("/api/cats")

                .then()
                .log().body()
                .statusCode(200)
                .body("content[0].id", equalTo(3))
                .body("content[0].name", equalTo("Luna"))
                .body("content[0].age", equalTo(2))
                .body("content[0].breed", equalTo("Siamese"))
                .body("content[0].color", equalTo("Cream"))
                .body("content[0].weight", equalTo(3.8f))
                .body("content[0].status", equalTo("AVAILABLE"))
                .body("content[0].ownerName", equalTo(null))
                .body("content[0].createdAt", notNullValue())
                .body("content[0].updatedAt", notNullValue());
    }

    @Test
    public void shouldReturnCorrectCatByIndexTwo() {

        given()
                .baseUri("http://localhost:8080")

                .when()
                .get("/api/cats")

                .then()
                .statusCode(200)
                .body("content[2].id", equalTo(5))
                .body("content[2].name", equalTo("Oliver"))
                .body("content[2].age", equalTo(1))
                .body("content[2].breed", equalTo("Ragdoll"))
                .body("content[2].color", equalTo("White"))
                .body("content[2].weight", equalTo(3.0f))
                .body("content[2].status", equalTo("PENDING"))
                .body("content[2].ownerName", equalTo(null));
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
                .log().body()
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
