package br.com.api.desafiometa.controller;

import br.com.api.desafiometa.model.Contato;
import br.com.api.desafiometa.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @GetMapping("/contatos")
    public List<Contato> finAll() {
        return service.findAll();
    }

    @GetMapping("/contatos/{id}")
    public ResponseEntity<Contato> findById(@PathVariable(value = "id") Integer id){
        Contato contato = service.findById(id);
        return ResponseEntity.ok().body(contato);
    }

    @PostMapping("/contatos")
    public Contato insert(@Validated @RequestBody Contato contato) {
        return service.insert(contato);
    }

    @PutMapping("/contatos/{id}")
    public ResponseEntity<Contato> update(@PathVariable(value = "id") Integer id, @Validated @RequestBody Contato contato) {
        Contato obj = service.findById(id);
        contato.setId(contato.getId());
        contato.setNome(contato.getNome());
        contato.setTipoDeContato(contato.getTipoDeContato());
        contato.setValorContato(contato.getValorContato());
        contato.setObservacao(contato.getObservacao());
        Contato updatedContato = service.insert(contato);
        return ResponseEntity.ok(updatedContato);
    }

    @DeleteMapping("/contatos/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Integer id) {
        Contato contato = service.findById(id);
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Contato deletado com sucesso!", Boolean.TRUE);
        return response;
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
