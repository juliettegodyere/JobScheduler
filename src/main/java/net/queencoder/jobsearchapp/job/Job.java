package net.queencoder.jobsearchapp.job;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.queencoder.jobsearchapp.company.Company;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "jobs")
@EntityListeners(AuditingEntityListener.class)
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "job_description")
    private String jobDescription;
    @Column(name = "min_salary")
    private String minSalary;
    @Column(name = "max_salary")
    private String maxSalary;
    @Column(name = "location")
    private String location;
    @Column(name = "is_favorite")
    private boolean isFavorite;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "posting_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date postingDate;

    @Column(name = "salary")
    private String salary;

    @Column(name = "job_type")
    @Enumerated(EnumType.STRING)
    private JobTypeEnum jobType;

    @Column(name = "job_reference", nullable = false, unique = true)
    private String jobReference;

    // @JsonIgnoreProperties("jobs") 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;
}
