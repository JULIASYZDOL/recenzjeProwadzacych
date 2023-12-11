package pl.ocenProfesora.recenzjeProwadzacych.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ocenProfesora.recenzjeProwadzacych.models.Prof;
import pl.ocenProfesora.recenzjeProwadzacych.repository.ProfRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/prowadzacy")
@RestController
public class ProfController {
    @Autowired
    ProfRepository profRepository;

    @GetMapping("/test")
    public int test(){
        return 1;
    }

    @GetMapping("")
    public List<Prof> getAll(){
        return profRepository.getAll();
    }

    @GetMapping("/byId/{id}")
    public List<Prof> getAllById(@PathVariable("id") int id){
        return profRepository.getAllById(id);
    }

    @GetMapping("/byUczelni/{idUczelni}")
    public List<String> getAllByIdUczelni(@PathVariable("idUczelni") int idUczelni){
        return profRepository.geAllByIdUczelni(idUczelni);
    }

    @GetMapping("byNazwa/{nazwa}")
    public Integer getIdByName(@PathVariable("nazwa") String nazwa){
        return profRepository.getByName(nazwa);
    }

    @PostMapping("")
    public int add(@RequestBody List<Prof> prowadzacy){
        return profRepository.save(prowadzacy);
    }

    @PatchMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Prof updatedProf){
        Prof prof = profRepository.getById(id);
        if(prof != null){
            prof.setNazwa(updatedProf.getNazwa());
            prof.setId(updatedProf.getId());
            prof.setIdUczelni(updatedProf.getIdUczelni());

            profRepository.update(prof);
            return 1;
        }else{
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id){
        return profRepository.delete(id);
    }
}
