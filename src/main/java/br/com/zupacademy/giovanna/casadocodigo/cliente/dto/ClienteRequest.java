package br.com.zupacademy.giovanna.casadocodigo.cliente.dto;

import br.com.zupacademy.giovanna.casadocodigo.cliente.Cliente;
import br.com.zupacademy.giovanna.casadocodigo.paisestado.Estado;
import br.com.zupacademy.giovanna.casadocodigo.paisestado.EstadoRepository;
import br.com.zupacademy.giovanna.casadocodigo.paisestado.Pais;
import br.com.zupacademy.giovanna.casadocodigo.paisestado.PaisRepository;
import br.com.zupacademy.giovanna.casadocodigo.validation.CPFOrCNPJ;
import br.com.zupacademy.giovanna.casadocodigo.validation.ExistsId;
import br.com.zupacademy.giovanna.casadocodigo.validation.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class ClienteRequest {

    @NotBlank(message = "O campo e-mail não pode estar vazio")
    @Email(message = "O e-mail deve ter um formato válido")
    @UniqueValue(domainClass = Cliente.class, fieldName = "email")
    private String email;

    @NotBlank(message = "O campo nome não pode estar vazio")
    private String nome;
    @NotBlank(message = "O campo sobrenome não pode estar vazio")
    private String sobrenome;

    @CPFOrCNPJ
    @UniqueValue(domainClass = Cliente.class, fieldName = "documento")
    @NotBlank(message = "O campo documento não pode estar vazio")
    private String documento;

    @NotBlank(message = "O campo endereco não pode estar vazio")
    private String endereco;

    @NotBlank(message = "O campo complemento não pode estar vazio")
    private String complemento;

    @NotBlank(message = "O campo telefone não pode estar vazio")
    private String telefone;

    @NotBlank(message = "O campo cep não pode estar vazio")
    private String cep;

    @NotBlank(message = "O campo cidade não pode estar vazio")
    private String cidade;

    @NotNull(message = "O país não pode ser nulo")
    @ExistsId(entity = Pais.class, fieldName = "id")
    private Long paisId;

    @ExistsId(entity = Estado.class, fieldName = "id")
    private Long estadoId; //(caso aquele pais tenha estado)

    /* se o país tiver estados, um estado precisa ser selecionado
     * estado(caso aquele pais tenha estado) - apenas se o país tiver cadastro de estados
     */

    public ClienteRequest(String email, String nome, String sobrenome, String documento, String endereco, String complemento, String telefone, String cep, String cidade, Long paisId) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.telefone = telefone;
        this.cep = cep;
        this.cidade = cidade;
        this.paisId = paisId;
    }

    public Cliente map(PaisRepository paisRepository, EstadoRepository estadoRepository) {
        @NotNull Optional<Pais> pais = paisRepository.findById(this.paisId);
        Cliente cliente = new Cliente(this.email, this.nome, this.sobrenome, this.documento,
                this.endereco, this.complemento, this.cidade, this.telefone, this.cep,
                pais.get());

        if (estadoId != null) {
            @NotNull Optional<Estado> estado = estadoRepository.findById(this.estadoId);
            cliente.setEstado(estado.get());
        }

        return cliente;
    }

    public Long getPaisId() {
        return paisId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }


}
