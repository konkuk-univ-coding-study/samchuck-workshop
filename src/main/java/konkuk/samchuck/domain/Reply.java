package konkuk.samchuck.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Reply")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posting_id")
    private Posting posting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "re_reply_id")
    private Reply reReplyParent;

    @OneToMany(mappedBy = "reReplyParent")
    private List<Reply> reReplies = new ArrayList<>();

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

}
