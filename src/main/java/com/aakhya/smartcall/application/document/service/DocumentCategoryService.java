package com.aakhya.smartcall.application.document.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.document.entity.DocumentCategory;
import com.aakhya.smartcall.application.document.entity.DocumentCategoryPK;
import com.aakhya.smartcall.application.document.repository.DocumentCategoryRepository;


@Service
public class DocumentCategoryService {

	@Autowired
	private DocumentCategoryRepository documentCategoryRepository;
	
	
public List<DocumentCategory> findAllDocumentCategorys(){
		
		List<DocumentCategory> documentCategorys = documentCategoryRepository.findAll();
		
		return documentCategorys;
		
	}


public DocumentCategory findByPrimaryKey(DocumentCategoryPK documentCategoryPK) {
	
	Optional<DocumentCategory> op = documentCategoryRepository.findById(documentCategoryPK);
	
	if(op.isPresent()) {
		
		DocumentCategory dc = op.get();
		
		return dc;
	}
	else {
		return null;
	}
}


public void saveDocumentCategory(DocumentCategory documentCategory) {
	
	if(null!= documentCategory) {
		documentCategoryRepository.save(documentCategory);
		
	}
	
}


	
	
}
