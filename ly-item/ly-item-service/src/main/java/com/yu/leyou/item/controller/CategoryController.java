package com.yu.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.yu.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //根据parentId查询类目
    @RequestMapping("/list")
    public ResponseEntity<List<Category>> queryCategoryListByParentId(
            @RequestParam(value = "pid",defaultValue = "0")Long pid){


        try {
            if (pid == null || pid.longValue() < 0){
                //pid为null或者小于等于0，返回400-客户端访问语法错误
                return ResponseEntity.badRequest().build();
            }

            //执行查询操作
            List<Category> categoryList = this.categoryService.queryCategoryListByParentId(pid);
            System.out.println(categoryList.size());
            //查看查询列表是否为空，为空则不存在这个类，响应404-找不到
            if (categoryList == null || categoryList.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            //成功则响应成功
            return ResponseEntity.ok(categoryList);
        }catch (Exception e){
            e.printStackTrace();
        }

        //响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }


}
