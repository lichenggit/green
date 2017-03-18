package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.ArticleScan;

import java.util.List;

public interface ArticleScanMapper {
    int deleteByPrimaryKey(String id);

    int insert(ArticleScan record);

    int insertSelective(ArticleScan record);

    ArticleScan selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArticleScan record);

    int updateByPrimaryKey(ArticleScan record);

    List<ArticleScan> listArticleScanByUserId(String userId);
}