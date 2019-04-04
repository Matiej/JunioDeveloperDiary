package pl.matiej.jdd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.matiej.jdd.entity.Candidate;
import pl.matiej.jdd.entity.EmployerReply;
import pl.matiej.jdd.entity.SeniorityLevel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferFeDto {

    private Long id;
    @Size(min = 2, message = "I'm sorry dear candidate, name size must be min 2 chars here")
    private String title;
    @Size(min = 2, message = "I'm sorry dear candidate, company size must by min 2 chars here")
    private String company;
    @Size(min = 10, max = 10, message = "The year must entered in this way (yyyy-MM-dd)")
    private String sentDate;
    private LocalDateTime offerSaveDate;
    private String salary;
    private SeniorityLevel seniorityLevel;
//    @NotEmpty
    private String mustHave;
//    @NotEmpty
    private String niceToHave;
    private String otherContent;
    private String sentMethod;
    private Candidate candidate;
    private List<EmployerReply> employerReplies = new ArrayList<>();

}
