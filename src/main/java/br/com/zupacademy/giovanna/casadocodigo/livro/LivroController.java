package br.com.zupacademy.giovanna.casadocodigo.livro;

import br.com.zupacademy.giovanna.casadocodigo.autor.AutorRepository;
import br.com.zupacademy.giovanna.casadocodigo.categoria.CategoriaRepository;
import br.com.zupacademy.giovanna.casadocodigo.livro.dto.LivroRequest;
import br.com.zupacademy.giovanna.casadocodigo.livro.dto.LivroResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    public LivroController(LivroRepository livroRepository,
                           AutorRepository autorRepository,
                           CategoriaRepository categoriaRepository) {
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

    @GetMapping
    public List<LivroResponse> listaTodosLivrosCadastrados() {
        List<Livro> livros = (List<Livro>) livroRepository.findAll();
        return LivroResponse.converter(livros);
    }

}
