package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;
import mate.academy.rickandmorty.dto.jsonmapping.CharacterMappingDto;
import mate.academy.rickandmorty.dto.jsonmapping.InfoMappingDto;

@Data
public class FetchedDataDto {
    private InfoMappingDto info;
    private List<CharacterMappingDto> results;
}
