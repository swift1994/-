package com.twohand.pojo;

/**
 * Created by Administrator on 2017/6/15.
 */
public class Reply {
    private Integer id;
    private  Integer userId;
    private Integer atUserId;
    private Integer commentId;
    private String createAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAtUserId() {
        return atUserId;
    }

    public void setAtUserId(Integer atUserId) {
        this.atUserId = atUserId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt==null? null: createAt.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content==null?null:content.trim();
    }

    private String content;

}
