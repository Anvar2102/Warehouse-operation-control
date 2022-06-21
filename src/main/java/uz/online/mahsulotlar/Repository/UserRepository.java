package uz.online.mahsulotlar.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.online.mahsulotlar.Entity.Users;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {


    Optional<Users> findByEmail(String username);

    boolean existsByEmail(String email);

}
