package br.com.zupacademy.giovanna.casadocodigo.paisestado;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo nome não pode estar vazio")
    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @NotNull(message = "O campo pais não pode ser nulo")
    private Pais pais;

    @Deprecated
    public Estado() {
    }

    public Estado(@NotBlank String nome, @NotNull Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "nome='" + nome + '\'' +
                ", pais=" + pais +
                '}';
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return id.equals(estado.id) && nome.equals(estado.nome) && pais.equals(estado.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, pais);
    }
}
