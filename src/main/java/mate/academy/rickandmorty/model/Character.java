package mate.academy.rickandmorty.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import mate.academy.rickandmorty.utils.CharacterDeserializer;

@Entity
@Getter
@Setter
@Table(name = "characters")
@JsonDeserialize(using = CharacterDeserializer.class)
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "external_id")
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}
