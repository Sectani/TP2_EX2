package ma.tp2_ex2.Controller;

import ma.tp2_ex2.Entities.Compte;
import ma.tp2_ex2.Service.CompteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Comptes")
public class CompteController {
    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<Compte> getAllCompte(){
        return this.compteService.getAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Compte> getCompteById(@PathVariable Integer id){
        return this.compteService.getById(id);
    }

    @GetMapping("/nom/{nom}")
    public List<Compte> getCompteByNom(@PathVariable String nom){
        return this.compteService.getByNom(nom);
    }

    @GetMapping("/tel/{tel}")
    public List<Compte> getCompteByTel(@PathVariable String tel){
        return this.compteService.getByTel(tel);
    }

    @GetMapping("/solde/{solde}")
    public List<Compte> getCompteBySolde(@PathVariable Double solde){
        return this.compteService.getBySolde(solde);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public void addCompte(@RequestBody Compte compte){
        this.compteService.createOrUpdate(compte);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/modify/{id}")
    public void modifyCompte(@PathVariable Integer id, @RequestBody Compte compte){
        compte.setId(id);
        this.compteService.createOrUpdate(compte);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/remove/{id}")
    public void deleteCompte(@PathVariable Integer id){
        Optional<Compte> compte1;
        compte1 = this.compteService.getById(id);
        this.compteService.delete(compte1.get());
    }

    @PutMapping("/retrait/{id}")
    public String retrait(@PathVariable Integer id, @RequestParam Double montant){
        return this.compteService.retrait(id,montant);
    }

    @PutMapping("/depot/{id}")
    public String depot(@PathVariable Integer id, @RequestParam Double montant){
        return this.compteService.depot(id,montant);
    }

}
