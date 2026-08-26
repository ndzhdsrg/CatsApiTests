package ui;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CatAnketPage {
    private SelenideElement addCatPage = $("[data-testid='add-cat-page']");

    private SelenideElement addCatTitle = $("[data-testid='add-cat-title']");

    private SelenideElement addCatForm = $("[data-testid='add-cat-form']");

    private SelenideElement catNameInput = $("[data-testid='cat-name-input']");

    private SelenideElement catAgeInput = $("[data-testid='cat-age-input']");

    private SelenideElement catColorSelect = $("[data-testid='cat-color-select']");

    private SelenideElement catBreedInput = $("[data-testid='cat-breed-input']");

    private SelenideElement catOwnerInput = $("[data-testid='cat-owner-input']");

    private SelenideElement catBirthDateInput = $("[data-testid='cat-birth-date-input']");

    private SelenideElement catWeightInput = $("[data-testid='cat-weight-input']");

    private SelenideElement catVaccinatedCheckbox = $("[data-testid='cat-vaccinated-checkbox']");

    private SelenideElement catOwnerEmailInput = $("[data-testid='cat-owner-email-input']");

    private SelenideElement saveCatButton = $("[data-testid='save-cat-button']");

    private SelenideElement successMessage = $("[data-testid='success-message']");

    private SelenideElement errorMessage = $("[data-testid='error-message']");

    private SelenideElement backToMainLink = $("[data-testid='back-to-main-link']");

    public CatAnketPage setCatName(String name) {

        catNameInput.setValue(name);

        return this;

    }

    public CatAnketPage setCatAge(String age) {

        catAgeInput.setValue(age);

        return this;

    }

    public CatAnketPage selectCatColor(String color) {

        catColorSelect.selectOption(color);

        return this;

    }

    public CatAnketPage setCatBreed(String breed) {

        catBreedInput.setValue(breed);

        return this;

    }

    public CatAnketPage setCatOwner(String owner) {

        catOwnerInput.setValue(owner);

        return this;

    }

    public CatAnketPage setCatBirthDate(String birthDate) {

        catBirthDateInput.setValue(birthDate);

        return this;

    }

    public CatAnketPage setCatWeight(String weight) {

        catWeightInput.setValue(weight);

        return this;

    }

    public CatAnketPage setCatVaccinated(boolean vaccinated) {

        if (catVaccinatedCheckbox.isSelected() != vaccinated) {

            catVaccinatedCheckbox.click();

        }

        return this;

    }

    public CatAnketPage setCatOwnerEmail(String email) {

        catOwnerEmailInput.setValue(email);

        return this;

    }

    public CatAnketPage clickSaveCat() {

        saveCatButton.click();

        return this;

    }

    public CatAnketPage clickBackToMain() {

        backToMainLink.click();

        return this;

    }

    public CatAnketPage addCat(

            String name,

            String age,

            String color,

            String breed,

            String owner,

            String birthDate,

            String weight,

            boolean vaccinated,

            String ownerEmail

    ) {

        setCatName(name);

        setCatAge(age);

        selectCatColor(color);

        setCatBreed(breed);

        setCatOwner(owner);

        setCatBirthDate(birthDate);

        setCatWeight(weight);

        setCatVaccinated(vaccinated);

        setCatOwnerEmail(ownerEmail);

        clickSaveCat();

        return this;

    }
}
