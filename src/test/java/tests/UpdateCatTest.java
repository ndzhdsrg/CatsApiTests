package tests;

import clients.CatApiClient;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import model.CatCreateRequestDto;
import model.CatUpdateRequestDto;
import models.response.CatResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CatGenerator;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.DisplayName;

public class UpdateCatTest {

    private final CatApiClient catApiClient = new CatApiClient();
    private Integer catId;

    @BeforeEach
    public void createCat() {
        CatCreateRequestDto createCat = CatGenerator.defaultCreateCat();

        CatResponseDto createResponse = catApiClient.createCat(createCat);

        catId = createResponse.getId().intValue();

        assertNotNull(catId);
    }

    @AfterEach
    public void deleteCat() {
            catApiClient.deleteCat(catId.intValue())
                    .then()
                    .statusCode(204);
    }

    @DisplayName("Успешное обновление имени кота")
    @Test
    public void updateValidNameTest() {
        String name = "Luna";

        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .name(name)
                .build();

        Response updateResponse = updateCatAndCatResponse(updateCat);

        CatResponseDto updatedCat = updateResponse.as(CatResponseDto.class);

        assertEquals(name, updatedCat.getName());
        assertEquals(catId.longValue(), updatedCat.getId());

        assertEquals(3, updatedCat.getAge());
        assertEquals("BLACK", updatedCat.getColor());
        assertEquals("Bombay", updatedCat.getBreed());
        assertEquals(4.2, updatedCat.getWeight());
        assertFalse(updatedCat.isVaccinated());
        assertEquals("2021-04-12", updatedCat.getBirthDate());
        assertEquals("before.update@example.com", updatedCat.getOwnerEmail());
    }

    private Response updateCatAndCatResponse(CatUpdateRequestDto updateCat) {
        Response updateResponse = catApiClient.updateCat(catId, updateCat);
        updateResponse.then().statusCode(200);
        return updateResponse;
    }

    private CatResponseDto updateCatAndDtoResponse(CatUpdateRequestDto updateCat) {
        Response updateResponse = catApiClient.updateCat(catId, updateCat);
        updateResponse.then().statusCode(200);
        return updateResponse.as(CatResponseDto.class);
    }


    @DisplayName("Успешное обновление возраста кота")
    @Test
    public void updateValidAgeTest() {
        Integer age = 7;

        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .age(age)
                .build();

        Response updateResponse = updateCatAndCatResponse(updateCat);

        CatResponseDto updatedCat = updateResponse.as(CatResponseDto.class);

        assertEquals(age, updatedCat.getAge());
        assertEquals(catId.longValue(), updatedCat.getId());

        assertEquals("BeforeUpdateCat", updatedCat.getName());
        assertEquals("BLACK", updatedCat.getColor());
        assertEquals("Bombay", updatedCat.getBreed());
        assertEquals(4.2, updatedCat.getWeight());
        assertFalse(updatedCat.isVaccinated());
        assertEquals("2021-04-12", updatedCat.getBirthDate());
        assertEquals("before.update@example.com", updatedCat.getOwnerEmail());
    }

    @DisplayName("Успешное обновление цвета кота")
    @Test
    public void updateValidColorTest() {
        String color = "WHITE";

        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .color(color)
                .build();

        Response updateResponse = updateCatAndCatResponse(updateCat);

        CatResponseDto updatedCat = updateResponse.as(CatResponseDto.class);

        assertEquals(color, updatedCat.getColor());
        assertEquals(catId.longValue(), updatedCat.getId());

        assertEquals("BeforeUpdateCat", updatedCat.getName());
        assertEquals(3, updatedCat.getAge());
        assertEquals("Bombay", updatedCat.getBreed());
        assertEquals(4.2, updatedCat.getWeight());
        assertFalse(updatedCat.isVaccinated());
        assertEquals("2021-04-12", updatedCat.getBirthDate());
        assertEquals("before.update@example.com", updatedCat.getOwnerEmail());
    }

