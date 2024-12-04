package net.queencoder.jobsearchapp.job;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.queencoder.jobsearchapp.company.Company;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDto {
    private String title;
    private String jobDescription;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Date posting_date;
    private String salary;
    private String jobType;
    private String jobReference;
    private boolean isFavorite;
    private Company company;
}
