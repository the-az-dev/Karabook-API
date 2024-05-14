package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.entity.CategoryParent;
import org.theaz.karabookapi.repository.CategoryParentRepository;

import java.util.List;

@Service
@Transactional
public class CategoryParentService {
    @Autowired
    private CategoryParentRepository categoryParentRepository;

    public List<CategoryParent> findAll() {
        return categoryParentRepository.findAll();
    }

    public void save(CategoryParent categoryParent) {
        categoryParentRepository.save(categoryParent);
    }
}