    @DisplayName("Успешное обновление породы кота")
    @Test
    public void updateValidBreedTest() {
        String breed = "Maine Coon";

        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .breed(breed)
                .build();

        Response updateResponse = updateCatAndCatResponse(updateCat);

        CatResponseDto updatedCat = updateResponse.as(CatResponseDto.class);

        assertEquals(breed, updatedCat.getBreed());
        assertEquals(catId.longValue(), updatedCat.getId());

        assertEquals("BeforeUpdateCat", updatedCat.getName());
        assertEquals(3, updatedCat.getAge());
        assertEquals("BLACK", updatedCat.getColor());
        assertEquals(4.2, updatedCat.getWeight());
        assertFalse(updatedCat.isVaccinated());
        assertEquals("2021-04-12", updatedCat.getBirthDate());
        assertEquals("before.update@example.com", updatedCat.getOwnerEmail());
    }

    @DisplayName("Успешное обновление веса кота")
    @Test
    public void updateValidWeightTest() {
        Double weight = 6.5;

        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .weight(weight)
                .build();

        Response updateResponse = updateCatAndCatResponse(updateCat);

        CatResponseDto updatedCat = updateResponse.as(CatResponseDto.class);

        assertEquals(weight, updatedCat.getWeight());
        assertEquals(catId.longValue(), updatedCat.getId());

        assertEquals("BeforeUpdateCat", updatedCat.getName());
        assertEquals(3, updatedCat.getAge());
        assertEquals("BLACK", updatedCat.getColor());
        assertEquals("Bombay", updatedCat.getBreed());
        assertFalse(updatedCat.isVaccinated());
        assertEquals("2021-04-12", updatedCat.getBirthDate());
        assertEquals("before.update@example.com", updatedCat.getOwnerEmail());
    }

    @DisplayName("Успешное обновление статуса вакцинации кота")
    @Test
    public void updateValidVaccinatedTest() {
        Boolean vaccinated = true;

        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .vaccinated(vaccinated)
                .build();

        Response updateResponse = updateCatAndCatResponse(updateCat);

        CatResponseDto updatedCat = updateResponse.as(CatResponseDto.class);

        assertTrue(updatedCat.isVaccinated());
        assertEquals(catId.longValue(), updatedCat.getId());

        assertEquals("BeforeUpdateCat", updatedCat.getName());
        assertEquals(3, updatedCat.getAge());
        assertEquals("BLACK", updatedCat.getColor());
        assertEquals("Bombay", updatedCat.getBreed());
        assertEquals(4.2, updatedCat.getWeight());
        assertEquals("2021-04-12", updatedCat.getBirthDate());
        assertEquals("before.update@example.com", updatedCat.getOwnerEmail());
    }

    @DisplayName("Успешное обновление даты рождения кота")
    @Test
    public void updateValidBirthDateTest() {
        String birthDate = "2020-02-29";

        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .birthDate(birthDate)
                .build();

        Response updateResponse = updateCatAndCatResponse(updateCat);

        CatResponseDto updatedCat = updateResponse.as(CatResponseDto.class);

        assertEquals(birthDate, updatedCat.getBirthDate());
        assertEquals(catId.longValue(), updatedCat.getId());

        assertEquals("BeforeUpdateCat", updatedCat.getName());
        assertEquals(3, updatedCat.getAge());
        assertEquals("BLACK", updatedCat.getColor());
        assertEquals("Bombay", updatedCat.getBreed());
        assertEquals(4.2, updatedCat.getWeight());
        assertFalse(updatedCat.isVaccinated());
        assertEquals("before.update@example.com", updatedCat.getOwnerEmail());
    }

    @DisplayName("Успешное обновление email владельца кота")
    @Test
    public void updateValidOwnerEmailTest() {
        String ownerEmail = "new.owner@example.com";

        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .ownerEmail(ownerEmail)
                .build();

        Response updateResponse = updateCatAndCatResponse(updateCat);

        CatResponseDto updatedCat = updateResponse.as(CatResponseDto.class);

        assertEquals(ownerEmail, updatedCat.getOwnerEmail());
        assertEquals(catId.longValue(), updatedCat.getId());

        assertEquals("BeforeUpdateCat", updatedCat.getName());
        assertEquals(3, updatedCat.getAge());
        assertEquals("BLACK", updatedCat.getColor());
        assertEquals("Bombay", updatedCat.getBreed());
        assertEquals(4.2, updatedCat.getWeight());
        assertFalse(updatedCat.isVaccinated());
        assertEquals("2021-04-12", updatedCat.getBirthDate());
    }

    @DisplayName("Ошибка при обновлении кота с пустым именем")
    @Test
    public void updateEmptyNameShouldReturnBadRequestTest() {
        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .name("")
                .build();

        Response updateResponse = catApiClient.updateCat(catId, updateCat);
        updateResponse.then().statusCode(400);
    }

