package net.queencoder.jobsearchapp.review;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.queencoder.jobsearchapp.company.Company;
import net.queencoder.jobsearchapp.company.CompanyDto;
import net.queencoder.jobsearchapp.company.CompanyService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    public final ReviewRepository reviewRepository;
    public final CompanyService companyService;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        System.out.println(reviews);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);

        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if (companyService.getCompanyById(companyId) != null) {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (companyService.getAllCompanys() != null && reviewRepository.existsById(reviewId)) {
            Review review = reviewRepository.findById(reviewId).orElse(null);

            if (review != null) {
                Company company = review.getCompany();
                company.getReviews().remove(review);

                CompanyDto companyDto = CompanyDto.builder()
                        .name(company.getName())
                        .description(company.getCompanyDescription())
                        .id(company.getId())
                        .jobs(null)
                        .build();

                companyService.updateCompany(companyDto, reviewId);

                reviewRepository.deleteById(reviewId);
                return true;
            }
        }
        return false;
    }

}
