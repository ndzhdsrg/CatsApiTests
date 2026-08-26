package ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ui.CatAssertions.assertCatCrested;

@DisplayName("Проверка добавления кота")
public class AddCatTest extends BaseCatTest {
    private CatMainPage catMainPage = new CatMainPage();
    private CatAnketPage catAnketPage = new CatAnketPage();

    @Test
    public void addCatPositiveTest() {
        catMainPage.clickAddButton();
        CatTestData cat = CatTestData.DEFAULT;

        catAnketPage.addCat(
                cat.getName(),
                cat.getAge(),
                cat.getColor(),
                cat.getBreed(),
                cat.getOwner(),
                cat.getBirthDate(),
                cat.getWeight(),
                cat.isVaccinated(),
                cat.getOwnerEmail());
        assertCatCrested();
    }
}
