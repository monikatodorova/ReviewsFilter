package embedsocial.reviewsfilter.repository;

import embedsocial.reviewsfilter.data.DataHolder;
import embedsocial.reviewsfilter.model.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {

    public List<Review> findAll() {
        return DataHolder.reviews;
    }

}
