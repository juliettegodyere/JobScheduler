package net.queencoder.jobsearchapp.company;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.*;

@RestController
@RequestMapping("/api/v1/companys")
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanys(){
        return ResponseEntity.ok(companyService.getAllCompanys());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        return new ResponseEntity<Company>(companyService.getCompanyById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody CompanyDto companyDto){
        return new ResponseEntity<Company>(companyService.createCompany(companyDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@RequestBody CompanyDto companyDto, @PathVariable Long id){
        return new ResponseEntity<Company>(companyService.updateCompany(companyDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        return new ResponseEntity<String>(companyService.deleteCompany(id), HttpStatus.OK);
    }
}
