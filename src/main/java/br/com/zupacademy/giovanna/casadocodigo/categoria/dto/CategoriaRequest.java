package br.com.zupacademy.giovanna.casadocodigo.categoria.dto;

import br.com.zupacademy.giovanna.casadocodigo.categoria.Categoria;
import br.com.zupacademy.giovanna.casadocodigo.validation.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank(message = "O campo nome não pode ser nulo nem vazio")
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "O campo nome deve ser único" )
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
