package com.springboot.blog.controller;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto newCategoryDto=categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(newCategoryDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(value = "id") Long id){
        CategoryDto categoryDto=categoryService.getCategory(id);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable (value = "id") Long categoryId,@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto,categoryId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable(value = "id")Long categoryID){
        categoryService.deleteCategory(categoryID);
        return ResponseEntity.ok("Category Deleted successfully");
    }
}
