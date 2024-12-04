package net.queencoder.jobsearchapp.company;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.queencoder.jobsearchapp.job.JobDto;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {
    private Long id;
    private String name;
    private String description;
    private List<JobDto> jobs;
}

// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
// class JobDto {
//     private Long id;
//     private String title;
//     private String description;
//     private String minSalary;
//     private String maxSalary;
//     private String location;

//     // Getters and setters
// }
