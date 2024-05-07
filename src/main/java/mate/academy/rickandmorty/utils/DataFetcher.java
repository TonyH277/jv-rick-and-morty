package mate.academy.rickandmorty.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class DataFetcher {
    private static final String ROOT_INFO = "info";
    private static final String ROOT_RESULTS = "results";
    private static final String ROOT_NEXT = "next";
    private static final String NULL_VALUE = "null";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;
    @Value("${characters.url}")
    private String characterUrl;

    public void populateCharactersDB() {
        String url = characterUrl;

        while (url != null) {
            String jsonResponse = restTemplate.getForObject(url, String.class);
            try {
                JsonNode rootNode = objectMapper.readTree(jsonResponse);
                List<Character> pageCharacters = deserializeCharacters(rootNode);
                characterRepository.saveAll(pageCharacters);
                url = getNextPageUrl(rootNode);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Can't fetch all characters");
            }
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
