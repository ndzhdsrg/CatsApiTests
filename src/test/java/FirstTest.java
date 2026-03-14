import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class FirstTest {

    @Test
    public void firstTest(){
        RestAssured
                .given()
                .baseUri("http://localhost:8080")

                .when()
                .get("/api/cats")

                .then()
                .statusCode(200);
    }
}
