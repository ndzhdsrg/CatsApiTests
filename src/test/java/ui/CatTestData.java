package ui;

public enum CatTestData {
    DEFAULT(

            "Мурзик",
            "3",
            "BLACK",
            "Британец",
            "Надя",
            "2023-05-10",
            "4.5",
            true,
            "test@test.ru"
    );

    private final String name;

    private final String age;

    private final String color;

    private final String breed;

    private final String owner;

    private final String birthDate;

    private final String weight;

    private final boolean vaccinated;

    private final String ownerEmail;

    CatTestData(

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

        this.name = name;

        this.age = age;

        this.color = color;

        this.breed = breed;

        this.owner = owner;

        this.birthDate = birthDate;

        this.weight = weight;

        this.vaccinated = vaccinated;

        this.ownerEmail = ownerEmail;

    }

    public String getName() {

        return name;

    }

    public String getAge() {

        return age;

    }

    public String getColor() {

        return color;

    }

    public String getBreed() {

        return breed;

    }

    public String getOwner() {

        return owner;

    }

    public String getBirthDate() {

        return birthDate;

    }

    public String getWeight() {

        return weight;

    }

    public boolean isVaccinated() {

        return vaccinated;

    }

    public String getOwnerEmail() {

        return ownerEmail;

    }
}
