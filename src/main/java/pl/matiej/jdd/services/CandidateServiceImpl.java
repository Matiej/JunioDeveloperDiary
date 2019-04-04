package pl.matiej.jdd.services;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.matiej.jdd.dao.CandidateDao;
import pl.matiej.jdd.dao.RoleDao;
import pl.matiej.jdd.entity.Candidate;
import pl.matiej.jdd.entity.Role;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {

    private final CandidateDao candidateDao;
    private final RoleDao roleDao;

    @Autowired
    public CandidateServiceImpl(CandidateDao candidateDao, RoleDao roleDao) {
        this.candidateDao = candidateDao;
        this.roleDao = roleDao;
    }

    @Override
    public Candidate save(Candidate candidate) throws EntityExistsException, HibernateException {
        if (candidateDao.findByLogin(candidate.getLogin()).isPresent()) {
            log.error("There is an account with login " + candidate.getLogin());
            throw new EntityExistsException("There is an account with login " + candidate.getLogin());
        }

        candidate.setEnabled(true);
        Role admin = roleDao.findByName("ADMIN");
        candidate.setRole(admin);
        admin.getCandidates().add(candidate);
        try {
            Candidate savedCandidate = candidateDao.save(candidate);
            log.info("Saved candidate. Id: " + savedCandidate.getId() + " login: " + savedCandidate.getLogin());
            return savedCandidate;
        } catch (HibernateException e) {
            log.info("Cant save candidate because of some db error ", e.getMessage());
            throw new RuntimeException("Cant save candidate because of some db error");
        }
    }

    @Override
    public Candidate update(Candidate candidate) {
        return null;
    }

    @Override
    public Candidate findById(Long id) throws EntityNotFoundException {
        return candidateDao.findById(id).orElseThrow(()-> new EntityNotFoundException("No Candidate with id: " + id + "found"));
    }

    @Override
    public List<Candidate> findAll() {
        return candidateDao.findAll();
    }

    @Override
    public void delete(Long id) {
        try {
            candidateDao.deleteById(id);
        } catch (EntityNotFoundException e){
            log.error("Can't delete user id: " + id, e);
        }
        log.warn("Deleted user id: " + id);
    }

}
