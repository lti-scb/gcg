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


import com.scb.model.LogData;
import com.scb.service.MainService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2


public class AuditLogServiceController {
	@Autowired
	private MainService mainservice;

	
	
	@PostMapping("/AddLogData")
	public ResponseEntity<Void> saveLog(@RequestBody LogData logdata,
			UriComponentsBuilder builder) {
		System.out.println("done");
		boolean flag = mainservice.saveLog(logdata);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/ProcessFlowSequence/{id}").buildAndExpand(logdata).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	

	@GetMapping("/getLogById/{transactionId}")
	public ResponseEntity<LogData> getLogById(@PathVariable("transactionId") long transactionId) {
		log.info(" Get Log By ID received: " + transactionId);
		LogData transactionById = mainservice.getLogById(transactionId);
		log.info("Log Recieved With Id" + transactionId + " received: " + transactionById);
		return new ResponseEntity<LogData>(transactionById,HttpStatus.OK);
	}

	@GetMapping("/getLogByType/{transactionType}")
	public ResponseEntity<List<LogData>> getLogById(
			@PathVariable("transactionType") String transactionType) {
		log.info(" Get Log By ID received: " + transactionType);
		List<LogData> transactionByType = mainservice.getLogByType(transactionType);
		log.info("Log Recieved With Type" + transactionType + " received: " + transactionByType);
		return new ResponseEntity<List<LogData>>(transactionByType, HttpStatus.OK);
	}
	
	@GetMapping("/getLogByStatus/{status}")
	public ResponseEntity<List<LogData>> getLogByStatus(
			@PathVariable("status") String status) {
		log.info(" Get Log By status received: " + status);
		List<LogData> transactionByStatus = mainservice.getLogByStatus(status);
		log.info("Log Recieved With Status" + status + " received: " + transactionByStatus);
		return new ResponseEntity<List<LogData>>(transactionByStatus, HttpStatus.OK);
	}
	
	@GetMapping("/getLogByDate/{timeStamp}")
	public ResponseEntity<List<LogData>> getLogByDate(
			@PathVariable("timeStamp") String timeStamp) {
		log.info(" Get Log By date received: " + timeStamp);
		List<LogData> transactionByDate = mainservice.getLogByDate(timeStamp);
		log.info("Log Recieved With date" + timeStamp + " received: " + transactionByDate);
		return new ResponseEntity<List<LogData>>(transactionByDate, HttpStatus.OK);
	}

	@GetMapping("/getLogByLogtype/{logType}")
	public ResponseEntity<List<LogData>> getLogByLogType(
			@PathVariable("logType") String logType) {
		log.info(" Get Log By logType received: " + logType);
		List<LogData> transactionByLogType = mainservice.getLogByLogType(logType);
		log.info("Log Recieved With logType" + logType + " received: " + transactionByLogType);
		return new ResponseEntity<List<LogData>>(transactionByLogType, HttpStatus.OK);
	}
}
