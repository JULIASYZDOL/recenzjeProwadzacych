package pl.ocenProfesora.recenzjeProwadzacych.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ocenProfesora.recenzjeProwadzacych.models.OcenaTrudnosc;
import pl.ocenProfesora.recenzjeProwadzacych.repository.OcenaTruRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ocenyTrudnosci")
@RestController
public class OcenaTruController {
    @Autowired
    OcenaTruRepository ocenaTruRepository;

    @GetMapping("/test")
    public int test(){
        return 1;
    }

    @GetMapping("")
    public List<OcenaTrudnosc> getAll(){
        return ocenaTruRepository.getAll();
    }

    @GetMapping("/{id}")
    public OcenaTrudnosc getById(@PathVariable("id") int id){
        return ocenaTruRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<OcenaTrudnosc> oceny){
        return ocenaTruRepository.save(oceny);
    }

    @PatchMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody OcenaTrudnosc updatedOcena){
        OcenaTrudnosc ocenaTrudnosc = ocenaTruRepository.getById(id);
        if(ocenaTrudnosc != null){
            ocenaTrudnosc.setWartosc(updatedOcena.getWartosc());
            ocenaTrudnosc.setIdProwadzacego(updatedOcena.getIdProwadzacego());

            ocenaTruRepository.update(ocenaTrudnosc);
            return 1;
        }else{
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id){
        return ocenaTruRepository.delete(id);
    }
}
