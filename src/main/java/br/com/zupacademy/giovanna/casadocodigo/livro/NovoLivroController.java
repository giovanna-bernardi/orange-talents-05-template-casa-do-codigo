package br.com.zupacademy.giovanna.casadocodigo.livro;

import br.com.zupacademy.giovanna.casadocodigo.autor.AutorRepository;
import br.com.zupacademy.giovanna.casadocodigo.categoria.CategoriaRepository;
import br.com.zupacademy.giovanna.casadocodigo.livro.dto.LivroRequest;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NovoLivroController {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    public NovoLivroController(LivroRepository livroRepository,
                               AutorRepository autorRepository,
                               CategoriaRepository categoriaRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping("/livros")
    @Transactional
    public String cadastra(@RequestBody @Valid LivroRequest request) {
        Livro livro = request.converteParaEntidade(autorRepository, categoriaRepository);
        livroRepository.save(livro);
        return livro.toString();
    }
}
