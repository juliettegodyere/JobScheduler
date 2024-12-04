package net.queencoder.jobsearchapp.job;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/v1/jobs")
@AllArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobResponseDto>> getAllJobs(){
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        return new ResponseEntity<Job>(jobService.getJobById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody JobDto jobDto){
        return new ResponseEntity<Job>(jobService.createJob(jobDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@RequestBody JobDto jobDto, @PathVariable Long id){
        return new ResponseEntity<Job>(jobService.updateJob(jobDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        return new ResponseEntity<String>(jobService.deleteJob(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobResponseDto>> getJobsByTitleAndLocation(
        @RequestParam(required = false) String title, @RequestParam(required = false) String location){
        return new ResponseEntity<List<JobResponseDto>>(jobService.getJobsByTitleAndLocation(title, location), HttpStatus.OK);
    }

    // @PostMapping("/uploadCSV")
    // public ResponseEntity<Company> createJobCSV(){
    //     return new ResponseEntity<Company>(companyService.createCompany(companyDto), HttpStatus.CREATED);
    // }
}
