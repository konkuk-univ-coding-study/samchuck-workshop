package konkuk.samchuck.dto;

import konkuk.samchuck.domain.Posting;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostingInfoDTO {

    private final Long id;
    private final String title;
    private final String author;
    private final LocalDateTime createDate;

    public PostingInfoDTO(Posting posting) {
        this.id = posting.getId();
        this.title = posting.getTitle();
        this.author = posting.getAuthor().getUserId();
        this.createDate = posting.getCreateDate();
    }
}
