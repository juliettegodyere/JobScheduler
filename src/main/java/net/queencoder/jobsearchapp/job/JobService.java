package net.queencoder.jobsearchapp.job;

import java.util.List;

public interface JobService{
    public Job createJob(JobDto jobDto);
    public List<JobResponseDto> getAllJobs();
    public Job getJobById(Long id);
    public Job updateJob(JobDto jobDto, Long id);
    public String deleteJob(Long id);
    public List<JobResponseDto> getJobsByTitleAndLocation(String title, String location);
}

