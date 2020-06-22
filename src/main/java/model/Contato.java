package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContato;
    private String nome;

    @Column(unique = true)
    private String email;
    private String valorEmail;
    private String observacao;

    public Contato() {
    }

    public Contato(Integer idContato, String nome, String email, String valorEmail, String observacao) {
        super();
        this.idContato = idContato;
        this.nome = nome;
        this.email = email;
        this.valorEmail = valorEmail;
        this.observacao = observacao;
    }

    public Contato(String nome, String email, String valorEmail, String observacao) {
    }

    @Id
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

    @Override
    public String toString() {
        return "Contato{" +
                "idContato=" + idContato +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", valorEmail='" + valorEmail + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
