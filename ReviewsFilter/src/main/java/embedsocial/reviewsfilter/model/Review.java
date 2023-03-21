package embedsocial.reviewsfilter.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Review {
    private Long id;
    private String reviewId;
    private String reviewFullText;
    private String reviewText;
    private Integer numLikes;
    private Integer numComments;
    private Integer numShares;
    private Long rating;
    private String createdOnText;
    private LocalDateTime reviewCreatedOn;
    private Long reviewCreatedOnTime;
    private String reviewerId;
    private String reviewerUrl;
    private String reviewerName;
    private String reviewerEmail;
    private String sourceType;
    private boolean isVerified;
    private String source;
    private String sourceName;
    private String sourceId;
    private List<String> tags;
    private String href;
    private String logoHref;
    private List<String> photos;


    public Review(Long id, String reviewText, Long rating, LocalDateTime reviewCreatedOn) {
        this.id = id;
        this.reviewText = reviewText;
        this.rating = rating;
        this.reviewCreatedOn = reviewCreatedOn;
    }

    public boolean emptyReviewText() {
        if(this.reviewText.equals("")) return true;
        return false;
    }

}