package com.aakhya.smartcall.application.document.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.document.entity.DocumentChecklist;
import com.aakhya.smartcall.application.document.entity.DocumentChecklistPK;
import com.aakhya.smartcall.application.document.repository.DocumentChecklistRepository;


@Service
public class DocumentChecklistService {

	
	
	@Autowired
	private DocumentChecklistRepository documentChecklistRepository;
	
	
public List<DocumentChecklist> findAllDocumentChecklists(){
		
		List<DocumentChecklist> documentChecklists = documentChecklistRepository.findAll();
		
		return documentChecklists;
		
	}


public DocumentChecklist findByPrimaryKey(DocumentChecklistPK documentChecklistPK) {
	
	Optional<DocumentChecklist> op = documentChecklistRepository.findById(documentChecklistPK);
	
	if(op.isPresent()) {
		
		DocumentChecklist dcl = op.get();
		
		return dcl;
	}
	else {
		return null;
	}
}


public void saveDocumentChecklist(DocumentChecklist documentChecklist) {
	
	if(null!= documentChecklist) {
		documentChecklistRepository.save(documentChecklist);
		
	}
	
}


	
	
}
