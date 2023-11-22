package pl.ocenProfesora.recenzjeProwadzacych.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OcenaJakosc {
    private int id;
    private int idProwadzacego;
    private int wartosc;
}
