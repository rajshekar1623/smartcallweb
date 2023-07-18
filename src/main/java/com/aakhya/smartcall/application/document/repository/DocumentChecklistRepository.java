package com.aakhya.smartcall.application.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aakhya.smartcall.application.document.entity.DocumentChecklist;
import com.aakhya.smartcall.application.document.entity.DocumentChecklistPK;

public interface DocumentChecklistRepository extends JpaRepository<DocumentChecklist, DocumentChecklistPK>{

}
