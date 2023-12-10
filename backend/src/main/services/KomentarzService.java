package pl.ocenProfesora.recenzjeProwadzacych.services;

import pl.ocenProfesora.recenzjeProwadzacych.dto.KomentarzDto;
import pl.ocenProfesora.recenzjeProwadzacych.models.Komentarz;
import pl.ocenProfesora.recenzjeProwadzacych.mappings.KomentarzMapper;
import pl.ocenProfesora.recenzjeProwadzacych.repositories.KomentarzRepository;
import java.util.Collection;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class KomentarzService {

  private final KomentarzMapper komentarzMapper;
  private final KomentarzRepository komentarzRepo;

  public Collection<KomentarzDto> findAll() {
    return komentarzRepo.findAll().stream()
        .map(komentarzMapper::toDto)
        .toList();
  }

  public KomentarzDto findById(Long id) {
    return komentarzRepo.findById(id)
        .map(komentarzMapper::toDto)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Komentarz not found"));
  }

  public KomentarzDto create(KomentarzDto komentarz) {
    if (komentarz.getId() != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide komentarz without id");
    }

    return save(komentarz);
  }

  public KomentarzDto update(Long id, KomentarzDto komentarz) {
    if (!Objects.equals(id, komentarz.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id and komentarz.id is not equal");
    }
    return save(komentarz);
  }

  public KomentarzDto save(KomentarzDto komentarz) {
    Komentarz entity = komentarzMapper.toEntity(komentarz);
    entity = komentarzRepo.saveAndFlush(entity);
    return komentarzMapper.toDto(entity);
  }

  public void deleteById(Long id) {
    komentarzRepo.deleteById(id);
  }
}