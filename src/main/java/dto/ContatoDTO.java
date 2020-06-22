package dto;

import model.Contato;

import java.io.Serializable;

public class ContatoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idContato;
    private String nome;
    private String email;
    private String valorEmail;
    private String observacao;

    public ContatoDTO() {
    }

    public ContatoDTO(Contato obj) {
        this.idContato = idContato;
        this.nome = nome;
        this.email = email;
        this.valorEmail = valorEmail;
        this.observacao = observacao;
    }

    public Integer getIdContato() {
        return idContato;
    }

    public void setIdContato(Integer idContato) {
        this.idContato = idContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getValorEmail() {
        return valorEmail;
    }

    public void setValorEmail(String valorEmail) {
        this.valorEmail = valorEmail;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
