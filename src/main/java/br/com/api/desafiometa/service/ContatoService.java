package br.com.api.desafiometa.service;

import br.com.api.desafiometa.exceptions.DataIntegrityException;
import br.com.api.desafiometa.model.Contato;
import br.com.api.desafiometa.repositories.ContatoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repo;

    public Contato findById(Integer id) {
        Optional<Contato> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Contato.class.getName(), Contato.class.getName()));
    }

    public Contato insert(Contato obj) {
        return repo.save(obj);
    }

    public Contato update(Contato obj) {
        Contato newObj = findById(obj.getId());
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possável excluir porque há entidades relacionadas");
        }
    }

    public List<Contato> findAll() {
        return repo.findAll();
    }

    public Page<Contato> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

}

