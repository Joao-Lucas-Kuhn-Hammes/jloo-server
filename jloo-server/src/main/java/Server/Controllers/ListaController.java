package Server.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Server.Models.Lista;
import Server.Repository.ListaRepository;

@CrossOrigin
@RestController
@RequestMapping("/lista/")
public class ListaController {
@Autowired
    private ListaRepository listaRepository;

    @GetMapping("{id}")
    public Lista getById(@PathVariable(name = "id") Integer id) {
        return listaRepository.findById(id).get();
    }

    @GetMapping()
    public List<Lista> getAll() {
        return listaRepository.findAll();
    }


    @PostMapping
    public ResponseEntity<Lista> adicionar(@RequestBody Lista novo) {
        return ResponseEntity.ok(listaRepository.save(novo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (listaRepository.existsById(id)) {
            listaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<Lista> atualizar(@RequestBody Lista novo) {
            Lista old = listaRepository.getOne(novo.getId());
            old.setItens(novo.getItens());
            old.setNome(novo.getNome());
            old.setQuadro(novo.getQuadro());
            old.setId(novo.getId());
            old = listaRepository.save(old);
            return ResponseEntity.ok(old);
    }

}


