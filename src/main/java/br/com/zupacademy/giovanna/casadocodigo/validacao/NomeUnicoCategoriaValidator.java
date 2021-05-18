package br.com.zupacademy.giovanna.casadocodigo.validacao;

import br.com.zupacademy.giovanna.casadocodigo.autor.AutorRepository;
import br.com.zupacademy.giovanna.casadocodigo.autor.dto.AutorRequest;
import br.com.zupacademy.giovanna.casadocodigo.categoria.CategoriaRepository;
import br.com.zupacademy.giovanna.casadocodigo.categoria.dto.CategoriaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NomeUnicoCategoriaValidator implements Validator {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoriaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        CategoriaRequest request = (CategoriaRequest) o;

        if(categoriaRepository.existsByNome(request.getNome())){
            errors.rejectValue("nome", null,
                    "Já existe uma categoria com este nome no banco: " + request.getNome());
        }

    }
}
