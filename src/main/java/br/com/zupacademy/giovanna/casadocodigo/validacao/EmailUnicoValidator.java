package br.com.zupacademy.giovanna.casadocodigo.validacao;

import br.com.zupacademy.giovanna.casadocodigo.autor.AutorRepository;
import br.com.zupacademy.giovanna.casadocodigo.autor.dto.AutorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmailUnicoValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return AutorRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        AutorRequest request = (AutorRequest) o;

        System.out.println(autorRepository.existsByEmail(request.getEmail()));
        if(autorRepository.existsByEmail(request.getEmail())){
            errors.rejectValue("email", null,
                    "Já existe um(a) autor(a) com este e-mail no banco " + request.getEmail());
        }

    }
}
