package pl.ocenProfesora.recenzjeProwadzacych.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Komentarz {
    private int id;
    private String tytul;
    private String tresc;
    private String pseudonim;
    private int idProwadzacego;
}
