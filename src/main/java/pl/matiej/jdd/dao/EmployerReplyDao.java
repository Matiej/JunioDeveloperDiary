package pl.matiej.jdd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matiej.jdd.entity.EmployerReply;

@Repository
public interface EmployerReplyDao extends JpaRepository<EmployerReply, Long> {
}
