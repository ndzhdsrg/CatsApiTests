package ui;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class BaseCatTest {

    @BeforeEach
    public void openBasePage(){
        open(Urls.BASE_URL);
    }

    @AfterEach
    public void tearDown(){
        Selenide.cookies().clear();
        Selenide.closeWebDriver();

    }
}
