package br.com.zupacademy.giovanna.casadocodigo.autor.dto;

import br.com.zupacademy.giovanna.casadocodigo.autor.Autor;

public class AutorDetailResponse {

    private String nome;
    private String descricao;

    public AutorDetailResponse(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
