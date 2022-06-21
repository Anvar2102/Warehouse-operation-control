package uz.online.mahsulotlar.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.online.mahsulotlar.Entity.Product;
import uz.online.mahsulotlar.Entity.ProductAmountUser;
import uz.online.mahsulotlar.Entity.Users;

import java.util.Optional;

public interface ProductAmountRepo extends JpaRepository<ProductAmountUser,Integer> {

    Optional<ProductAmountUser> findByUsernameAndProduct(String username, Product product);
}
