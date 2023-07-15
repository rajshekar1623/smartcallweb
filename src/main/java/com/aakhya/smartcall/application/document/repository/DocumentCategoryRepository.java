package com.aakhya.smartcall.application.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aakhya.smartcall.application.document.entity.DocumentCategory;
import com.aakhya.smartcall.application.document.entity.DocumentCategoryPK;

public interface DocumentCategoryRepository extends JpaRepository<DocumentCategory, DocumentCategoryPK>{

}
