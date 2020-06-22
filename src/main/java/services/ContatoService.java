package services;

import dto.ContatoDTO;
import dto.ContatoNewDTO;
import exceptions.DataIntegrityException;
import model.Contato;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ContatoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repo;

    public Contato find(Integer idContato) {
        Optional<Contato> obj = repo.findById(idContato);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException(
                        "Objeto não encontrado! Id: " + idContato + ", " +
                                "Tipo: " + Contato.class.getName(), Contato.class.getName()));
    }

    @Transactional
    public Contato insert(Contato obj) {
        obj.setIdContato(null);
        obj = repo.save(obj);
        return obj;
    }

    public Contato update(Contato obj) {
        Contato newObj = find(obj.getIdContato());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer idContato) {
        find(idContato);
        try {
            repo.deleteById(idContato);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possável excluir porque há entidades relacionadas");
        }
    }

    public List<Contato> findAll() {
        return repo.findAll();
    }

    // Método que retorna as categorias especificadas.
    public Page<Contato> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    // Método auxiliar que instância uma Categoria a partir de DTO.
    public Contato fromDTO(ContatoDTO objDto) {
        return new Contato(objDto.getIdContato(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    public Contato fromDTO(ContatoNewDTO objDto) {
        Contato contato = new Contato(null, objDto.getNome(), objDto.getEmail(), objDto.getValorEmail(),
                objDto.getObservacao());
        return contato;
    }

    private void updateData(Contato newObj, Contato obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
