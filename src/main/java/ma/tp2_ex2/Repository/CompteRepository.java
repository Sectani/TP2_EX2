package ma.tp2_ex2.Repository;

import ma.tp2_ex2.Entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte,Integer> {
    List<Compte> findAllByNom(String nom);
    List<Compte> findAllByTel(String tel);
    List<Compte> findAllBySolde(Double sole);

}
