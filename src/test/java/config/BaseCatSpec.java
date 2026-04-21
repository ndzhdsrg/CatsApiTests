package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * Класс с базовой конфигурацией для всех API-запросов.
 *
 * Содержит общие настройки Rest Assured, которые будут использоваться
 * во всех тестах (base URL, content-type и т.д.), чтобы не дублировать их в каждом тесте.
 */
public class BaseCatSpec {
        // Базовый URL приложения (точка входа для всех API-запросов)
        public static final String BASE_URL = "http://localhost:8081";

        /**
         * Базовая спецификация запроса.
         *
         * Используется для того, чтобы во всех тестах автоматически применялись
         * общие настройки запроса:
         * - baseUri (адрес сервера)
         * - Content-Type (тип контента — JSON)
         *
         * Это позволяет писать тесты короче и избегать дублирования кода.
         */
        public static RequestSpecification baseSpec() {
            return new RequestSpecBuilder()
                    .setBaseUri(BASE_URL)
                    .setContentType("application/json")
                    .build();
        }

}
