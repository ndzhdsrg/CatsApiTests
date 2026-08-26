package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
@NoArgsConstructor
public class CatUpdateRequestDto {

    private String name;
    private Integer age;
    private String color;
    private String breed;
    private Double weight;
    private Boolean vaccinated;
    private String birthDate;
    private String ownerEmail;


}