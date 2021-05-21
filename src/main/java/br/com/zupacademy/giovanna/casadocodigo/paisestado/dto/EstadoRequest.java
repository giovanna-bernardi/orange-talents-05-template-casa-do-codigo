package br.com.zupacademy.giovanna.casadocodigo.paisestado.dto;

import br.com.zupacademy.giovanna.casadocodigo.paisestado.Estado;
import br.com.zupacademy.giovanna.casadocodigo.paisestado.Pais;
import br.com.zupacademy.giovanna.casadocodigo.paisestado.PaisRepository;
import br.com.zupacademy.giovanna.casadocodigo.validation.ExistsId;
import br.com.zupacademy.giovanna.casadocodigo.validation.UniqueStateInCountry;
import br.com.zupacademy.giovanna.casadocodigo.validation.UniqueValue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@UniqueStateInCountry(entity = Estado.class)
public class EstadoRequest {

    @NotBlank(message = "O campo nome não pode estar vazio")
    private String nome;

    @ExistsId(entity = Pais.class, fieldName = "id")
    @NotNull(message = "O id do país não pode ser nulo")
    private Long paisId;

    public EstadoRequest(@NotBlank String nome, @NotNull Long paisId) {
        this.nome = nome;
        this.paisId = paisId;
    }

    public Estado converteParaEntidade(PaisRepository repository) {
        @NotNull Optional<Pais> pais = repository.findById(this.paisId);
        return new Estado(this.nome, pais.get());
    }

    public String getNome() {
        return nome;
    }

    public Long getPaisId() {
        return paisId;
    }

    @Override
    public String toString() {
        return "EstadoRequest{" +
                "nome='" + nome + '\'' +
                ", paisId=" + paisId +
                '}';
    }
}
