package br.com.api.desafiometa.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contatos")
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;
    private String tipoDeContato;
    private String valorContato;
    private String observacao;

    public Contato() {
    }

    public Contato(Integer id, String nome, String tipoDeContato, String valorContato, String observacao) {
        super();
        this.id = id;
        this.nome = nome;
        this.tipoDeContato = tipoDeContato;
        this.valorContato = valorContato;
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoDeContato() {
        return tipoDeContato;
    }

    public void setTipoDeContato(String tipoDeContato) {
        this.tipoDeContato = tipoDeContato;
    }

    public String getValorContato() {
        return valorContato;
    }

    public void setValorContato(String valorContato) {
        this.valorContato = valorContato;
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
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoDeContato='" + tipoDeContato + '\'' +
                ", valorContato='" + valorContato + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
