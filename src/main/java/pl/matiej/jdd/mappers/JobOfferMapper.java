package pl.matiej.jdd.mappers;

import org.springframework.stereotype.Component;
import pl.matiej.jdd.entity.JobOffer;
import pl.matiej.jdd.model.JobOfferFeDto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class JobOfferMapper {

DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public JobOfferFeDto mapToJobOfferDto(JobOffer jobOffer) {
        return JobOfferFeDto.builder()
                .id(jobOffer.getId())
                .title(jobOffer.getTitle())
                .company(jobOffer.getCompany())
                .sentDate(jobOffer.getSentDate().toString())
                .offerSaveDate(LocalDateTime.now())
                .salary(jobOffer.getSalary())
                .seniorityLevel(jobOffer.getSeniorityLevel())
                .mustHave(jobOffer.getMustHave())
                .niceToHave(jobOffer.getNiceToHave())
                .otherContent(jobOffer.getOtherContent())
                .sentMethod(jobOffer.getSentMethod())
                .candidate(jobOffer.getCandidate())
                .employerReplies(jobOffer.getEmployerReplies())
                .build();
    }

    public JobOffer mapToJobOffer(JobOfferFeDto jobOfferFeDto) {
        return JobOffer.builder()
                .id(jobOfferFeDto.getId())
                .title(jobOfferFeDto.getTitle())
                .company(jobOfferFeDto.getCompany())
                .sentDate(LocalDate.parse(jobOfferFeDto.getSentDate(),format))
                .offerSaveDate(LocalDateTime.now())
                .salary(jobOfferFeDto.getSalary())
                .seniorityLevel(jobOfferFeDto.getSeniorityLevel())
                .mustHave(jobOfferFeDto.getMustHave())
                .niceToHave(jobOfferFeDto.getNiceToHave())
                .otherContent(jobOfferFeDto.getOtherContent())
                .sentMethod(jobOfferFeDto.getSentMethod())
                .candidate(jobOfferFeDto.getCandidate())
                .employerReplies(jobOfferFeDto.getEmployerReplies())
                .build();
    }
}
