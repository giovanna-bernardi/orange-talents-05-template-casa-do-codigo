package br.com.zupacademy.giovanna.casadocodigo.cliente;

import br.com.zupacademy.giovanna.casadocodigo.paisestado.Estado;
import br.com.zupacademy.giovanna.casadocodigo.paisestado.Pais;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo e-mail não pode estar vazio")
    @Email(message = "O e-mail deve ter um formato válido")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "O campo nome não pode estar vazio")
    @Column(nullable = false)
    private String nome;
    @NotBlank(message = "O campo sobrenome não pode estar vazio")
    @Column(nullable = false)
    private String sobrenome;

    @NotBlank(message = "O campo documento não pode estar vazio")
    @Column(nullable = false)
    private String documento;

    @NotBlank(message = "O campo endereco não pode estar vazio")
    @Column(nullable = false)
    private String endereco;

    @NotBlank(message = "O campo complemento não pode estar vazio")
    @Column(nullable = false)
    private String complemento;

    @NotBlank(message = "O campo cidade não pode estar vazio")
    @Column(nullable = false)
    private String cidade;

    @NotBlank(message = "O campo telefone não pode estar vazio")
    @Column(nullable = false)
    private String telefone;

    @NotBlank(message = "O campo cep não pode estar vazio")
    @Column(nullable = false)
    private String cep;

    @NotNull(message = "O país não pode ser nulo")
    @ManyToOne
    private Pais pais;

    @ManyToOne
    private Estado estado; //(caso aquele pais tenha estado)

    @Deprecated
    public Cliente() {
    }

    public Cliente(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome,
                   @NotBlank String documento, @NotBlank String endereco,
                   @NotBlank String complemento, @NotBlank String cidade,
                   @NotBlank String telefone, @NotBlank String cep, @NotNull Pais pais) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.telefone = telefone;
        this.cep = cep;
        this.pais = pais;
    }

    public void setEstado(Estado estado) {
        Assert.notNull(this.pais, "Não é permitido escolher um estado se país for nulo");
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}

