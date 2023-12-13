package pl.ocenProfesora.recenzjeProwadzacych.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.ocenProfesora.recenzjeProwadzacych.models.OcenaTrudnosc;

import java.util.List;
@Repository
public class OcenaTruRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<OcenaTrudnosc> getAll(){
        return jdbcTemplate.query("SELECT id, idProwadzacego, wartosc FROM ocenyTrudnosc", BeanPropertyRowMapper.newInstance(OcenaTrudnosc.class));
    }

    public OcenaTrudnosc getById(int id){
        return jdbcTemplate.queryForObject("SELECT id, idProwadzacego, wartosc FROM ocenyTrudnosc WHERE " + "id = ?", BeanPropertyRowMapper.newInstance(OcenaTrudnosc.class), id);
    }

    public Double getMeanByIdProw(int idProw){
        return jdbcTemplate.queryForObject("SELECT AVG(wartosc) FROM ocenyTrudnosc WHERE " + "idProwadzacego = ?", Double.class, idProw);
    }

    public int save(List<OcenaTrudnosc> oceny) {
        oceny.forEach(ocena-> jdbcTemplate.update("INSERT INTO ocenyTrudnosc(id, idProwadzacego, wartosc) VALUES(?, ?, ?)", ocena.getId(), ocena.getIdProwadzacego(), ocena.getWartosc()));

        return 1;
    }

    public int update(OcenaTrudnosc ocena){
        return jdbcTemplate.update("UPDATE ocenyTrudnosc SET(idProwadzacego=?, wartosc=?) WHERE id=?", ocena.getIdProwadzacego(), ocena.getWartosc(), ocena.getId());
    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM ocenyTrudnosc WHERE id=?", id);
    }
}
