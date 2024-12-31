package net.queencoder.jobsearchapp.job;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.queencoder.jobsearchapp.company.Company;
import net.queencoder.jobsearchapp.company.CompanyRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServiceImp implements JobService{

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public Job createJob(JobDto jobDto){

        Optional<Company> company = companyRepository.findById(jobDto.getCompany().getId());
        log.info("Dto {}", company);

        Job job = new Job();
        job.setJobDescription(jobDto.getJobDescription());
        job.setLocation(jobDto.getLocation());
        job.setMaxSalary(jobDto.getMaxSalary());
        job.setMinSalary(jobDto.getMinSalary());
        job.setJobReference(jobDto.getJobReference());
        job.setJobType(JobTypeEnum.valueOf(jobDto.getJobType()));
        job.setSalary(jobDto.getSalary());
        job.setTitle(jobDto.getTitle());
        job.setFavorite(jobDto.isFavorite());

        if(company.isPresent()){
            job.setCompany(company.get());
        }else{
            Company newCompany = new Company();
            newCompany.setCompanyDescription(jobDto.getCompany().getCompanyDescription());
            newCompany.setName(jobDto.getCompany().getName());
            job.setCompany(companyRepository.save(newCompany));
        }
        return jobRepository.save(job);
    }
    @Override
    public List<JobResponseDto> getAllJobs(){
        List<JobResponseDto> jobList = jobRepository.findAllJobs();
        return jobList;
        //return jobList.stream().map((job) -> mapJobToDto(job)).collect(Collectors.toList());
    }
    @Override
    public Job getJobById(Long id){
        Job job = jobRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Given id does not exist " + id));
        return job;
    }
    @Override
    public Job updateJob(JobDto jobDto, Long id){
        Job existingjob = jobRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Given id does not exist " + id));
        
        existingjob.setJobDescription(jobDto.getJobDescription());
        existingjob.setLocation(jobDto.getLocation());
        existingjob.setMaxSalary(jobDto.getMaxSalary());
        existingjob.setMinSalary(jobDto.getMinSalary());
        existingjob.setJobReference(jobDto.getJobReference());
        existingjob.setJobType(JobTypeEnum.valueOf(jobDto.getJobType()));
        existingjob.setSalary(jobDto.getSalary());
        existingjob.setTitle(jobDto.getTitle());
        existingjob.setFavorite(jobDto.isFavorite());
        return jobRepository.save(existingjob);
    }
    @Override
    public String deleteJob(Long id){
        Job existingjob = jobRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Given id does not exist " + id));
        jobRepository.delete(existingjob);

        return "Job deleted succesfully";
    }

    @Override
    public List<JobResponseDto> getJobsByTitleAndLocation(String title, String location) {
        List<JobResponseDto> jobResponseDto =  jobRepository.findJobsByTitleAndLocation(title, location);
        return jobResponseDto;
       //return jobResponseDto.stream().map((job) -> mapJobToDto(job)).collect(Collectors.toList());
    }


    // private JobResponseDto mapJobToDto(Job job) {
    //     JobResponseDto dto = new JobResponseDto();
    //     dto.setJobDescription(job.getJobDescription());
    //     dto.setLocation(job.getLocation());
    //     dto.setMaxSalary(job.getMaxSalary());
    //     dto.setMinSalary(job.getMinSalary());
    //     dto.setJobReference(job.getJobReference());
    //     dto.setJobType(String.valueOf(job.getJobType()));
    //     dto.setSalary(job.getSalary());
    //     dto.setTitle(job.getTitle());
    //     dto.setFavorite(job.isFavorite());
    //     //dto.setCompanyDescription(job.getCompanyDescription);
    //     return dto;
    // }
}
