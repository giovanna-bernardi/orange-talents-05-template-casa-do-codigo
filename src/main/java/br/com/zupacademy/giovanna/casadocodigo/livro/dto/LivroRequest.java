package br.com.zupacademy.giovanna.casadocodigo.livro.dto;

import br.com.zupacademy.giovanna.casadocodigo.autor.Autor;
import br.com.zupacademy.giovanna.casadocodigo.autor.AutorRepository;
import br.com.zupacademy.giovanna.casadocodigo.categoria.Categoria;
import br.com.zupacademy.giovanna.casadocodigo.categoria.CategoriaRepository;
import br.com.zupacademy.giovanna.casadocodigo.livro.Livro;
import br.com.zupacademy.giovanna.casadocodigo.validation.ExistsId;
import br.com.zupacademy.giovanna.casadocodigo.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.util.Assert;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class LivroRequest {

    @UniqueValue(domainClass = Livro.class, fieldName = "titulo", message = "O título deve ser único")
    @NotBlank(message = "O campo título não pode ser nulo nem vazio")
    private String titulo;

    @NotBlank(message = "O campo resumo não pode ser nulo nem vazio")
    @Size(max = 500, message = "Máximo de 500 caracteres")
    private String resumo;

    @NotBlank(message = "O campo sumario não pode ser nulo nem vazio")
    private String sumario;

    @NotNull(message = "O campo preço não pode ser nulo")
    @Min(value = 20, message = "O preço mínimo do livro é de 20")
    private BigDecimal preco;

    @NotNull(message = "O campo numeroPaginas não pode ser nulo")
    @Min(value = 100, message = "O livro deve ter no mínimo 100 páginas")
    private Integer numeroPaginas;

    @ISBN(type = ISBN.Type.ANY)
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn", message = "O isbn deve ser único")
    @NotBlank(message = "O campo isbn não pode ser nulo nem vazio")
    private String isbn;

    @Future
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    @ExistsId(entity = Categoria.class, fieldName = "id")
    @NotNull(message = "O id da categoria não pode ser nulo")
    private Long categoriaId;

    @ExistsId(entity = Autor.class, fieldName = "id")
    @NotNull(message = "O id do autor não pode ser nulo")
    private Long autorId;

    public LivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
                        @NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer numeroPaginas,
                        @NotBlank @ISBN(type = ISBN.Type.ANY) String isbn,
                        @NotNull Long categoriaId, @NotNull Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    /* Precisa desse setter para que o jackson consiga desserializar
     * o json no formato xx/xx/xxxx, pelo
     * construtor ele não conseguiu.
     * Obs: funciona com LocalDate, com LocalDateTime não deu certo fazer assim.
     * */

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Livro converteParaEntidade(AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
        @NotNull Optional<Autor> autor = autorRepository.findById(this.autorId);
        @NotNull Optional<Categoria> categoria = categoriaRepository.findById(this.categoriaId);

        Assert.state(autor.isPresent(),"Você esta querendo cadastrar um livro para um autor que nao existe no banco " + this.autorId);
        Assert.state(categoria.isPresent(),"Você esta querendo cadastrar um livro para uma categoria que nao existe no banco " + this.categoriaId);

        return new Livro(this.titulo, this.resumo, this.sumario,
                this.preco, this.numeroPaginas, this.isbn, this.dataPublicacao, categoria.get(), autor.get());

    }
}
