package br.com.zupacademy.giovanna.casadocodigo.livro.dto;

import br.com.zupacademy.giovanna.casadocodigo.autor.dto.AutorDetailResponse;
import br.com.zupacademy.giovanna.casadocodigo.livro.Livro;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class LivroDetailResponse {

    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private Integer numeroPaginas;
    private String isbn;
    private String dataPublicacao;
    private AutorDetailResponse autor;

    public LivroDetailResponse(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.isbn = livro.getIsbn();
        this.dataPublicacao = livro.getDataPublicacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.autor = new AutorDetailResponse(livro.getAutor());
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public AutorDetailResponse getAutor() {
        return autor;
    }
}
