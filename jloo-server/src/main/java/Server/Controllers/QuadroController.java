package Server.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Server.Models.Quadro;
import Server.Repository.QuadroRepository;

@CrossOrigin
@RestController
@RequestMapping("/quadro/")
public class QuadroController {
	
	@org.springframework.beans.factory.annotation.Autowired(required=false)
	private QuadroRepository quadroR;
	
	@GetMapping("{id}")
	public Quadro getById(@PathVariable(name = "id") Integer id) {
		return quadroR.findById(id).get();
	}
	
	@GetMapping()
	public List<Quadro> getAll() {
		return quadroR.findAll();
	}
	
	
	@PostMapping
	public ResponseEntity<Quadro> adicionar(@RequestBody Quadro novo) {
		return ResponseEntity.ok(quadroR.save(novo));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		if (quadroR.existsById(id)) {
			quadroR.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping()
	public ResponseEntity<Quadro> atualizar(@RequestBody Quadro novo) {
			Quadro old = quadroR.getOne(novo.getId());
			old.setId(novo.getId());
			old.setLista(novo.getLista());
			old.setNome(novo.getNome());
			old.setParticipantes(novo.getParticipantes());
			old.setUser(novo.getUser());
			old = quadroR.save(old);
			return ResponseEntity.ok(old);
	}

}


