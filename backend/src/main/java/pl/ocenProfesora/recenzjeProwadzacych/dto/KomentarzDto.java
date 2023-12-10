package pl.ocenProfesora.recenzjeProwadzacych.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class KomentarzDto {

  @Schema(description = "Komentarz primary key")
  @JsonView({Views.Get.class, Views.Put.class})
  Long id;

  @Schema(description = "Tytuł komentarza", example = "Mój komentarz")
  @JsonView({Views.Get.class, Views.Put.class, Views.Post.class})
  String tytul;

  @Schema(description = "Treść komentarza", example = "Treść mojego komentarza")
  @JsonView({Views.Get.class, Views.Put.class, Views.Post.class})
  String tresc;

  @Schema(description = "Pseudonim komentarza", example = "Mój psudonim")
  @JsonView({Views.Get.class, Views.Put.class, Views.Post.class})
  String psudonim;

  @Schema(description = "Prowadzący key")
  @JsonView({Views.Get.class, Views.Put.class})
  Long idProwadzacego;

  @JsonView({Views.Get.class})
  String createdBy;
}