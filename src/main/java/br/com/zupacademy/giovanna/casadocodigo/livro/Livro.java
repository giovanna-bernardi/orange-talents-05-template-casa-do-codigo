package br.com.zupacademy.giovanna.casadocodigo.livro;

import br.com.zupacademy.giovanna.casadocodigo.autor.Autor;
import br.com.zupacademy.giovanna.casadocodigo.categoria.Categoria;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo título não pode ser nulo nem vazio")
    @Column(nullable = false)
    private String titulo;

    @NotBlank(message = "O campo resumo não pode ser nulo nem vazio")
    @Size(max = 500, message = "Máximo de 500 caracteres")
    @Column(nullable = false)
    private String resumo;

    @NotBlank(message = "O campo sumario não pode ser nulo nem vazio")
    @Column(columnDefinition = "TEXT")
    private String sumario;

    @NotNull(message = "O campo preço não pode ser nulo")
    @Min(value = 20, message = "O preço mínimo do livro é de 20")
    @Column(nullable = false)
    private BigDecimal preco;

    @NotNull(message = "O campo numeroPaginas não pode ser nulo")
    @Min(value = 100, message = "O livro deve ter no mínimo 100 páginas")
    @Column(nullable = false)
    private Integer numeroPaginas;

    @NotBlank(message = "O campo isbn não pode ser nulo nem vazio")
    @ISBN(type = ISBN.Type.ANY)
    @Column(nullable = false)
    private String isbn;

    @NotNull(message = "O campo dataPublicacao não pode ser nulo")
    @Future(message = "A data de publicação deve estar no futuro")
    private LocalDate dataPublicacao;

    @ManyToOne
    @NotNull(message = "O campo categoria não pode ser nulo")
    private Categoria categoria;

    @ManyToOne
    @NotNull(message = "O campo autor não pode ser nulo")
    private Autor autor;

    @Deprecated
    public Livro() {
    }

    public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
                 @NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer numeroPaginas, @NotBlank String isbn,
                 @NotNull @Future LocalDate dataPublicacao, @NotNull @Valid Categoria categoria, @NotNull @Valid Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }


    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", numeroPaginas=" + numeroPaginas +
                ", isbn='" + isbn + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", categoria=" + categoria +
                ", autor=" + autor +
                '}';
    }
}
