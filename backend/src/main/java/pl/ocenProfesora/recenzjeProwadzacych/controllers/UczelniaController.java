package pl.ocenProfesora.recenzjeProwadzacych.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ocenProfesora.recenzjeProwadzacych.models.Uczelnia;
import pl.ocenProfesora.recenzjeProwadzacych.repository.UczelniaRepository;

import java.util.List;

@RequestMapping("/uczelnie")
@RestController
public class UczelniaController {
    @Autowired
    UczelniaRepository uczelniaRepository;

    @GetMapping("/test")
    public int test(){
        return 1;
    }

    @GetMapping("")
    public List<Uczelnia> getAll(){
        return uczelniaRepository.getAll();
    }

    @GetMapping("/{id}")
    public Uczelnia getById(@PathVariable("id") int id){
        return uczelniaRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Uczelnia> ucz){
        return uczelniaRepository.save(ucz);
    }

    @PatchMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Uczelnia updatedUcz){
        Uczelnia ucz = uczelniaRepository.getById(id);
        if(ucz != null){
            ucz.setNazwa(updatedUcz.getNazwa());
            ucz.setId(updatedUcz.getId());
            ucz.setMiasto(updatedUcz.getMiasto());

            uczelniaRepository.update(ucz);
            return 1;
        }else{
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id){
        return uczelniaRepository.delete(id);
    }
}
