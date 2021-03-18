package it.iad.relazioni.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Merenda {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String descrizione;

    @OneToOne(mappedBy = "merenda")
    private Alunno alunno;
            
    public Merenda() {
    }

    public Merenda(String descrizione) {
        this.descrizione = descrizione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Alunno getAlunno() {
        return alunno;
    }

    public void setAlunno(Alunno alunno) {
        this.alunno = alunno;
    }
    
    @Override
    public String toString() {
        return "Merenda{" + "id=" + id + ", descrizione=" + descrizione + '}';
    }
    
}
