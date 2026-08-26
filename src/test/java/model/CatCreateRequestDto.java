package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
@NoArgsConstructor
public class CatCreateRequestDto {

    private String name;
    private int age;
    private String color;
    private String breed;
    private double weight;
    private boolean vaccinated;
    private String birthDate;
    private String ownerEmail;


}