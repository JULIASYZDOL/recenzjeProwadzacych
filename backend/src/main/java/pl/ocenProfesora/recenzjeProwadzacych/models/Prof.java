package pl.ocenProfesora.recenzjeProwadzacych.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prof {
    private int id;
    private String nazwa;
    private int idUczelni;
}
