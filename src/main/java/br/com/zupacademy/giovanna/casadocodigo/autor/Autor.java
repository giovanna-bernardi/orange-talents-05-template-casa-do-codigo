package br.com.zupacademy.giovanna.casadocodigo.autor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo nome não pode ser nulo nem vazio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O campo e-mail não pode ser nulo nem vazio")
    @Email(message = "O e-mail deve ter um formato válido")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "O campo descrição não pode ser nulo nem vazio")
    @Size(max=400, message = "Máximo de 400 caracteres")
    @Column(nullable = false)
    private String descricao;

    @NotNull(message = "Instante do registro não pode ser nulo")
    @Column(nullable = false)
    private LocalDateTime instanteRegistro = LocalDateTime.now();

    public Autor() {
    }

    public Autor(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                ", instanteRegistro=" + instanteRegistro +
                '}';
    }
}
