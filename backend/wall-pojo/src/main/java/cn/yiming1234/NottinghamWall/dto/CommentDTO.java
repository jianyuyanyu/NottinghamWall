package cn.yiming1234.NottinghamWall.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentDTO {

    private Integer id;
    private Integer topicId;
    private Integer userId;
    private String username;
    private String avatar;
    private String content;
    private Integer parentId;
    private Boolean isLiked;
    private Integer likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private StudentDTO user;
    private List<CommentDTO> replies;

}
