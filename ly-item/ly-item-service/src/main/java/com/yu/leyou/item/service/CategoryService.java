package com.yu.leyou.item.service;


import com.leyou.item.pojo.Category;
import com.yu.leyou.item.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    // 根据parentId查询子类目
    public List<Category> queryCategoryListByParentId(Long pid){
        Category record = new Category();
        record.setParentId(pid);

        /*
        //排查错误，categoryMapper为空，未加Autowire
        if (categoryMapper == null){
            System.out.println(1111);
        }

        List<Category> l = categoryMapper.select(record);

        return l;
        */

        //JPA提供方法，返回List
        return this.categoryMapper.select(record);
    }

}
