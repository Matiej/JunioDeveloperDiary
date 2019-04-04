package pl.matiej.jdd.services;

import org.hibernate.HibernateException;
import pl.matiej.jdd.entity.JobOffer;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface JobOfferService {

    JobOffer save(JobOffer jobOffer) throws HibernateException;

    JobOffer update(JobOffer jobOffer) throws HibernateException;

    List<JobOffer> findAll() throws HibernateException;

    List<JobOffer> findAllByCandidateId(Long id);

    JobOffer findOneById(Long id)throws HibernateException, EntityNotFoundException;

    boolean delete(JobOffer jobOffer) throws HibernateException;

}
