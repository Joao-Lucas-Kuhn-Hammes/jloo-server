package Server.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Server.Models.Quadro;

public interface QuadroRepository extends JpaRepository<Quadro, Integer> {

	Optional<Quadro> findById(Integer id);
	
	List<Quadro> findAll();
}
