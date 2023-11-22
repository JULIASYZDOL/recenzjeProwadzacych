package pl.ocenProfesora.recenzjeProwadzacych.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ocenProfesora.recenzjeProwadzacych.models.Komentarz;
import pl.ocenProfesora.recenzjeProwadzacych.repository.KomentarzRepository;

import java.util.List;

@RequestMapping("/komentarze")
@RestController
public class KomentarzController {
    @Autowired
    KomentarzRepository komentarzRepository;

    @GetMapping("/test")
    public int test(){
        return 1;
    }

    @GetMapping("")
    public List<Komentarz> getAll(){
        return komentarzRepository.getAll();
    }

    @GetMapping("/{id}")
    public Komentarz getById(@PathVariable("id") int id){
        return komentarzRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Komentarz> kom){
        return komentarzRepository.save(kom);
    }

    @PatchMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Komentarz updatedKomentarz){
        Komentarz kom = komentarzRepository.getById(id);
        if(kom != null){
            kom.setTytul(updatedKomentarz.getTytul());
            kom.setTresc(updatedKomentarz.getTresc());
            kom.setPseudonim(updatedKomentarz.getPseudonim());
            kom.setIdProwadzacego(updatedKomentarz.getIdProwadzacego());

            komentarzRepository.update(kom);
            return 1;
        }else{
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id){
        return komentarzRepository.delete(id);
    }
}
