package pl.ocenProfesora.recenzjeProwadzacych.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.ocenProfesora.recenzjeProwadzacych.models.Komentarz;

import java.util.List;

@Repository
public class KomentarzRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Komentarz> getAll(){
        return jdbcTemplate.query("SELECT id, tytul, tresc, pseudonim, idProwadzacego FROM komentarz", BeanPropertyRowMapper.newInstance(Komentarz.class));
    }

    public Komentarz getById(int id){
        return jdbcTemplate.queryForObject("SELECT id, tytul, tresc, pseudonim, idProwadzacego FROM komentarz WHERE " + "id = ?", BeanPropertyRowMapper.newInstance(Komentarz.class), id);
    }

    public int save(List<Komentarz> komentarze) {
        komentarze.forEach(kom -> jdbcTemplate.update("INSERT INTO komentarz(id, tytul, tresc, pseudonim, idProwadzacego) VALUES(?, ?, ?, ?, ?)", kom.getId(), kom.getTytul(), kom.getTresc(), kom.getPseudonim(), kom.getIdProwadzacego()));

        return 1;
    }

    public int update(Komentarz kom){
        return jdbcTemplate.update("UPDATE komentarz SET(tytul=?, tresc=?, pseudonim=?, idProwadzacego=?) WHERE id=?", kom.getTytul(), kom.getTresc(), kom.getPseudonim(), kom.getIdProwadzacego(), kom.getId());
    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM komentarz WHERE id=?", id);
    }
}
