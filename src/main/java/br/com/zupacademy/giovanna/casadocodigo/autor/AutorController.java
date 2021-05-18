package br.com.zupacademy.giovanna.casadocodigo.autor;

import br.com.zupacademy.giovanna.casadocodigo.autor.dto.AutorRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @PostMapping
    @Transactional
    public void cadastra(@RequestBody @Valid AutorRequest request) {
        Autor autor = request.converteParaEntidade();
        System.out.println(autor);
        autorRepository.save(autor);
    }
}
