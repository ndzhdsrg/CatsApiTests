package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ExampleTest {
    @Test
    public void exampleTest(){
        //BDD-цепочка given-when-then
        given()  //Дано (все, что нужно для выполнения запроса и все, что мы передаем в запрос)
                .header("Content-Type", "application/json") //пример - headers
                .when() //тогда выполни действие.
                .get("http://testUrl.com") //действие: непосредственно выполнение запроса, в данном случае get
                .then() //тогда выполни проверки
                .statusCode(200); //проверь статус-код
    }

    @Test
    public void exampleTestNotBdd(){
        given() //Нет when, короче, но читается похуже
                .header("Content-Type", "application/json")
                .get("http://testUrl.com")
                .then()
                .statusCode(200);
    }
}
