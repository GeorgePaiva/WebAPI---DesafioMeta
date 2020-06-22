package br.com.api.desafiometa.controller;

import br.com.api.desafiometa.dto.ContatoDTO;
import br.com.api.desafiometa.dto.ContatoNewDTO;
import br.com.api.desafiometa.model.Contato;
import br.com.api.desafiometa.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/contatos")
public class ContatoController {

    //Instacia automaticamente
    @Autowired
    private ContatoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Contato> find(@PathVariable Integer idContato) {
        Contato obj = service.find(idContato);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody ContatoNewDTO objDto) {
        Contato obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdContato()).toUri();

        return ResponseEntity.created(uri).build();
    }

    // Método para efetuar atualização.
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody ContatoDTO objDto, @PathVariable Integer idContato) {
        Contato obj = service.fromDTO(objDto);
        obj.setIdContato(idContato);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    // Método para deletar.
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer idContato) {
        service.delete(idContato);
        return ResponseEntity.noContent().build();
    }

    // Método para pegar todos os Contatos.
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ContatoDTO>> findAll() {
        List<Contato> list = service.findAll();
        List<ContatoDTO> listDto = list.stream().map(obj -> new ContatoDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    // Método com pagina para pegar os Contatos com paginação.
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ContatoDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                    @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                    @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Contato> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<ContatoDTO> listDto = list.map(obj -> new ContatoDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }

}
