package pl.ocenProfesora.recenzjeProwadzacych.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.ocenProfesora.recenzjeProwadzacych.models.OcenaJakosc;

import java.util.List;

@Repository
public class OcenaJakRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<OcenaJakosc> getAll(){
        return jdbcTemplate.query("SELECT id, idProwadzacego, wartosc FROM ocenyJakosc", BeanPropertyRowMapper.newInstance(OcenaJakosc.class));
    }

    public OcenaJakosc getById(int id){
        return jdbcTemplate.queryForObject("SELECT id, idProwadzacego, wartosc FROM ocenyJakosc WHERE " + "id = ?", BeanPropertyRowMapper.newInstance(OcenaJakosc.class), id);
    }

    public int save(List<OcenaJakosc> oceny) {
        oceny.forEach(ocena-> jdbcTemplate.update("INSERT INTO ocenyJakosc(id, idProwadzacego, wartosc) VALUES(?, ?, ?)", ocena.getId(), ocena.getIdProwadzacego(), ocena.getWartosc()));

        return 1;
    }

    public int update(OcenaJakosc ocena){
        return jdbcTemplate.update("UPDATE ocenyJakosc SET(idProwadzacego=?, wartosc=?) WHERE id=?", ocena.getIdProwadzacego(), ocena.getWartosc(), ocena.getId());
    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM ocenyJakosc WHERE id=?", id);
    }
}
