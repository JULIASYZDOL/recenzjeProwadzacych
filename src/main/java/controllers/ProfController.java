package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import models.Prof;
import repository.ProfRepository;

import java.util.List;

@RestController
@RequestMapping("/prowadzacy")
public class ProfController {
    @Autowired
    ProfRepository profRepository;

    @GetMapping("/test")
    public int test(){
        return 1;
    }

    @GetMapping("/")
    public List<Prof> getAll(){
        return profRepository.getAll();
    }

    @GetMapping("/{id}")
    public Prof getById(@PathVariable("id") int id){
        return profRepository.getById(id);
    }

    @PostMapping("/")
    public int add(@RequestBody List<Prof> prowadzacy){
        return profRepository.save(prowadzacy);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Prof updatedProf){
        Prof prof = profRepository.getById(id);
        if(prof != null){
            prof.setNazwa(updatedProf.getNazwa());
            prof.setId(updatedProf.getId());

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
