package com.aakhya.smartcall.application.transaction.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aakhya.smartcall.application.transaction.data.entity.DetailViewObject;
import com.aakhya.smartcall.application.transaction.data.entity.DpdQueue;
import com.aakhya.smartcall.application.transaction.data.entity.DpdQueueList;
import com.aakhya.smartcall.application.transaction.data.service.TransactionDataSetService;

@RestController
@RequestMapping("/transactionDataSet")
public class TransactionDataSetController {

	@Autowired
	private TransactionDataSetService transactionDataSetService;

	@RequestMapping(path = "/getDpdQueues", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DpdQueue> findDpdQueues(@RequestParam("userId") String userId,
			@RequestParam("branchCode") String branchCode) {
		return transactionDataSetService.findDpdQueus(userId, branchCode);
	}

	@RequestMapping(path = "/getDpdQueueList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DpdQueueList> getDpdQueueList(@RequestParam("userId") String userId,
			@RequestParam("branchCode") String branchCode, @RequestParam("queue") Long queue) {
		return transactionDataSetService.getDpdQueueList(userId, branchCode, queue);
	}

	@RequestMapping(path = "/getDetailView", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DetailViewObject> getDetailView(@RequestParam("dataSetId") Long dataSetId, Long queue) {
		return transactionDataSetService.getDetailView(dataSetId, queue);
	}
	
	@RequestMapping(path = "/getVisitLists", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DpdQueueList> getVisitList(@RequestParam("userId") String userId,
			@RequestParam("branchCode") String branchCode) {
		return transactionDataSetService.getVisitList(userId, branchCode);
	}
	
	@RequestMapping(path = "/getCallLists", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DpdQueueList> getCallList(@RequestParam("userId") String userId,
			@RequestParam("branchCode") String branchCode) {
		return transactionDataSetService.getCallList(userId, branchCode);
	}
	
	@RequestMapping(path = "/updateLocation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateLocation(@RequestParam("dataSetId") Long dataSetId,
			@RequestParam("lat") String lat,
			@RequestParam("lon")String lon,
			@RequestParam("dist")String dist) {
		return transactionDataSetService.updateLocation(dataSetId, lat, lon, dist);
	}
	
	@RequestMapping(path = "/getNearByCustomers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DpdQueueList> getNearByCustomersList(@RequestParam("userId")String userId, 
			@RequestParam("lat") String lat,@RequestParam("lon") String lon){
		return transactionDataSetService.getNearByCustomersList(userId, lat, lon);
	}
}
