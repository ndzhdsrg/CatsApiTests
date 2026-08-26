package ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CatMainPage {
    private SelenideElement addCatButton = $("[data-testid='add-cat-button']");

    public void clickAddButton(){
        addCatButton.should(Condition.visible).click();
    }
}