    @DisplayName("Ошибка при обновлении кота с отрицательным возрастом")
    @Test
    public void updateNegativeAgeShouldReturnBadRequestTest() {
        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .age(-1)
                .build();

        Response updateResponse = catApiClient.updateCat(catId, updateCat);
        updateResponse.then().statusCode(400);
    }

    @DisplayName("Ошибка при обновлении кота с возрастом 0")
    @Test
    public void updateZeroAgeShouldReturnBadRequestTest() {
        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .age(0)
                .build();

        Response updateResponse = catApiClient.updateCat(catId, updateCat);
        updateResponse.then().statusCode(400);
    }

    @DisplayName("Ошибка при обновлении кота с невалидным цветом")
    @Test
    public void updateInvalidColorShouldReturnBadRequestTest() {
        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .color("PURPLE")
                .build();

        Response updateResponse = catApiClient.updateCat(catId, updateCat);
        updateResponse.then().statusCode(400);
    }

    @DisplayName("Ошибка при обновлении кота с пустой породой")
    @Test
    public void updateEmptyBreedShouldReturnBadRequestTest() {
        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .breed("")
                .build();

        Response updateResponse = catApiClient.updateCat(catId, updateCat);
        updateResponse.then().statusCode(400);
    }

    @DisplayName("Ошибка при обновлении кота с отрицательным весом")
    @Test
    public void updateNegativeWeightShouldReturnBadRequestTest() {
        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .weight(-2.5)
                .build();

        Response updateResponse = catApiClient.updateCat(catId, updateCat);
        updateResponse.then().statusCode(400);
    }

    @DisplayName("Ошибка при обновлении кота с весом 0")
    @Test
    public void updateZeroWeightShouldReturnBadRequestTest() {
        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .weight(0.0)
                .build();

        Response updateResponse = catApiClient.updateCat(catId, updateCat);
        updateResponse.then().statusCode(400);
    }

    @DisplayName("Ошибка при обновлении кота с невалидной датой рождения")
    @Test
    public void updateInvalidBirthDateFormatShouldReturnBadRequestTest() {
        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .birthDate("12-04-2021")
                .build();

        Response updateResponse = catApiClient.updateCat(catId, updateCat);
        updateResponse.then().statusCode(400);
    }

    @DisplayName("Ошибка при обновлении кота с невалидным email владельца")
    @Test
    public void updateInvalidOwnerEmailShouldReturnBadRequestTest() {
        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .ownerEmail("bad-email")
                .build();

        Response updateResponse = catApiClient.updateCat(catId, updateCat);
        updateResponse.then().statusCode(400);
    }

    @DisplayName("Ошибка при обновлении несуществующего кота")
    @Test
    public void updateCatByNotExistingIdShouldReturnNotFoundTest() {
        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .name("Ghost")
                .build();

        Response updateResponse = catApiClient.updateCat(999999, updateCat);
        updateResponse.then().statusCode(404);
    }

    @DisplayName("Успешное обновление кота: работа только с DTO")
    @Test
    public void updateCatWithDtoOnlyTest() {
        String name = "DtoOnlyCat";

        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .name(name)
                .build();

        CatResponseDto updatedCat = updateCatAndDtoResponse(updateCat);

        assertEquals(name, updatedCat.getName());
        assertEquals(catId.longValue(), updatedCat.getId());
    }

    @DisplayName("Успешное обновление кота: работа с Response и DTO")
    @Test
    public void updateCatWithResponseAndDtoTest() {
        String color = "WHITE";

        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .color(color)
                .build();

        Response response = updateCatAndCatResponse(updateCat);
        CatResponseDto updatedCat = response.as(CatResponseDto.class);

        assertEquals(color, updatedCat.getColor());
        assertEquals(catId.longValue(), updatedCat.getId());
    }

    @DisplayName("Успешное обновление кота: работа с ValidatableResponse")
    @Test
    public void updateCatWithValidatableResponseTest() {
        String breed = "Maine Coon";

        CatUpdateRequestDto updateCat = CatUpdateRequestDto.builder()
                .breed(breed)
                .build();

        ValidatableResponse validatableResponse = catApiClient.updateCat(catId, updateCat)
                .then();

        validatableResponse
                .statusCode(200)
                .body("id", equalTo(catId))
                .body("breed", equalTo(breed));
    }

}