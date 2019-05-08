package com.test.web.function;

import com.test.entity.Category;
import com.test.service.BusinessService;
import com.test.service.impl.BusinessServiceImpl;

/**
 * Created by Administrator on 2017/5/10.
 */
public class MyFunction {
    public static String getCategoryId(String categoryId){
        BusinessService bs = new BusinessServiceImpl();
        if(categoryId != null||!"".equals(categoryId)){
            Category category = bs.findCategoryById(categoryId);
            return category.getName();
        }
        return "";
    }
}
