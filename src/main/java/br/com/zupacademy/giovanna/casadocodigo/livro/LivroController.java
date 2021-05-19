package br.com.zupacademy.giovanna.casadocodigo.livro;

import br.com.zupacademy.giovanna.casadocodigo.autor.AutorRepository;
import br.com.zupacademy.giovanna.casadocodigo.categoria.CategoriaRepository;
import br.com.zupacademy.giovanna.casadocodigo.livro.dto.LivroRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    public LivroController(LivroRepository livroRepository,
                           AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    @Transactional
    public String cadastra(@RequestBody @Valid LivroRequest request) {
        Livro livro = request.converteParaEntidade(autorRepository, categoriaRepository);
        livroRepository.save(livro);
        return livro.toString();
    }
}
