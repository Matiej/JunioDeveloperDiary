package pl.matiej.jdd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matiej.jdd.entity.JobOffer;

@Repository
public interface JobOfferDao extends JpaRepository<JobOffer, Long> {
}
