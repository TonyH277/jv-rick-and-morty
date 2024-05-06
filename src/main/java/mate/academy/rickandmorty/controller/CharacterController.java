package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty character management",
        description = "Endpoints for managing characters")
@RestController
@RequestMapping("characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get one character",
            description = "Get random character from Rick and Morty universe")
    @GetMapping
    public Character generate() {
        return characterService.generateRandomCharacter();
    }

    @Operation(summary = "Get list of characters by name contains 'row'",
            description = "Get list of character from Rick and Morty universe "
                    + "where character name like '%:row%'")
    @GetMapping("/byNameContains")
    public List<Character> findCharacterNameContains(@RequestParam String row) {
        return characterService.findCharacterNameContains(row);
    }

}
