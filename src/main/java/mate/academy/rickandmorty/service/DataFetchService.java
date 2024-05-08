package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.jsonmapping.CharacterMappingDto;

public interface DataFetchService {
    List<CharacterMappingDto> fetch();
}
