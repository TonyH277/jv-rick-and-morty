package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final int CHARACTERS_AMOUNT = 826;
    private final CharacterRepository characterRepository;

    @Override
    public Character generateRandomCharacter() {
        return Optional.of(characterRepository.findById(new Random().nextLong(CHARACTERS_AMOUNT)))
                .get()
                .get();
    }

    @Override
    public List<Character> findCharacterNameContains(String row) {
        return characterRepository.findByNameContainsIgnoreCase(row);
    }
}
