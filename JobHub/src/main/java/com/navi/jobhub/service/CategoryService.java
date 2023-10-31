package com.navi.jobhub.service;

import com.navi.jobhub.data.*;
import com.navi.jobhub.model.*;

import java.util.List;

public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService(){
        categoryDAO = new CategoryDAO();
    }

    public List<Category> categoryList(){
        return categoryDAO.select();
    }

    public Category viewCategory(int id){
        return categoryDAO.viewCategory(id);
    }

    public void create(Category category){
        categoryDAO.insert(category);
    }

    public void update(Category category){
        categoryDAO.update(category);
    }

}
