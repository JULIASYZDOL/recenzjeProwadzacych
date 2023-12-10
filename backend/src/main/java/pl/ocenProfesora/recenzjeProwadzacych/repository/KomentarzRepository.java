package pl.ocenProfesora.recenzjeProwadzacych.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import pl.ocenProfesora.recenzjeProwadzacych.models.Komentarz;

@NoRepositoryBean
@Repository
public interface KomentarzRepository extends JpaRepository<Komentarz, Long> {
}

