package br.com.zupacademy.giovanna.casadocodigo.categoria;

import br.com.zupacademy.giovanna.casadocodigo.autor.AutorRepository;
import br.com.zupacademy.giovanna.casadocodigo.categoria.dto.CategoriaRequest;
import br.com.zupacademy.giovanna.casadocodigo.validacao.EmailUnicoValidator;
import br.com.zupacademy.giovanna.casadocodigo.validacao.NomeUnicoCategoriaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    private NomeUnicoCategoriaValidator nomeUnicoCategoriaValidator;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(nomeUnicoCategoriaValidator);
    }


    @PostMapping
    @Transactional
    public String cadastra(@RequestBody @Valid CategoriaRequest request) {
        Categoria categoria = new Categoria(request.getNome());
        categoriaRepository.save(categoria);
        return categoria.toString();
    }
}
