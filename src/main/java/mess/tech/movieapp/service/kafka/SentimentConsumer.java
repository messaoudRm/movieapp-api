package mess.tech.movieapp.service.kafka;

import mess.tech.movieapp.dto.SentimentEvent;
import mess.tech.movieapp.entites.Comment;
import mess.tech.movieapp.repository.CommentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SentimentConsumer {

    private final CommentRepository commentRepository;

    public SentimentConsumer(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @KafkaListener(
            topics = "sentiment.analyzed",
            groupId = "movieapp-group"
    )
    public void consume(SentimentEvent event) {
        Comment comment = commentRepository.findById(event.reviewId()).get();
        comment.setSentimentLabel(event.sentiment());
        comment.setSentimentScore(event.score());
        commentRepository.save(comment);
    }
}
