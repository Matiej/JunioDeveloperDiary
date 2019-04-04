package pl.matiej.jdd.services;

import pl.matiej.jdd.entity.Candidate;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface CandidateService {

    Candidate save(Candidate candidate);

    Candidate update(Candidate candidate);

    Candidate findById(Long id) throws EntityNotFoundException;

    List<Candidate> findAll();

    void delete(Long id);

}
