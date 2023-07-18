package com.aakhya.smartcall.application.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aakhya.smartcall.application.document.entity.Document;
import com.aakhya.smartcall.application.document.entity.DocumentPK;

public interface DocumentRepository extends JpaRepository<Document, DocumentPK>{

}
