package com.scb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.scb.model.PersistanceData;
import com.scb.service.MainService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class PersistanceDataController {
	@Autowired
	private MainService mainservice;

	@PostMapping("/AddPersistanceData")
	public ResponseEntity<Void> saveTransaction(@RequestBody PersistanceData persistancedata,
			UriComponentsBuilder builder) {
		System.out.println("done");
		boolean flag = mainservice.saveTrancation(persistancedata);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/ProcessFlowSequence/{id}").buildAndExpand(persistancedata).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping("/getAllTransaction")
	public ResponseEntity<List<PersistanceData>> getAllTransaction() {
		log.info(" Get All Transaction received: ");
		List<PersistanceData> list = mainservice.getAllTransations();
		log.info("Transaction Recieved " + list);
		return new ResponseEntity<List<PersistanceData>>(list, HttpStatus.OK);
	}

	@GetMapping("/getTransactionById/{transactionId}")
	public ResponseEntity<PersistanceData> getTransactionById(@PathVariable("transactionId") long transactionId) {
		log.info(" Get Transaction By ID received: " + transactionId);
		PersistanceData transactionById = mainservice.getTransactionById(transactionId);
		log.info("Transaction Recieved With Id" + transactionId + " received: " + transactionById);
		return new ResponseEntity<PersistanceData>(transactionById,HttpStatus.OK);
	}

	@GetMapping("/getTransactionByType/{transactionType}")
	public ResponseEntity<List<PersistanceData>> getProcessFlowSequenceById(
			@PathVariable("transactionType") String transactionType) {
		log.info(" Get Transaction By ID received: " + transactionType);
		List<PersistanceData> transactionByType = mainservice.getTransactionByType(transactionType);
		log.info("Transaction Recieved With Type" + transactionType + " received: " + transactionByType);
		return new ResponseEntity<List<PersistanceData>>(transactionByType, HttpStatus.OK);
	}

}
