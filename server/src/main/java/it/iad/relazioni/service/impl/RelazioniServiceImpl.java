package it.iad.relazioni.service.impl;

import it.iad.relazioni.model.Alunno;
import it.iad.relazioni.model.Merenda;
import it.iad.relazioni.repository.AlunnoRepository;
import it.iad.relazioni.repository.MerendaRepository;
import it.iad.relazioni.service.RelazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelazioniServiceImpl implements RelazioniService {

    @Autowired
    AlunnoRepository alunnoRepository;
    @Autowired
    MerendaRepository merendaRepository;

    @Override
    public void genera() {
        alunnoRepository.deleteAllInBatch();
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
    }

}
