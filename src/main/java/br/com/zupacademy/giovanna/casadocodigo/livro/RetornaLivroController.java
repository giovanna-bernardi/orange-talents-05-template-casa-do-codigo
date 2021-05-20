package br.com.zupacademy.giovanna.casadocodigo.livro;

import br.com.zupacademy.giovanna.casadocodigo.livro.dto.LivroDetailResponse;
import br.com.zupacademy.giovanna.casadocodigo.livro.dto.LivroResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class RetornaLivroController {

    private final LivroRepository livroRepository;

    public RetornaLivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @GetMapping("/livros")
    public List<LivroResponse> listaTodosLivrosCadastrados() {
        List<Livro> livros = (List<Livro>) livroRepository.findAll();
        return LivroResponse.converter(livros);
    }

    @GetMapping("/livros/{id}")
    public ResponseEntity<?> detalhaLivro(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            return ResponseEntity.ok(new LivroDetailResponse(livro.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
