package tests.createCat;

import clients.CatApiClient;
import model.CatCreateRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CatCreationTest {

    private final CatApiClient client = new CatApiClient();

    @Test
    void shouldCreateCatSuccessfully() {
        // 1. Подготовка данных (DTO)
        CatCreateRequestDto request = new CatCreateRequestDto(
                "Whiskers",
                3,
                "TABBY",
                "Maine Coon",
                4.5,
                true,
                "2021-03-15",
                "owner@example.com"
        );

        // 2. Отправка запроса
        models.response.CatResponseDto response = client.createCat(request);


        // 5. Проверки тела ответа
        assertNotNull(response.getId());
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getAge(), response.getAge());
        assertEquals(request.getColor(), response.getColor());
        assertEquals(request.getBreed(), response.getBreed());
        assertEquals(request.getWeight(), response.getWeight());
        assertEquals(request.isVaccinated(), response.isVaccinated());
        assertEquals(request.getBirthDate(), response.getBirthDate());
        assertEquals(request.getOwnerEmail(), response.getOwnerEmail());
    }
}