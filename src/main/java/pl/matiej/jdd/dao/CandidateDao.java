package pl.matiej.jdd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matiej.jdd.entity.Candidate;

import java.util.Optional;

@Repository
public interface CandidateDao extends JpaRepository<Candidate, Long> {

    Optional<Candidate> findByLogin(String email);
}
