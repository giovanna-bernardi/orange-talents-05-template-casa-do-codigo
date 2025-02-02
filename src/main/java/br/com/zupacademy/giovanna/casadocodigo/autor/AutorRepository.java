package br.com.zupacademy.giovanna.casadocodigo.autor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Long> {
    boolean existsByEmail(String email);
}
