package pl.ocenProfesora.recenzjeProwadzacych.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.ocenProfesora.recenzjeProwadzacych.models.Prof;

import java.util.List;

@Repository
public class ProfRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Prof> getAll(){
        return jdbcTemplate.query("SELECT id, nazwa, idUczelni FROM prowadzacy", BeanPropertyRowMapper.newInstance(Prof.class));
    }

    public Prof getById(int id){
        return jdbcTemplate.queryForObject("SELECT id, nazwa, idUczelni FROM prowadzacy WHERE " + "id = ?", BeanPropertyRowMapper.newInstance(Prof.class), id);
    }

    public Integer getByName(String name){
        return jdbcTemplate.queryForObject("SELECT id FROM prowadzacy WHERE " + "nazwa = ?", Integer.class, name);
    }

    public List<Prof> getAllById(int id){
        return jdbcTemplate.query("SELECT id, nazwa, idUczelni FROM prowadzacy WHERE " + "id = ?", BeanPropertyRowMapper.newInstance(Prof.class), id);
    }

    public List<String> geAllByIdUczelni(int idUczelni){
        return jdbcTemplate.queryForList("SELECT nazwa FROM prowadzacy WHERE idUczelni = ?", String.class, idUczelni);
    }

    public int save(List<Prof> prowadzacy) {
        prowadzacy.forEach(prof -> jdbcTemplate.update("INSERT INTO prowadzacy(id, nazwa, idUczelni) VALUES(?, ?, ?)", prof.getId(), prof.getNazwa(), prof.getIdUczelni()));

        return 1;
    }

    public int update(Prof prof){
        return jdbcTemplate.update("UPDATE prowadzacy SET(nazwa=?, idUczelni=?) WHERE id=?", prof.getNazwa(), prof.getIdUczelni(), prof.getId());
    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM prowadzacy WHERE id=?", id);
    }
}
