package br.com.zupacademy.giovanna.casadocodigo.autor.dto;

import br.com.zupacademy.giovanna.casadocodigo.autor.Autor;
import br.com.zupacademy.giovanna.casadocodigo.validation.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorRequest {

    @NotBlank(message = "O campo nome não pode ser nulo nem vazio")
    private String nome;

    @NotBlank(message = "O campo e-mail não pode ser nulo nem vazio")
    @Email(message = "O e-mail deve ter um formato válido")
    @UniqueValue(domainClass = Autor.class, fieldName = "email", message = "O campo e-mail deve ser único")
    private String email;

    @NotBlank(message = "O campo descricao não pode ser nulo nem vazio")
    @Size(max=400, message = "Máximo de 400 caracteres")
    private String descricao;

    public AutorRequest(@NotBlank String nome,  @NotBlank @Email String email,  @NotBlank @Size(max=400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor converteParaEntidade() {
        return new Autor(this.nome, this.email, this.descricao);
    }

    public String getEmail() {
        return email;
    }
}
