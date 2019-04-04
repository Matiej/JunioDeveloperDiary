package pl.matiej.jdd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.matiej.jdd.entity.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByName(String roleName);
}
