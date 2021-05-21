package br.com.zupacademy.giovanna.casadocodigo.cliente;

import br.com.zupacademy.giovanna.casadocodigo.cliente.dto.ClienteRequest;
import br.com.zupacademy.giovanna.casadocodigo.paisestado.EstadoRepository;
import br.com.zupacademy.giovanna.casadocodigo.paisestado.PaisRepository;
import br.com.zupacademy.giovanna.casadocodigo.validation.ExistsStateInCountryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NovoClienteController {

    private final ClienteRepository clienteRepository;
    private final PaisRepository paisRepository;
    private final EstadoRepository estadoRepository;

    @Autowired
    private ExistsStateInCountryValidator existsStateInCountryValidator;

    public NovoClienteController(ClienteRepository clienteRepository,
                                 PaisRepository paisRepository,
                                 EstadoRepository estadoRepository) {
        this.clienteRepository = clienteRepository;
        this.paisRepository = paisRepository;
        this.estadoRepository = estadoRepository;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(existsStateInCountryValidator);
    }

    @PostMapping("/clientes")
    @Transactional
    public String cadastra(@RequestBody @Valid ClienteRequest request) {

        Cliente cliente = request.map(paisRepository, estadoRepository);
        clienteRepository.save(cliente);

        return cliente.toString();
    }
}
