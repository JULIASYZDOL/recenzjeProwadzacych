package pl.ocenProfesora.recenzjeProwadzacych.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.ocenProfesora.recenzjeProwadzacych.models.Uczelnia;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

@Repository
public class UczelniaRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Uczelnia> getAll(){
        return jdbcTemplate.query("SELECT id, nazwa, miasto FROM uczelnie", BeanPropertyRowMapper.newInstance(Uczelnia.class));
    }

    public List<String> getAllNames() {
        List<String> names = jdbcTemplate.queryForList("SELECT nazwa FROM uczelnie", String.class);
    
        return names;    }

    public Uczelnia getById(int id){
        return jdbcTemplate.queryForObject("SELECT id, nazwa, miasto FROM uczelnie WHERE " + "id = ?", BeanPropertyRowMapper.newInstance(Uczelnia.class), id);
    }

    public Integer getIdByName(String nazwaUczelni) {
        try {
            return jdbcTemplate.queryForObject("SELECT id FROM uczelnie WHERE " + "nazwa = ?", Integer.class, nazwaUczelni);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
       

    public int save(List<Uczelnia> uczelnias) {
        uczelnias.forEach(ucz -> jdbcTemplate.update("INSERT INTO uczelnie(id, nazwa, miasto) VALUES(?, ?, ?)", ucz.getId(), ucz.getNazwa(), ucz.getMiasto()));

        return 1;
    }

    public int update(Uczelnia ucz){
        return jdbcTemplate.update("UPDATE uczelnie SET(nazwa=?, miasto=?) WHERE id=?", ucz.getNazwa(), ucz.getMiasto(), ucz.getId());
    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM uczelnie WHERE id=?", id);
    }
}
