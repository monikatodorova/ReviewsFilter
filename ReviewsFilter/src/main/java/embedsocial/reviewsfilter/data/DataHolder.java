package embedsocial.reviewsfilter.data;

import embedsocial.reviewsfilter.model.Review;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Review> reviews = new ArrayList<>();

    @PostConstruct
    public void init() {
        reviews = DataReader.read();
    }

}
