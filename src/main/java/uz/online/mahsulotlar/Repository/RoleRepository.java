package uz.online.mahsulotlar.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.online.mahsulotlar.Entity.Enum.RoleNames;
import uz.online.mahsulotlar.Entity.Roles;

public interface RoleRepository extends JpaRepository<Roles,Integer> {

    Roles findByRoleNames(RoleNames roleAdmin);

}
