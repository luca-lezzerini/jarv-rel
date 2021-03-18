package it.iad.relazioni.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Aula {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String codice;
    
    @OneToMany(mappedBy = "aula")
    private List<Alunno> alunni;

    public Aula() {
    }

    public Aula(String codice) {
        this.codice = codice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public List<Alunno> getAlunni() {
        if (alunni == null){
            alunni = new ArrayList<>();
        }
        return alunni;
    }

    public void setAlunni(List<Alunno> alunni) {
        if (alunni == null){
            System.out.println("Uheeee! Null nun c'entra!!!");
//            throw new IllegalArgumentException();
        }
        this.alunni = alunni;
    }

    @Override
    public String toString() {
        return "Aula{" + "id=" + id + ", codice=" + codice + '}';
    }

}
