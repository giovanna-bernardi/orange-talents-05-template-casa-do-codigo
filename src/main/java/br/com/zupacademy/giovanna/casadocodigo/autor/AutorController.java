package br.com.zupacademy.giovanna.casadocodigo.autor;

import br.com.zupacademy.giovanna.casadocodigo.autor.dto.AutorRequest;
import br.com.zupacademy.giovanna.casadocodigo.validacao.EmailUnicoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorRepository autorRepository;

    @Autowired
    private EmailUnicoValidator emailUnicoValidator;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(emailUnicoValidator);
    }

    @PostMapping
    @Transactional
    public void cadastra(@RequestBody @Valid AutorRequest request) {
        Autor autor = request.converteParaEntidade();
        System.out.println(autor);
        autorRepository.save(autor);
    }
}
