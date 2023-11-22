package pl.ocenProfesora.recenzjeProwadzacych.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ocenProfesora.recenzjeProwadzacych.models.OcenaJakosc;
import pl.ocenProfesora.recenzjeProwadzacych.repository.OcenaJakRepository;

import java.util.List;

@RequestMapping("/ocenyJakosci")
@RestController
public class OcenaJakController {
    @Autowired
    OcenaJakRepository ocenaJakRepository;

    @GetMapping("/test")
    public int test(){
        return 1;
    }

    @GetMapping("")
    public List<OcenaJakosc> getAll(){
        return ocenaJakRepository.getAll();
    }

    @GetMapping("/{id}")
    public OcenaJakosc getById(@PathVariable("id") int id){
        return ocenaJakRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<OcenaJakosc> oceny){
        return ocenaJakRepository.save(oceny);
    }

    @PatchMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody OcenaJakosc updatedOcena){
        OcenaJakosc ocenaJakosc = ocenaJakRepository.getById(id);
        if(ocenaJakosc != null){
            ocenaJakosc.setWartosc(updatedOcena.getWartosc());
            ocenaJakosc.setIdProwadzacego(updatedOcena.getIdProwadzacego());

            ocenaJakRepository.update(ocenaJakosc);
            return 1;
        }else{
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id){
        return ocenaJakRepository.delete(id);
    }
}
