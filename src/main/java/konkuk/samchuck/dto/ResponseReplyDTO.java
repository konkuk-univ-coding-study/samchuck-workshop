package konkuk.samchuck.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ResponseReplyDTO {
    private String author;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private List<ResponseReplyDTO> reReplies = new ArrayList<>();
}
