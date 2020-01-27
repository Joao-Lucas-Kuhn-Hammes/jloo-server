package Server.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import Server.Models.Quadro;

@Service
public interface QuadroRepository extends JpaRepository<Quadro, Integer> {

	Optional<Quadro> findById(Integer id);
	
	List<Quadro> findAll();
}
