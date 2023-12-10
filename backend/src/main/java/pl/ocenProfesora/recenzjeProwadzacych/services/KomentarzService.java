package pl.ocenProfesora.recenzjeProwadzacych.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.ocenProfesora.recenzjeProwadzacych.dto.KomentarzDto;
import pl.ocenProfesora.recenzjeProwadzacych.mappings.KomentarzMapper;
import pl.ocenProfesora.recenzjeProwadzacych.models.Komentarz;
import pl.ocenProfesora.recenzjeProwadzacych.repository.KomentarzRepository;

import java.util.Collection;
import java.util.Objects;
@Service
@RequiredArgsConstructor
public class KomentarzService {
    private final KomentarzMapper komentarzMapper;
    private final KomentarzRepository komentarzRepository;

    public Collection<KomentarzDto> findAll() {
        return komentarzRepository.findAll().stream()
                .map(komentarzMapper::toDto)
                .toList();
    }

    public KomentarzDto findById(Long id) {
        return komentarzRepository.findById(id)
                .map(komentarzMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Komentarz not found"));
    }

    public KomentarzDto create(KomentarzDto komentarz) {
        if (komentarz.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide Komentarz without id");
        }

        return save(komentarz);
    }

    public KomentarzDto update(Long id, KomentarzDto komentarz) {
        if (!Objects.equals(id, komentarz.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id and Komentarz.id is not equal");
        }
        return save(komentarz);
    }

    public KomentarzDto save(KomentarzDto komentarzDto) {
        Komentarz entity = komentarzMapper.toEntity(komentarzDto);
        entity = komentarzRepository.saveAndFlush(entity);
        return komentarzMapper.toDto(entity);
    }

    public void deleteById(Long id) {
        komentarzRepository.deleteById(id);
    }
}
