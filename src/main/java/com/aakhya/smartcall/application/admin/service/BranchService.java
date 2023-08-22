package com.aakhya.smartcall.application.admin.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.admin.repository.BranchRepository;
import com.aakhya.smartcall.application.admin.service.utils.Distance;
import com.aakhya.smartcall.application.admin.service.utils.Element;
import com.aakhya.smartcall.application.admin.service.utils.Root;
import com.aakhya.smartcall.application.admin.service.utils.Row;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;
import com.google.gson.Gson;

@Service
public class BranchService {

	private static final String apiKey = "AIzaSyDsqxiDX4Pqfn7NUYzKFS2Nn2H4W5ywtaQ";
	@Autowired
	private BranchRepository branchRepository;

	public List<Branch> findAllBranches(String branchNameFilter, String branchCode) {
		List<Branch> branches = new ArrayList<Branch>();
		if (null != branchNameFilter && !branchNameFilter.isEmpty() && null != branchCode && !branchCode.isEmpty()) {
			branches = branchRepository.findByNameAndCode(branchNameFilter, branchCode);
		} else if (null != branchNameFilter && !branchNameFilter.isEmpty()
				&& (null == branchCode || branchCode.isEmpty())) {
			branches = branchRepository.search(branchNameFilter);
		} else if (null != branchCode && !branchCode.isEmpty()
				&& (null == branchNameFilter || branchNameFilter.isEmpty())) {
			branches = branchRepository.findByCode(branchCode);
		} else {
			branches = branchRepository.findAll();
		}
		return branches;
	}

	public List<Branch> findBranchesByHirearchy(String branchNameFilter) {
		List<Branch> hirearchicialBranches = new ArrayList<Branch>();
		if (null == branchNameFilter || branchNameFilter.isEmpty()) {
			hirearchicialBranches = branchRepository.findTopLevelBranches();
			if (null != hirearchicialBranches && !hirearchicialBranches.isEmpty()) {
				for (Branch parentBranch : hirearchicialBranches) {
					parentBranch.setChildBranches(getChildBranches(parentBranch));
				}
			}
		} else {
			hirearchicialBranches = branchRepository.findTopLevelBranches(branchNameFilter);
			if (null == hirearchicialBranches || hirearchicialBranches.isEmpty()) {
				for (Branch parentBranch : hirearchicialBranches) {
					parentBranch.setChildBranches(getChildBranches(parentBranch, branchNameFilter));
				}
			}
		}
		return hirearchicialBranches;
	}

	public List<Branch> getChildBranches(Branch parentBranch) {
		List<Branch> childBranches = branchRepository.findBranchesByHirearchy(parentBranch.getBranchCode());
		if (null != childBranches && !childBranches.isEmpty()) {
			for (Branch childBranch : childBranches) {
				childBranch.setChildBranches(getChildBranches(childBranch));
			}
		}
		return childBranches;
	}

	private List<Branch> getChildBranches(Branch parentBranch, String branchNameFilter) {
		List<Branch> childBranches = branchRepository.findBranchesByHirearchy(parentBranch.getBranchCode(),
				branchNameFilter);
		if (null != childBranches && !childBranches.isEmpty()) {
			for (Branch childBranch : childBranches) {
				childBranch.setChildBranches(getChildBranches(childBranch, branchNameFilter));
			}
		}
		return childBranches;
	}

	public void saveBranch(Branch branch) {
		if (null != branch && null != branch.getBranchCode() && !branch.getBranchCode().isEmpty()) {
			branch.setCompanyId(1L);
			branch.setStatus(RecordStatusType.ACTIVE.getValue());
			System.out.println("Branch Lattitude is :: " + branch.getGenericDecimal1());
			System.out.println("Branch Longitude is :: " + branch.getGenericDecimal2());
			branchRepository.save(branch);
		}
	}

	public void deleteBranch(Branch branch) {
		if (null != branch && null != branch.getBranchCode() && !branch.getBranchCode().isEmpty()) {
			branch.setStatus(RecordStatusType.DELETED.getValue());
			branchRepository.save(branch);
		}
	}

	public void deleteBranches(Set<Branch> branches) {
		if (null != branches && !branches.isEmpty()) {
			for (Branch branch : branches) {
				branch.setStatus(RecordStatusType.DELETED.getValue());
				branchRepository.save(branch);
			}
		}
	}

	public List<Branch> findClusters() {
		List<Branch> clusters = new ArrayList<Branch>();
		clusters.addAll(branchRepository.findBranchesByType(6L));
		clusters.addAll(branchRepository.findBranchesByType(7L));
		return clusters;
	}

	public void getDistance(Double originLat, Double originLon, Double destLat,Double destLon) {
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+originLat+"%2C"+originLon+"&destinations="+destLat+"%2C"+destLon+"&key="+apiKey;
		Gson gson = new Gson();
		try {
			URI u = new URI(url);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Root> response = restTemplate.exchange(u, HttpMethod.GET, null, Root.class);
			Root root = response.getBody();
			if (null != root.getRows() && !root.getRows().isEmpty()) {
				for (Row row : root.getRows()) {
					if (null != row.getElements() && !row.getElements().isEmpty()) {
						for (Element element : row.getElements()) {
							Distance distance = element.getDistance();
							if (null != distance) {
								System.out.println("Distance :: " + distance.text);
								System.out.println("Distance :: " + distance.value);
							}else
								System.out.println(gson.toJson(element));
						}
					}
				}
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		String root = restTemplate.getForObject(url, String.class);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.TEXT_PLAIN);
//		headers.add("", "");

//		HttpEntity<?> entity = new HttpEntity<>(headers);
//		Root root = restTemplate.getForObject(url, Root.class);
//		ResponseEntity<Root> response = restTemplate.getForEntity(url, Root.class);
//		Root root = response.getBody();
//		

	}

	public void getDistanceNew(Double lat, Double lon, TransactionDataSet dataset) {
		try {
			StringBuffer urlString = new StringBuffer();
			// urlString.append("http://103.16.101.52:8080/sendsms/bulksms?username=kap2-kapuser&password=trans321&type=0&dlr=1");
			urlString.append(
					"https://maps.googleapis.com/maps/api/distancematrix/json?origins=40.6655101,-73.89188969999998&destinations=40.659569,-73.933783|40.729029,-73.851524|40.6860072,-73.6334271|40.598566,-73.7527626&key=AIzaSyDsqxiDX4Pqfn7NUYzKFS2Nn2H4W5ywtaQ");

//			StringBuffer message = new StringBuffer("Dear user your verification code is " + otp + " CSPL");
//			if (null != message) {
//				urlString.append(URLEncoder.encode(message.toString(), "UTF-8"));
//				System.out.println(urlString.toString());
			URL url = new URL(urlString.toString());
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setDoOutput(true);
			http.setRequestMethod("GET");
			http.disconnect();
			BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
			Stream<String> lines = in.lines();
			for (int i = 0; i < lines.count(); i++) {
				System.out.println(in.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
