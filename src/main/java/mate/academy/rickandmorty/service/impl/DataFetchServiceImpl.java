package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.FetchedDataDto;
import mate.academy.rickandmorty.dto.jsonmapping.CharacterMappingDto;
import mate.academy.rickandmorty.service.DataFetchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class DataFetchServiceImpl implements DataFetchService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    @Value("${characters.url}")
    private String characterUrl;
    private final List<CharacterMappingDto> characterDtos;

    @Override
    public List<CharacterMappingDto> fetch() {
        String url = characterUrl;

        while (url != null) {
            String jsonResponse = restTemplate.getForObject(url, String.class);
            try {
                FetchedDataDto fetchedDataDto = objectMapper
                        .readValue(jsonResponse, FetchedDataDto.class);
                characterDtos.addAll(fetchedDataDto.getResults());
                url = fetchedDataDto.getInfo().getNext();
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Can't fetch all characters");
            }
        }

        return characterDtos;
    }
}
