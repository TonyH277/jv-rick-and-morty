package mate.academy.rickandmorty.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.impl.DataFetchServiceImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final DataFetchServiceImpl dataFetcher;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @Override
    public void run(ApplicationArguments args) {
        List<Character> characters = dataFetcher.fetch()
                .stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characters);
    }
}
