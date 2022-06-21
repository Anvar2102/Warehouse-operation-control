package uz.online.mahsulotlar.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.online.mahsulotlar.Entity.Outcome;

public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {
}
