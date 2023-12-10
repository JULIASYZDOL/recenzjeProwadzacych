package pl.ocenProfesora.recenzjeProwadzacych.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
@Setter
@Getter
@SuperBuilder
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Komentarz {
    @Id
    @GeneratedValue
    private int id;
    private String tytul;
    private String tresc;
    private String pseudonim;
    private int idProwadzacego;
}
