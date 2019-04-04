package pl.matiej.jdd.facade;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.matiej.jdd.entity.JobOffer;
import pl.matiej.jdd.mappers.JobOfferMapper;
import pl.matiej.jdd.model.JobOfferFeDto;
import pl.matiej.jdd.services.JobOfferService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class JddFacade {

    private final JobOfferService jobOfferService;
    private final JobOfferMapper jobOfferMapper;

    @Autowired
    public JddFacade(JobOfferService jobOfferService, JobOfferMapper jobOfferMapper) {
        this.jobOfferService = jobOfferService;
        this.jobOfferMapper = jobOfferMapper;
    }

    public JobOfferFeDto save(JobOfferFeDto jobOfferFeDto) throws HibernateException {
        JobOffer jobOffer = jobOfferMapper.mapToJobOffer(jobOfferFeDto);
        return jobOfferMapper.mapToJobOfferDto(jobOfferService.save(jobOffer));
    }

    public JobOfferFeDto update(JobOfferFeDto jobOfferFeDto) {
        JobOffer jobOffer = jobOfferMapper.mapToJobOffer(jobOfferFeDto);
        return jobOfferMapper.mapToJobOfferDto(jobOfferService.update(jobOffer));
    }

    public List<JobOfferFeDto> findAll() throws HibernateException {
        return jobOfferService.findAll()
                .stream()
                .map(jobOfferMapper::mapToJobOfferDto)
                .collect(toList());
    }

    public List<JobOfferFeDto> findAllByCandidateId(Long id) throws HibernateException {
        return jobOfferService.findAllByCandidateId(id).stream()
                .map(jobOfferMapper::mapToJobOfferDto)
                .collect(toList());
    }

    public JobOfferFeDto findOne(Long id) throws HibernateException, EntityNotFoundException {
        return jobOfferMapper.mapToJobOfferDto(jobOfferService.findOneById(id));
    }

    public boolean delete(JobOfferFeDto jobOfferFeDto) throws HibernateException{
        return jobOfferService.delete(jobOfferMapper.mapToJobOffer(jobOfferFeDto));
    }


}
