package com.codegym.blog.service.impl;

import com.codegym.blog.model.Blog;
import com.codegym.blog.model.Category;
import com.codegym.blog.repository.IBlogRepository;
import com.codegym.blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository iBlogRepository;

    @Override
    public Page<Blog> findAll(Pageable pageble) {
        return iBlogRepository.findAll(pageble);
    }

    @Override
    public void save(Blog blog) {
        iBlogRepository.save(blog);
    }

    @Override
    public Blog findById(int id) {
        return iBlogRepository.findById(id);
    }

    @Override
    public void update(Blog blog) {
        iBlogRepository.update(blog.getTitle(), blog.getContent(), blog.getCreatingDate(), blog.getId());
    }

    @Override
    public void remove(Integer id) {
        iBlogRepository.remove(id);
    }

    @Override
    public Page<Blog> searchByName(String title, String categoryId, Pageable pageble) {
        return iBlogRepository.searchByName("%" + title + "%", "%" + categoryId + "%", pageble);
    }

    @Override
    public List<Blog> findAllByCategoryContaining(Category category) {
        return iBlogRepository.findAllByCategoryContaining(category);
    }

    @Override
    public List<Blog> searchByTitle(String title) {
        return iBlogRepository.searchByTitle(title);
    }
}
