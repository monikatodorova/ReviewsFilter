package embedsocial.reviewsfilter.service.impl;

import embedsocial.reviewsfilter.model.Review;
import embedsocial.reviewsfilter.repository.ReviewRepository;
import embedsocial.reviewsfilter.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> filterReviews(boolean highestRatingFirst, Integer minimumRating, boolean newestFirst, boolean textPriority) {
        List<Review> reviews = this.reviewRepository.findAll();

        Comparator<Review> comparatorTextPriority = (object1, object2) -> {
            if(textPriority) {
                if(object1.emptyReviewText() && !object2.emptyReviewText()) {
                    return 1;
                } else if (!object1.emptyReviewText() && object2.emptyReviewText())
                    return -1;
                return 0;
            }
            return 0;
        };

        Comparator<Review> comparatorRating = (object1, object2) -> {
            if(highestRatingFirst)
                return (-1) * object1.getRating().compareTo(object2.getRating());
            else
                return object1.getRating().compareTo(object2.getRating());
        };

        Comparator<Review> comparatorDate = (object1, object2) -> {
            if(newestFirst)
                return (-1) * object1.getReviewCreatedOn().compareTo(object2.getReviewCreatedOn());
            else
                return object1.getReviewCreatedOn().compareTo(object2.getReviewCreatedOn());
        };

        return reviews.stream().filter(review -> review.getRating() >= minimumRating)
                .sorted(comparatorTextPriority
                        .thenComparing(comparatorRating)
                        .thenComparing(comparatorDate))
                .collect(Collectors.toList());
    }

}
