package repositories;

import model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
    @Transactional(readOnly = true)
    Contato findByEmail(String email);
}
