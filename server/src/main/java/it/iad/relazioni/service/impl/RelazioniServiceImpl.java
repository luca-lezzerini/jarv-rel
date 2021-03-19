package it.iad.relazioni.service.impl;

import it.iad.relazioni.model.Alunno;
import it.iad.relazioni.model.Aula;
import it.iad.relazioni.model.Docente;
import it.iad.relazioni.model.Merenda;
import it.iad.relazioni.repository.AlunnoRepository;
import it.iad.relazioni.repository.AulaRepository;
import it.iad.relazioni.repository.DocenteRepository;
import it.iad.relazioni.repository.MerendaRepository;
import it.iad.relazioni.service.RelazioniService;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelazioniServiceImpl implements RelazioniService {

    @Autowired
    AlunnoRepository alunnoRepository;
    @Autowired
    AulaRepository aulaRepository;
    @Autowired
    MerendaRepository merendaRepository;
    @Autowired
    DocenteRepository docenteRepository;

    @Override
    public void genera() {
        docenteRepository.deleteAll();
        alunnoRepository.deleteAllInBatch();
        aulaRepository.deleteAllInBatch();
        merendaRepository.deleteAllInBatch();

        Alunno alu1 = new Alunno("Mario");
        Alunno alu2 = new Alunno("Elisa");
        Alunno alu3 = new Alunno("Gino");
        alu1 = alunnoRepository.save(alu1);
        alu2 = alunnoRepository.save(alu2);
        alu3 = alunnoRepository.save(alu3);

        Merenda m1 = new Merenda("Pizza");
        Merenda m2 = new Merenda("Hamburger");
        Merenda m3 = new Merenda("Cozze");
        m1 = merendaRepository.save(m1);
        m2 = merendaRepository.save(m2);
        m3 = merendaRepository.save(m3);

        Docente d1 = new Docente("Luca", "Lezzerini");
        Docente d2 = new Docente("Marie", "Curie");
        Docente d3 = new Docente("Albert", "Einstein");
        d1 = docenteRepository.save(d1);
        d2 = docenteRepository.save(d2);
        d3 = docenteRepository.save(d3);

        // associo alunni e docenti: a1,a2 -> d1; a1, a2, a3 -> d2; a2,a3 -> d3
        associaDocenteAlunni(d1, alu1, alu2);
        associaDocenteAlunni(d2, alu1, alu2, alu3);
        associaDocenteAlunni(d3, alu2, alu3);

        // associo alunni e merende
        alu1.setMerenda(m1);
        alunnoRepository.save(alu1);
        m1.setAlunno(alu1);
        merendaRepository.save(m1);
        alu2.setMerenda(m2);
        alunnoRepository.save(alu2);
        m2.setAlunno(alu2);
        merendaRepository.save(m2);
        alu3.setMerenda(m3);
        alunnoRepository.save(alu3);
        m3.setAlunno(alu3);
        merendaRepository.save(m3);
        System.out.println("----------------------");

        // recupero merenda da alu2
        Alunno alx = alunnoRepository.getOne(alu2.getId());
        Merenda mx = alx.getMerenda();
        System.out.println(alx);
        System.out.println(mx);
        System.out.println("----------------------");

        // recupero alunno da m3
        Merenda mex = merendaRepository.getOne(m3.getId());
        Alunno ax = mex.getAlunno();
        System.out.println(mex);
        System.out.println(ax);

        // creo due aule
        Aula a1 = new Aula("VA");
        Aula a2 = new Aula("VB");
        a1 = aulaRepository.save(a1);
        a2 = aulaRepository.save(a2);

        // associo gli alunni alle aule
        a1.setAlunni(null);
        List<Alunno> lx = a1.getAlunni();
        lx.add(alu1);
        lx.add(alu2);
        aulaRepository.save(a1);
        alu1.setAula(a1);
        alu2.setAula(a1);
        alunnoRepository.save(alu1);
        alunnoRepository.save(alu2);

        // recupero tutti gli alunni e, per ognuno, stampo tutti i suoi docenti
        List<Alunno> lalu = alunnoRepository.findAll();
//        for (Alunno alu : lalu) {
        lalu.forEach(a -> {
            System.out.println("Alunno: " + a.getNome());
            Set<Docente> docs = a.getDocenti();
            docs.forEach(d -> System.out.println("\t\t Docente: " + d.getCognome()));
        });

        // recupero tutti i docenti e stampare gli alunni di ciascuno
        List<Docente> ld = docenteRepository.findAll();
        ld.forEach(d -> {
            System.out.println("Docente: " + d.getCognome());
//            Set<Alunno> alus = d.getAlunni();
//            alus.forEach(a -> {
//                System.out.println("\t\t Alunno: " + a.getNome());
//            });
            d.getAlunni().forEach(a -> System.out.println("\t\t Alunno: " + a.getNome()));
        });

        // partendo dalle merende, recuperare l'alunno associato e stamparne la classe e i docenti
        merendaRepository.findAll()
                .forEach(mrn -> {
                    Alunno alux = mrn.getAlunno();
                    Aula aula = alux.getAula();
                    System.out.println("L'alunno " + alux.getNome() + " oggi ha per merenda " + mrn.getDescrizione() + " nell'aula " + aula.getCodice());
                    alux.getDocenti().forEach(d -> System.out.println("\t\t Docente: " + d.getCognome()));
                });
    }

    private void associaDocenteAlunni(Docente d, Alunno... a) {
        Set<Alunno> sa = d.getAlunni();
        for (Alunno alu : a) {
            sa.add(alu);
        }
        docenteRepository.save(d);
        for (Alunno alu : a) {
            Set<Docente> sd = alu.getDocenti();
            sd.add(d);
            alunnoRepository.save(alu);
        }
    }

    private void associaAulaAlunni(Aula a, Alunno... alu) {
        Collection<Alunno> calu = a.getAlunni();
        for (Alunno alunno : alu) {
            calu.add(alunno);
            alunno.setAula(a);
            alunnoRepository.save(alunno);
        }
        aulaRepository.save(a);
    }

}
