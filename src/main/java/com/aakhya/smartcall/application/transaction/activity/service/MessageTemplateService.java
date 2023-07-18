package com.aakhya.smartcall.application.transaction.activity.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.EntityNameType;
import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.admin.service.SequenceService;
import com.aakhya.smartcall.application.security.entity.User;
import com.aakhya.smartcall.application.transaction.activity.entity.MessageTemplate;
import com.aakhya.smartcall.application.transaction.activity.repository.MessageTemplateRepository;

@Service
public class MessageTemplateService {

	@Autowired
	private MessageTemplateRepository messageTemplateRepository;
	@Autowired
	private SequenceService sequenceService;
	
	public List<MessageTemplate> findMessageTemplates(){
		return messageTemplateRepository.findMessageTemplates();
	}
	
	public List<MessageTemplate> findApprovedMessageTemplates(String messageType){
		return messageTemplateRepository.findApprovedMessageTemplates(messageType);
	}
	
	public void saveMessageTemplare(MessageTemplate messageTemplate,User createdUser) {
		if(null != messageTemplate) {
			if(null == messageTemplate.getTemplateId() || messageTemplate.getTemplateId().equals(0L)) {
				Long templteId = sequenceService.getNewSequence(EntityNameType.MESSAGE_TEMPLATE, 1L);
				messageTemplate.setTemplateId(templteId);
				messageTemplate.setCompanyId(1L);
				messageTemplate.setCreatedBy(createdUser.getUserId());
				messageTemplate.setCreatedDateTime(new Date());
				messageTemplate.setValidFrom(new Date());
				try {
					Date eot = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2200");
					messageTemplate.setRemoveDateTime(eot);
					messageTemplate.setValdTo(eot);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				messageTemplate.setStatus(RecordStatusType.ACTIVE.getValue());
			}
			messageTemplateRepository.save(messageTemplate);
		}
	}
}
