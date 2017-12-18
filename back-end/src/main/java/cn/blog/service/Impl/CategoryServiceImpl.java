package cn.blog.service.Impl;

import cn.blog.bean.Category;
import cn.blog.bean.CategoryExample;
import cn.blog.dao.CategoryMapper;
import cn.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategory() {
        return categoryMapper.selectByExample(new CategoryExample());
    }
}
