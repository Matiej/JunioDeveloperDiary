package pl.matiej.jdd.services;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.matiej.jdd.dao.CandidateDao;
import pl.matiej.jdd.dao.JobOfferDao;
import pl.matiej.jdd.entity.Candidate;
import pl.matiej.jdd.entity.EmployerReply;
import pl.matiej.jdd.entity.JobOffer;
import pl.matiej.jdd.entity.ReplyType;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@Slf4j
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferDao jobOfferDao;
    private final CandidateDao candidateDao;

    @Autowired
    public JobOfferServiceImpl(JobOfferDao jobOfferDao, CandidateDao candidateDao) {
        this.jobOfferDao = jobOfferDao;
        this.candidateDao = candidateDao;
    }

    @Override
    public JobOffer save(JobOffer jobOffer) throws HibernateException {
        try {
            JobOffer savedJobOffer = jobOfferDao.save(jobOffer);
            log.info("Job offer saved correct. Id: " + savedJobOffer.getId() + " title: " + savedJobOffer.getTitle());
            return savedJobOffer;
        } catch (HibernateException e) {
            log.error("Can't save job offer because of some db error: ", e);
            throw new RuntimeException("Can't save job offer because of some db error:");
        }
    }

    @Override
    public JobOffer update(JobOffer jobOffer) {

        return null;
    }

    @Override
    public List<JobOffer> findAll() throws HibernateException {

        try {
            List<JobOffer> jobOfferList = jobOfferDao.findAll();
            log.info("Found " + jobOfferList.size() + " job offers");
            return jobOfferList;
        } catch (HibernateException e) {
            log.error("Cat find job offers because of db error", e);
            throw new RuntimeException("Cat find job offers because of db error");
        }
    }

    @Override
    public List<JobOffer> findAllByCandidateId(Long id) {
        return null;
    }

    @Override
    public JobOffer findOneById(Long id) throws EntityNotFoundException {
        Optional<JobOffer> jobOfferDaoById = jobOfferDao.findById(id);
        if (jobOfferDaoById.isPresent()) {
            JobOffer jobOffer = jobOfferDaoById.get();
            log.info("Found job offer id: " + jobOffer.getId());
            return jobOffer;
        } else {
            log.error("No job offer with id: " + id + " found");
            throw new EntityNotFoundException("No job offer with id: " + id + " found");
        }
    }

    @Override
    public boolean delete(JobOffer jobOffer) throws HibernateException {
        boolean isDeleted = false;
        generateJobs(1);
//        try {
//            jobOfferDao.delete(jobOffer);
//            isDeleted = true;
//            log.info("Job offer deleted successful, Title: " + jobOffer.getTitle() + " id: " + jobOffer.getId());
//        } catch (HibernateException e) {
//            log.error("Can't delete job offer", e);
//            throw new RuntimeException("Cant delete job offer " + e.getMessage());
//        }
        return isDeleted;
    }

    private void generateJobs(int noOfJobs) {
        Candidate candidate = candidateDao.findById(1L).get();

        EmployerReply employerReply = new EmployerReply();
        employerReply.setDate(LocalDate.now());
        employerReply.setReplyType(ReplyType.EMAIL);
        employerReply.setReplyContent("CONTENT_CONTENTCONTENTCONTENTCONTENT");

        JobOffer jobOffer = new JobOffer();
//            jobOffer.setCandidate(candidate);
        jobOffer.setCompany("Company__");

        jobOffer.setMustHave("MUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVEMUST HAVE _ " );
        jobOffer.setSalary("0001");
        jobOffer.setSentMethod("email cos tu zmienie");
        jobOffer.setTitle("TITELE "+2);
        jobOffer.setSentDate(LocalDate.now());
        jobOffer.setOfferSaveDate(LocalDateTime.now());
        jobOffer.getEmployerReplies().add(employerReply);
        employerReply.setJobOffer(jobOffer);
        jobOfferDao.save(jobOffer);


//        IntStream.range(0, noOfJobs).forEach(j-> {
//            EmployerReply employerReply = new EmployerReply();
//            employerReply.setDate(LocalDate.now());
//            employerReply.setReplyType(ReplyType.EMAIL);
//            employerReply.setReplyContent("CONTENT_CONTENT+CONTENTCONTENTCONTENTCONTENTCONTENTCONTENTCONTENTCONTENTCONTENT");
//
//            JobOffer jobOffer = new JobOffer();
////            jobOffer.setCandidate(candidate);
//            jobOffer.setCompany("Company__"+j);
//
//            jobOffer.setMustHave("MUST HAVE _ " + j);
//            jobOffer.setSalary(j+"0001");
//            jobOffer.setSentMethod("email cos tu zmienie"+j);
//            jobOffer.setTitle(j+"TITELE "+j+2);
//            jobOffer.setSentDate(LocalDate.now());
//            jobOffer.setOfferSaveDate(LocalDateTime.now());
//            jobOffer.getEmployerReplies().add(employerReply);
//            employerReply.setJobOffer(jobOffer);
//            jobOfferDao.save(jobOffer);
//        });
    }
}
