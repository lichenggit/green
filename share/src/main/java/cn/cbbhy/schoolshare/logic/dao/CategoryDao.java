package cn.cbbhy.schoolshare.logic.dao;

import cn.cbbhy.schoolshare.logic.model.Category;

import java.util.List;

/**
 * Created by Administrator on 2016/11/26 0026.
 */
public interface CategoryDao {
    List<Category> selectByConditions(Category record);
}
