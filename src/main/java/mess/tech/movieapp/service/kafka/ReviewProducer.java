package mess.tech.movieapp.service.kafka;

import mess.tech.movieapp.dto.ReviewEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewProducer {
    private final KafkaTemplate<String, ReviewEvent> kafkaTemplate;

    public ReviewProducer(KafkaTemplate<String, ReviewEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendReview(Long reviewId, Long movieId, String text) {
        ReviewEvent event = new ReviewEvent(reviewId, movieId, text);
        kafkaTemplate.send("review.created", reviewId.toString(), event);
    }
}
