package it.iad.relazioni.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Docente {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nome;
    @Column
    private String cognome;

    @ManyToMany
    @JoinTable(name = "alu_doc")
    private Set<Alunno> alunni;

    public Docente() {
    }

    public Docente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Set<Alunno> getAlunni() {
        if (alunni == null) {
            alunni = new HashSet<>();
        }
        return alunni;
    }

    public void setAlunni(Set<Alunno> alunni) {
        this.alunni = alunni;
    }

    @Override
    public String toString() {
        return "Docente{" + "id=" + id + ", nome=" + nome + ", cognome=" + cognome + '}';
    }

}
