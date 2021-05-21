package br.com.zupacademy.giovanna.casadocodigo.paisestado;

import br.com.zupacademy.giovanna.casadocodigo.paisestado.dto.EstadoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NovoEstadoController {

    private final EstadoRepository estadoRepository;
    private final PaisRepository paisRepository;

    public NovoEstadoController(EstadoRepository estadoRepository, PaisRepository paisRepository) {
        this.estadoRepository = estadoRepository;
        this.paisRepository = paisRepository;
    }

    @PostMapping("/estados")
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid EstadoRequest request) {
        Estado estado = request.converteParaEntidade(paisRepository);
        estadoRepository.save(estado);
        return ResponseEntity.ok(estado.toString());
    }
}
