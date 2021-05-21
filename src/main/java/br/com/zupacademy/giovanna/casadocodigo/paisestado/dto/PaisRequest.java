package br.com.zupacademy.giovanna.casadocodigo.paisestado.dto;

import br.com.zupacademy.giovanna.casadocodigo.paisestado.Pais;
import br.com.zupacademy.giovanna.casadocodigo.validation.UniqueValue;

import javax.validation.constraints.NotBlank;

public class PaisRequest {

    @NotBlank(message = "O campo nome não pode ser vazio")
    @UniqueValue(domainClass = Pais.class, fieldName = "nome")
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pais converteParaEntidade() {
        return new Pais(this.nome);
    }
}
