package mate.academy.rickandmorty.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class DataFetcher {

    private static final String CHARACTER_URL = "https://rickandmortyapi.com/api/character";
    private static final String ROOT_INFO = "info";
    private static final String ROOT_RESULTS = "results";
    private static final String ROOT_NEXT = "next";
    private static final String NULL_VALUE = "null";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;

    @PostConstruct
    public void fetchData() throws JsonProcessingException {
        String url = CHARACTER_URL;

        while (url != null) {
            String jsonResponse = restTemplate.getForObject(url, String.class);
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            List<Character> pageCharacters = deserializeCharacters(rootNode);
            characterRepository.saveAll(pageCharacters);
            url = getNextPageUrl(rootNode);
        }
    }

    private String getNextPageUrl(JsonNode rootNode) {
        JsonNode infoNode = rootNode.get(ROOT_INFO);
        if (infoNode != null) {
            JsonNode nextNode = infoNode.get(ROOT_NEXT);
            if (nextNode.asText() != NULL_VALUE) {
                return nextNode.asText();
            }
        }
        return null;
    }

    private List<Character> deserializeCharacters(JsonNode rootNode)
            throws JsonProcessingException {
        JsonNode resultNode = rootNode.get(ROOT_RESULTS);
        return objectMapper.treeToValue(resultNode,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Character.class));

    }
}

