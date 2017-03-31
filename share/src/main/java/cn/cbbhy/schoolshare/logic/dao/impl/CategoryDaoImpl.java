package cn.cbbhy.schoolshare.logic.dao.impl;

import cn.cbbhy.schoolshare.logic.dao.CategoryDao;
import cn.cbbhy.schoolshare.logic.mapping.CategoryMapper;
import cn.cbbhy.schoolshare.logic.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/11/26 0026.
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectByConditions(Category record) {
        return categoryMapper.selectByConditions(record);
    }
}
