package br.com.zupacademy.giovanna.casadocodigo.paisestado;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Long> {
    List<Estado> findByPaisId(Long paisId);
}
