package net.queencoder.jobsearchapp.company;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import net.queencoder.jobsearchapp.job.Job;
import net.queencoder.jobsearchapp.review.Review;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "company",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"company_name"})})
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false, unique = true)
    private String name;
    @Column(name = "company_description")
    private String companyDescription;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Job> jobs;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;
}
