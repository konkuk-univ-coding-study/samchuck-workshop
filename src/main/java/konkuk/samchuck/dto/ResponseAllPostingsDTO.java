package konkuk.samchuck.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseAllPostingsDTO {
    private String success;
    private String message;
    private List<PostingInfoDTO> postings = new ArrayList<>();

    public ResponseAllPostingsDTO(String success, String message) {
        this.success = success;
        this.message = message;
    }
}
