package pl.ocenProfesora.recenzjeProwadzacych.mappings;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.ocenProfesora.recenzjeProwadzacych.dto.KomentarzDto;
import pl.ocenProfesora.recenzjeProwadzacych.models.Komentarz;

@Component
@Mapper
public interface KomentarzMapper {

  KomentarzDto toDto(Komentarz komentarz);

  Komentarz toEntity(KomentarzDto dto);
}