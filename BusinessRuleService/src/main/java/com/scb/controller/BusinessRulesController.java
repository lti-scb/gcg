package com.scb.controller;

import java.util.Iterator;
import java.util.List;

import javax.xml.xpath.XPath;

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
import org.w3c.dom.Document;

import com.scb.model.BussinessRules;
import com.scb.service.MainService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class BusinessRulesController {
	@Autowired
	private MainService mainservice;

	@PostMapping("/AddBussinessRules")
	public ResponseEntity<Void> saveBussinessRule(@RequestBody BussinessRules bussinessrules,
			UriComponentsBuilder builder) {
		boolean flag = mainservice.saveBussinessRule(bussinessrules);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/getBussinessRules/{transactionId}").buildAndExpand(bussinessrules).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping("/getAllBussinessRules")
	public ResponseEntity<List<BussinessRules>> AllBussinessRules() {
		log.info(" Get All Transaction received: ");
		List<BussinessRules> list = mainservice.getAllBussinessRules();
		log.info("Transaction Recieved " + list);
		return new ResponseEntity<List<BussinessRules>>(list, HttpStatus.OK);
	}

	@GetMapping("/getBussinessRules/{constraintNumber}")
	public ResponseEntity<BussinessRules> getBussinessRuleById(
			@PathVariable("constraintNumber") long constraintNumber) {
		log.info(" Get Transaction By ID received: " + constraintNumber);
		BussinessRules transactionById = mainservice.getBussinessRule(constraintNumber);
		log.info("Transaction Recieved With Id" + constraintNumber + " received: " + transactionById);
		return new ResponseEntity<BussinessRules>(transactionById, HttpStatus.OK);
	}

	@GetMapping("/XMLValidate")
	public ResponseEntity<Void> XMLValidation() {
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("/BusinessRuleValidate")
	public ResponseEntity<Void> BussinessRuleValidation(long transactionType,long countryCode) {
		List<BussinessRules> list=mainservice.getBussinessRulesByTypeandCountryCode(transactionType, countryCode);
		Iterator<BussinessRules> idr=list.iterator();
		BussinessRules bussinessRuleVar;
		while(idr.hasNext()) {
			bussinessRuleVar=(BussinessRules)idr.next();
			List<String>data=mainservice.XMLServiceValidation(bussinessRuleVar.getXPath());
			
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	@GetMapping("/GroupHeaderInterbankSettlementDateRule")
	public String GroupHeaderInterbankSettlementDateRule() {
		String s1=mainservice.GroupHeaderInterbankSettlementDateRule();
		return s1;
	}
	@GetMapping("/InstructedAgentRule")
	public String InstructedAgentRule() {
		String s1=mainservice.InstructedAgentRule();
		return s1;
	}

}
