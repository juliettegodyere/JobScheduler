package net.queencoder.jobsearchapp.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/companies/{companyId}")
@RequiredArgsConstructor
public class ReviewController {
    
    public final ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        System.out.println("The company ID");
        System.out.println(companyId);
        return new ResponseEntity<List<Review>>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReviews(@PathVariable Long companyId, Review review){
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if(isReviewSaved){
            return new ResponseEntity<>("Review Added successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Saved", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReviewById(companyId, reviewId), HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, 
                                                @PathVariable Long reviewId,
                                                @RequestBody Review review
                                                ){
            boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, review);

            if(isReviewUpdated){
                return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Review Not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId){

        boolean isReviewdeleted = reviewService.deleteReview(companyId, reviewId);

        if(isReviewdeleted){
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not deleted", HttpStatus.NOT_FOUND);
    }

}


