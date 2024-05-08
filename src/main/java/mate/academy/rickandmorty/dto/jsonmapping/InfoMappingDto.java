package mate.academy.rickandmorty.dto.jsonmapping;

import lombok.Data;

@Data
public class InfoMappingDto {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
