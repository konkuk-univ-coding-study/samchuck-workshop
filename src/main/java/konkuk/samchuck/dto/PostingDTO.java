package konkuk.samchuck.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostingDTO {

    @NotNull
    private String title;

    @NotNull
    private String content;
}
