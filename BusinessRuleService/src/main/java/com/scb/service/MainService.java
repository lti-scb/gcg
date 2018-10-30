package com.scb.service;

import java.util.List;

import javax.xml.xpath.XPath;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.scb.model.BussinessRules;

@Service
public interface MainService {
	boolean saveBussinessRule(BussinessRules bussinessRules);

	List<BussinessRules> getAllBussinessRules();

	BussinessRules getBussinessRule(long constraintNumber);

	List <BussinessRules> getBussinessRulesByTypeandCountryCode(long transactionType,long countryCode);
	
	List<String> XMLServiceValidation(String XPathVariable);
	
	String  GroupHeaderInterbankSettlementDateRule();
	
	String  InstructedAgentRule();
}
