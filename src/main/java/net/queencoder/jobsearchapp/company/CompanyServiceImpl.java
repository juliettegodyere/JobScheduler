package net.queencoder.jobsearchapp.company;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.queencoder.jobsearchapp.job.Job;
import net.queencoder.jobsearchapp.job.JobRepository;
import net.queencoder.jobsearchapp.job.JobTypeEnum;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;

    @Override
    public Company createCompany(CompanyDto companyDto) {
        // List<Job> jobs = companyDto.getJobs().stream()
        //         .map(jobDto -> {
        //             Job job = Job.builder()
        //             .description(jobDto.getDescription())
        //             .location(jobDto.getLocation())
        //             .maxSalary(jobDto.getMaxSalary())
        //             .jobReference(jobDto.getJobReference())
        //             .jobtype(JobTypeEnum.valueOf(jobDto.getJobtype()))
        //             .salary(jobDto.getSalary())
        //             .title(jobDto.getTitle())
        //             .build();
        //             return job;
        //         })
        //         .collect(Collectors.toList());
        // jobRepository.saveAll(jobs);

        Company company = new Company();
        company.setName(companyDto.getName());
        company.setCompanyDescription(companyDto.getDescription());
        // company.setJobs(jobs); 
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanys() {
        return companyRepository.findAllWithJobs();
    }

    @Override
    public Company getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Given id does not exist " + id));
        return company;
    }

    @Override
    public Company updateCompany(CompanyDto companyDto, Long id) {
        Company existingCompany = companyRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Given id does not exist " + id));
        
        existingCompany.setCompanyDescription(companyDto.getDescription());
        existingCompany.setName(companyDto.getName());
        List<Job> jobs = companyDto.getJobs().stream()
        .map(jobDto -> {
            Job job = Job.builder()
            .jobDescription(jobDto.getJobDescription())
            .location(jobDto.getLocation())
            .maxSalary(jobDto.getMaxSalary())
            .minSalary(jobDto.getMinSalary())
            .title(jobDto.getTitle())
            .build();
            return job;
        })
        .collect(Collectors.toList());
        existingCompany.setJobs(jobs);
        return companyRepository.save(existingCompany);
    }

    @Override
    public String deleteCompany(Long id) {
        Company existingCompamy = companyRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Given id does not exist " + id));
        companyRepository.delete(existingCompamy);

        return "Job deleted succesfully";
    }
    
}
