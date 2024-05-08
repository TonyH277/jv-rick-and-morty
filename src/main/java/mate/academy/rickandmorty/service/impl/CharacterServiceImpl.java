package mate.academy.rickandmorty.service.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private Random random = new Random();

    @Override
    @Transactional
    public Character generateRandomCharacter() {
        long charactersAmount = characterRepository.count();
        long id = random.nextLong(charactersAmount);
        return characterRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Character not found with id: " + id));
    }

    @Override
    public List<Character> findCharacterNameContains(String row) {
        return characterRepository.findByNameContainsIgnoreCase(row);
    }
}
