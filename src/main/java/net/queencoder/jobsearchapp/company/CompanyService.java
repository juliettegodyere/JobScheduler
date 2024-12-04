package net.queencoder.jobsearchapp.company;

import java.util.List;

public interface CompanyService {
     public Company createCompany(CompanyDto companyDto);
    public List<Company> getAllCompanys();
    public Company getCompanyById(Long id);
    public Company updateCompany(CompanyDto companyDto, Long id);
    public String deleteCompany(Long id);
}
