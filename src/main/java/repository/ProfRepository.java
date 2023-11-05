package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import models.Prof;

import java.util.List;

@Repository
public class ProfRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Prof> getAll(){
        return jdbcTemplate.query("SELECT id, nazwa FROM prowadzacy", BeanPropertyRowMapper.newInstance(Prof.class));
    }

    public Prof getById(int id){
        return jdbcTemplate.queryForObject("SELECT id, nazwa FROM prowadzacy WHERE " + "id = ?", BeanPropertyRowMapper.newInstance(Prof.class), id);
    }

    public int save(List<Prof> prowadzacy) {
        prowadzacy.forEach(prof -> jdbcTemplate.update("INSERT INTO prowadzacy(id, nazwa) VALUES(?, ?)", prof.getId(), prof.getNazwa()));

        return 1;
    }

    public int update(Prof prof){
        return jdbcTemplate.update("UPDATE prowadzacy SET nazwa=? WHERE id=?", prof.getNazwa(), prof.getId());
    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM prowadzacy WHERE id=?", id);
    }
}
