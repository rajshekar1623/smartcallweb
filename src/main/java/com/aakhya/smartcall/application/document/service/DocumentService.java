package com.aakhya.smartcall.application.document.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.document.entity.Document;
import com.aakhya.smartcall.application.document.entity.DocumentPK;
import com.aakhya.smartcall.application.document.repository.DocumentRepository;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;

	public List<Document> findAllDocuments() {

		List<Document> documents = documentRepository.findAll();

		return documents;

	}

	public Document findByPrimaryKey(DocumentPK documentPK) {

		Optional<Document> op = documentRepository.findById(documentPK);

		if (op.isPresent()) {

			Document doc = op.get();

			return doc;
		} else {
			return null;
		}
	}

	public void saveDocument(Document document) {

		if (null != document) {
			documentRepository.save(document);

		}

	}

}