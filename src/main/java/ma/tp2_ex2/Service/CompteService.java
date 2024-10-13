package ma.tp2_ex2.Service;

import ma.tp2_ex2.Entities.Compte;
import ma.tp2_ex2.Repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService {
    private final CompteRepository compteRepository;

    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }
    public List<Compte> getAll(){
        return this.compteRepository.findAll();
    }
    public Optional<Compte> getById(Integer id){
        return this.compteRepository.findById(id);
    }
    public List<Compte> getByNom(String nom){
        return this.compteRepository.findAllByNom(nom);
    }
    public List<Compte> getByTel(String tel){
        return this.compteRepository.findAllByTel(tel);
    }
    public List<Compte> getBySolde(Double solde){
        return this.compteRepository.findAllBySolde(solde);
    }
    public void createOrUpdate(Compte compte){
        this.compteRepository.save(compte);
    }
    public void delete(Compte compte){
        this.compteRepository.delete(compte);
    }
    public String retrait(Integer id, Double montant){
       Compte compte = this.compteRepository.getReferenceById(id);
        if (compte.getSolde() >= 100.0){
            compte.setSolde(compte.getSolde() - montant);
            this.compteRepository.save(compte);
            return "Operation avec success";
        }
        return "Insuffisant Solde!";
    }
    public String depot(Integer id, Double montant){
        Compte compte = this.compteRepository.getReferenceById(id);
        if (montant >= 100.0){
            compte.setSolde(compte.getSolde() + montant);
            this.compteRepository.save(compte);
            return "Operation avec success";
        }
        return "Minimum montant est 100 dh!";
    }

}
