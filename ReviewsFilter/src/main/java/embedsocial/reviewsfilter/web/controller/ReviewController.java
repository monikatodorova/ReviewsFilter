package embedsocial.reviewsfilter.web.controller;

import embedsocial.reviewsfilter.model.Review;
import embedsocial.reviewsfilter.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping({"/", "/reviews"})
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getReviewsPage(Model model,
                                 @RequestParam(required = false) boolean highestRatingFirst,
                                 @RequestParam(required = false) String minimumRating,
                                 @RequestParam(required = false) boolean newestFirst,
                                 @RequestParam(required = false) boolean textPriority) {

        System.out.println(highestRatingFirst + " " + minimumRating + " " + newestFirst + " " + textPriority);
        List<Review> reviews = reviewService.findAll();
        if(minimumRating != null) {
            reviews = this.reviewService.filterReviews(highestRatingFirst, Integer.parseInt(minimumRating), newestFirst, textPriority);
        }
        model.addAttribute("reviews", reviews);
        return "reviews";
    }
}
