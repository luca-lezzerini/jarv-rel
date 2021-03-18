package it.iad.relazioni.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Alunno {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nome;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Merenda merenda;

    public Alunno() {
    }

    public Alunno(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Merenda getMerenda() {
        return merenda;
    }

    public void setMerenda(Merenda merenda) {
        this.merenda = merenda;
    }

    @Override
    public String toString() {
        return "Alunno{" + "id=" + id + ", nome=" + nome + '}';
    }

}
