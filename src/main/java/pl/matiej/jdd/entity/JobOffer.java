package pl.matiej.jdd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "JOB_OFFER")
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String company;
    private LocalDate sentDate;
    private LocalDateTime offerSaveDate;
    private String salary;
    @Enumerated(value = EnumType.STRING)
    private SeniorityLevel seniorityLevel;
    @Column(length = 5000)
    private String mustHave;
    @Column(length = 5000)
    private String niceToHave;
    @Column(length = 5000)
    private String otherContent;
    private String sentMethod;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CANDIDATE_ID")
    private Candidate candidate;

    @JsonIgnore
    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployerReply> employerReplies = new ArrayList<>();

}
