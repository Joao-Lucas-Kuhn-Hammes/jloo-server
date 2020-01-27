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

import Server.Models.User;
import Server.Repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/user/")
public class UserController {
	
	@org.springframework.beans.factory.annotation.Autowired(required=false)
	private UserRepository userR;
	
	@GetMapping("{id}")
	public User getById(@PathVariable(name = "id") Integer id) {
		return userR.findById(id).get();
	}
	
	@GetMapping()
	public List<User> getAll() {
		return userR.findAll();
	}
	
	@GetMapping("{email}/{senha}")
	public User login(@PathVariable(name = "email") String email, @PathVariable(name = "senha") String senha) {
		return userR.findByEmailAndSenhaIn(email, senha.hashCode()+"");
	}
	
	@PostMapping
	public ResponseEntity<User> adicionar(@RequestBody User novo) {
		novo.setSenha(novo.getSenha().hashCode()+"");
		return ResponseEntity.ok(userR.save(novo));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		if (userR.existsById(id)) {
			userR.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping()
	public ResponseEntity<User> atualizar(@RequestBody User novo) {
			User old = userR.getOne(novo.getId());
			old.setEmail(novo.getEmail());
			old.setNome(novo.getNome());
			old.setSenha(novo.getSenha().hashCode()+"");
			old.setId(novo.getId());	
			old.setSobrenome(novo.getSobrenome());
			old = userR.save(old);
			return ResponseEntity.ok(old);
	}

}
