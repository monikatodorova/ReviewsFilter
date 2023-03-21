package embedsocial.reviewsfilter.service;

import embedsocial.reviewsfilter.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll();
    List<Review> filterReviews(boolean highestRatingFirst, Integer minimumRating, boolean newestFirst, boolean textPriority);
}
