package br.com.zupacademy.giovanna.casadocodigo.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo nome não pode ser nulo nem vazio")
    @Column(nullable = false)
    private String nome;

    @Deprecated
    public Categoria() {
    }

    public Categoria(@NotBlank String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria{" + "nome='" + nome + '\'' + '}';
    }
}
