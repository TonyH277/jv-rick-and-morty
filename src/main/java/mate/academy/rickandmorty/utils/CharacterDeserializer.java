package mate.academy.rickandmorty.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import mate.academy.rickandmorty.model.Character;

public class CharacterDeserializer extends JsonDeserializer<Character> {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String STATUS = "status";
    private static final String GENDER = "gender";

    @Override
    public Character deserialize(JsonParser jsonParser,
                                 DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        JsonNode treeNode = jsonParser.getCodec().readTree(jsonParser);

        String id = treeNode.get(ID).asText();
        String name = treeNode.get(NAME).asText();
        String status = treeNode.get(STATUS).asText();
        String gender = treeNode.get(GENDER).asText();

        Character character = new Character();
        character.setExternalId(Long.valueOf(id));
        character.setName(name);
        character.setStatus(status);
        character.setGender(gender);

        return character;
    }
}
