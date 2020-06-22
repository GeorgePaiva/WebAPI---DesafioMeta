package dto;

import java.io.Serializable;

public class ContatoNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idContato;
    private String nome;

    private String email;
    private String valorEmail;
    private String observacao;

    public ContatoNewDTO() {
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
