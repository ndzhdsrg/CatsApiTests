package models.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
@NoArgsConstructor
public class CatResponseDto {

    private Long id;

    private String name;
    private int age;
    private String color;
    private String breed;
    private double weight;
    private boolean vaccinated;
    private String birthDate;
    private String ownerEmail;

    private String status;
    private String createdAt;
    private String updatedAt;


}