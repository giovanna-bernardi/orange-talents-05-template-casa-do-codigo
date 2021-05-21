package br.com.zupacademy.giovanna.casadocodigo.paisestado;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends CrudRepository<Pais, Long> {
}
