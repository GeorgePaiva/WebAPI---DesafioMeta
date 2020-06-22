package br.com.api.desafiometa.controller;

import br.com.api.desafiometa.model.Contato;
import br.com.api.desafiometa.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/contatos")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Contato> findById(@PathVariable Integer id) {
        Contato obj = service.findById(id);
        return ResponseEntity.ok().body(obj);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Contato contato) {
        Contato obj = service.insert(contato);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@Validated @RequestBody Contato contato, @PathVariable Integer id) {
        Contato obj = service.update(contato);
        obj.setId(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Contato>> findAll() {
        List<Contato> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<Contato>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                 @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                 @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Contato> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

}
