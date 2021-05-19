package br.com.zupacademy.giovanna.casadocodigo.categoria;

import br.com.zupacademy.giovanna.casadocodigo.categoria.dto.CategoriaRequest;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    @Transactional
    public String cadastra(@RequestBody @Valid CategoriaRequest request) {
        Categoria categoria = new Categoria(request.getNome());
        categoriaRepository.save(categoria);
        return categoria.toString();
    }
}
