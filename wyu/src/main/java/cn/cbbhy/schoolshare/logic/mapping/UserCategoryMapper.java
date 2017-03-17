package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.UserCategory;

import java.util.List;

public interface UserCategoryMapper {
    int deleteByPrimaryKey(String categoryId);
    int insert(UserCategory record);
    int insertSelective(UserCategory record);
    UserCategory selectByPrimaryKey(String categoryId);
    int updateByPrimaryKeySelective(UserCategory record);
    int updateByPrimaryKey(UserCategory record);

    /**
     * 查询所有子目录
     * @param categoryId
     * @return
     */
    List<UserCategory> selectSubCategories(String categoryId);
}