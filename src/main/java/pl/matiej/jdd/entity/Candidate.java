package pl.matiej.jdd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String matchingPassword;
    private LocalDateTime registerDate;
    private boolean enabled;

    @JsonIgnore
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JobOffer> jobOffers = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;
}
