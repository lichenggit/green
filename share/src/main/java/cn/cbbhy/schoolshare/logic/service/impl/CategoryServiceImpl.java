package cn.cbbhy.schoolshare.logic.service.impl;

import cn.cbbhy.schoolshare.logic.dao.CategoryDao;
import cn.cbbhy.schoolshare.logic.model.Category;
import cn.cbbhy.schoolshare.logic.model.constant.Status;
import cn.cbbhy.schoolshare.logic.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/26 0026.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> searchAll() {
        Category category = new Category();
        category.setStatus(Status.NORMAL);
        return categoryDao.selectByConditions(category);
    }
}
