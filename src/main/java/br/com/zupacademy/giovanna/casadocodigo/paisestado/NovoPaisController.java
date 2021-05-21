package br.com.zupacademy.giovanna.casadocodigo.paisestado;

import br.com.zupacademy.giovanna.casadocodigo.paisestado.dto.PaisRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NovoPaisController {

    private final PaisRepository paisRepository;

    public NovoPaisController(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    @PostMapping("/paises")
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid PaisRequest request) {
        Pais pais = request.converteParaEntidade();
        paisRepository.save(pais);
        return ResponseEntity.ok(pais.toString());
    }
}
