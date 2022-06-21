package uz.online.mahsulotlar.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.online.mahsulotlar.Entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
