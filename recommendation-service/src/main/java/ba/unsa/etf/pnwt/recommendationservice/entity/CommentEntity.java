package ba.unsa.etf.pnwt.recommendationservice.entity;

import ba.unsa.etf.pnwt.recommendationservice.constants.DatabaseConstants;
import jakarta.persistence.*;

@Entity
@Table(schema = DatabaseConstants.RECOMMENDATION_SERVICE_SCHEMA, name="comment")
public class CommentEntity {
    @Id
    @SequenceGenerator(
            name="comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_sequence")
    private Long id;
}
