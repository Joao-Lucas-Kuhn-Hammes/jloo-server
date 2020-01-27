package Server.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Server.Models.Lista;

public interface ListaRepository extends JpaRepository<Lista, Integer> {

    Optional<Lista> findById(Integer id);

    List<Lista> findAll();

}