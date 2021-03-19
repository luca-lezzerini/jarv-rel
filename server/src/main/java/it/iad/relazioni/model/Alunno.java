package it.iad.relazioni.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Aula aula;

    @ManyToMany(mappedBy = "alunni")
    private Set<Docente> docenti;

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

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Set<Docente> getDocenti() {
        if (docenti == null) {
            docenti = new HashSet<>();
        }
        return docenti;
    }

    public void setDocenti(Set<Docente> docenti) {
        this.docenti = docenti;
    }

    @Override
    public String toString() {
        return "Alunno{" + "id=" + id + ", nome=" + nome + '}';
    }

}
