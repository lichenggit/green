package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.ArticleComment;

public interface ArticleCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(ArticleComment record);

    int insertSelective(ArticleComment record);

    ArticleComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArticleComment record);

    int updateByPrimaryKey(ArticleComment record);
}