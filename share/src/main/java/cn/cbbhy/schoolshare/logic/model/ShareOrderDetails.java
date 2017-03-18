package cn.cbbhy.schoolshare.logic.model;

public class ShareOrderDetails {
    private String id;

    private String shareOrderId;

    private String articleId;

    private Integer articleCount;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShareOrderId() {
        return shareOrderId;
    }

    public void setShareOrderId(String shareOrderId) {
        this.shareOrderId = shareOrderId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}