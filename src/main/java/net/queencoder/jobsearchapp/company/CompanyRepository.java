package net.queencoder.jobsearchapp.company;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long>{
    @Query("SELECT DISTINCT c FROM Company c LEFT JOIN FETCH c.jobs")
    List<Company> findAllWithJobs();

    Optional<Company> findByName(String name);
}
