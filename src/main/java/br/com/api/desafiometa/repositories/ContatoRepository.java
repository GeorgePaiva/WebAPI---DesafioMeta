package br.com.api.desafiometa.repositories;

import br.com.api.desafiometa.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}
