package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.entity.CategoryType;
import org.theaz.karabookapi.repository.CategoryTypeRepository;

import java.util.List;

@Service
@Transactional
public class CategoryTypeService {
    @Autowired
    private CategoryTypeRepository categoryTypeRepository;

    public List<CategoryType> findAll() {
        return this.categoryTypeRepository.findAll();
    }
}
