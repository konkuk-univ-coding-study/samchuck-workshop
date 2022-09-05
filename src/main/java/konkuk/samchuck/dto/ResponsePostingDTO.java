package konkuk.samchuck.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ResponsePostingDTO {

    private String success;
    private String message;

    private String author;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private List<ResponseReplyDTO> replies;


}
