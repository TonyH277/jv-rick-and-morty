package mate.academy.rickandmorty.dto.jsonmapping;

import lombok.Data;

@Data
public class CharacterMappingDto {
    private int id;
    private String name;
    private String status;
    private String gender;
}
