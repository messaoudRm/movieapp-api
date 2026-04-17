package mess.tech.movieapp.service;

import mess.tech.movieapp.service.kafka.ReviewProducer;
import org.springframework.stereotype.Service;

@Service
public class SentimentAnalyzeService {

    private final ReviewProducer reviewProducer;

    public SentimentAnalyzeService(ReviewProducer reviewProducer) {
        this.reviewProducer = reviewProducer;
    }

    public void analyzeSentiment(Long reviewId, Long movieId, String text) {
        reviewProducer.sendReview(reviewId, movieId, text);
    }

}

