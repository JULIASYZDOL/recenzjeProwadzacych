package pl.ocenProfesora.recenzjeProwadzacych.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.ocenProfesora.recenzjeProwadzacych.dto.KomentarzDto;
import pl.ocenProfesora.recenzjeProwadzacych.dto.Views;
import pl.ocenProfesora.recenzjeProwadzacych.services.KomentarzService;

import java.util.Collection;

@Slf4j
@RestController
@Tag(name = "Komentarz", description = "Komentarz API")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
@RequestMapping(value = "komentarze")
public class KomentarzController {

    private final KomentarzService komentarzService;

    @Operation(
            summary = "Find all Komentarzs",
            description = "Get all Komentarz objects.",
            tags = {"get"})
    @GetMapping
    @JsonView(Views.Get.class)
    public Collection<KomentarzDto> findAll() {
        log.debug("Find all Komentarzs");
        return komentarzService.findAll();
    }

    @Operation(
            summary = "Find a Komentarz by Id",
            description = "Get a Komentarz object by specifying its id.",
            tags = {"get"})
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(schema = @Schema(implementation = KomentarzDto.class), mediaType = "application/json")})
    @ApiResponse(
            responseCode = "404",
            content = {@Content(schema = @Schema())})
    @ApiResponse(
            responseCode = "500",
            content = {@Content(schema = @Schema())})
    @GetMapping("/{id}")
    @JsonView(value = Views.Get.class)
    public KomentarzDto findById(
            @Parameter(description = "Komentarz Id.", example = "1")
            @PathVariable Long id) {
        log.debug("Find Komentarz with id: {}", id);
        return komentarzService.findById(id);
    }

    @Operation(
            summary = "Create a Komentarz",
            description = "Create a Komentarz object.",
            tags = {"post"})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(value = Views.Get.class)
    public KomentarzDto create(
            @RequestBody @JsonView(value = Views.Post.class) KomentarzDto komentarz) {
        log.debug("Create Komentarz: {}", komentarz);
        return komentarzService.create(komentarz);
    }

    @Operation(
            summary = "Update a Komentarz by Id",
            description = "Update a Komentarz object by specifying its id.",
            tags = {"put"})
    @PutMapping("/{id}")
    @JsonView(value = Views.Get.class)
    public KomentarzDto update(
            @Parameter(description = "Komentarz Id.", example = "1")
            @PathVariable Long id,
            @RequestBody @JsonView(value = Views.Put.class) KomentarzDto komentarz) {
        log.debug("Update Komentarz with id: {}, with Komentarz {}", id, komentarz);
        return komentarzService.update(id, komentarz);
    }

    @Operation(
            summary = "Delete a Komentarz by Id",
            description = "Delete a Komentarz object by specifying its id.",
            tags = {"delete"})
    @DeleteMapping("/{id}")
    public void deleteEmployee(
            @Parameter(description = "Komentarz Id.", example = "1")
            @PathVariable Long id) {
        log.debug("Delete Komentarz with id: {}", id);
        komentarzService.deleteById(id);
    }

    /*@GetMapping("/test")
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
    }*/
}
