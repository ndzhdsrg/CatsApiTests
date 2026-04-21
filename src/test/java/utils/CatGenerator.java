package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CatGenerator {

    /**
     * Генерирует валидное тело запроса для создания кота.
     * Используется в тестах, чтобы не хардкодить данные.
     */
    public static Map<String, Object> generateCat() {
        Map<String, Object> body = new HashMap<>();

        String randomName = "Cat_" + UUID.randomUUID().toString().substring(0, 5);

        body.put("name", randomName);
        body.put("age", 2);
        body.put("color", "WHITE");
        body.put("breed", "Persian");
        body.put("weight", 3.5);
        body.put("vaccinated", false);
        body.put("birthDate", "2023-01-01");
        body.put("ownerEmail", randomName.toLowerCase() + "@example.com");

        return body;
    }

    /**
     * Генерирует тело запроса для создания кота с переданными параметрами.
     * Позволяет гибко задавать значения для тестов.
     */
    public static Map<String, Object> generateBaseCat(
            String name,
            int age,
            String color,
            String breed,
            double weight,
            boolean vaccinated,
            String birthDate,
            String ownerEmail
    ) {
        Map<String, Object> body = new HashMap<>();

        body.put("name", name);
        body.put("age", age);
        body.put("color", color);
        body.put("breed", breed);
        body.put("weight", weight);
        body.put("vaccinated", vaccinated);
        body.put("birthDate", birthDate);
        body.put("ownerEmail", ownerEmail);

        return body;
    }
}
