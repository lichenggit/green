package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.Category;
import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(String categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(String categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * 条件查询
     * @param record
     * @return
     */
    List<Category> selectByConditions(Category record);
}