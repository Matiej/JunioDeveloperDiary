package pl.matiej.jdd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class EmployerReply {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    @Enumerated(value = EnumType.STRING)
    private ReplyType replyType;
    @Column(length = 5000)
    private String replyContent;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "OFFER_ID")
    private JobOffer jobOffer;

}
