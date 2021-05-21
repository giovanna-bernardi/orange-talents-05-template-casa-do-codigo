package br.com.zupacademy.giovanna.casadocodigo.validation;

import br.com.zupacademy.giovanna.casadocodigo.cliente.dto.ClienteRequest;
import br.com.zupacademy.giovanna.casadocodigo.paisestado.Estado;
import br.com.zupacademy.giovanna.casadocodigo.paisestado.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class ExistsStateInCountryValidator implements Validator {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return ClienteRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        ClienteRequest cliente = (ClienteRequest) o;

        List<Estado> estados = estadoRepository.findByPaisId(cliente.getPaisId());

        if(estados.isEmpty() && cliente.getEstadoId() != null) {
            errors.rejectValue("paisId", null, "Você não deve escolher um estado, pois o país escolhido não possui estados");
        }

        if(estados.size() >= 1 && cliente.getEstadoId() == null) {
            errors.rejectValue("estadoId", null, "Este país possui estados, então você deve selecionar um");
        }

        if(cliente.getEstadoId() != null && estadoNaoPertenceAoPais(estados, cliente)) {
            errors.rejectValue("estadoId", null, "O estado escolhido não pertence a este país");
        }

    }

    private boolean estadoNaoPertenceAoPais(List<Estado> estados, ClienteRequest cliente) {
        for (Estado e : estados) {
            if (cliente.getEstadoId() == e.getId()) {
                return false;
            }
        }
        return true;
    }
}
