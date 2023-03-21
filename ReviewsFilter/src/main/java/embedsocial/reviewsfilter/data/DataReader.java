package embedsocial.reviewsfilter.data;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import embedsocial.reviewsfilter.model.Review;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class DataReader {
    public static List<Review> read() {

        List<Review> reviews = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/resources/data/reviews.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray reviewList = (JSONArray) obj;

            reviewList.forEach( item -> {
                JSONObject object = (JSONObject) item;
                LocalDateTime createdOn = LocalDateTime.parse((String) object.get("reviewCreatedOnDate"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+00:00"));
                Review review = new Review((Long) object.get("id"), (String) object.get("reviewText"), (Long) object.get("rating"), createdOn);

                reviews.add(review);
            } );
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return reviews;
    }
}
