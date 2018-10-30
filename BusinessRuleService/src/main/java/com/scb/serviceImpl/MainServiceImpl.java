package com.scb.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.scb.model.BussinessRules;
import com.scb.repository.BussinessRulesRepo;
import com.scb.service.MainService;
import com.scb.util.XPathConverstion;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MainServiceImpl implements MainService {
	@Autowired
	private BussinessRulesRepo bussinessrulesrepo;

	@Override
	public boolean saveBussinessRule(BussinessRules bussinessRules) {
		log.info("Bussiness Rule received: " + bussinessRules.getConstraintNumber());
		BussinessRulesRepo persistDataVar = null;
		try {
			persistDataVar = (BussinessRulesRepo) bussinessrulesrepo.findById(bussinessRules.getConstraintNumber())
					.get();
		} catch (NoSuchElementException ex) {
			log.info("Error in finding rule" + ex.getMessage());
		}
		if (persistDataVar != null) {
			return false;
		} else {
			log.info("Rule details being saved in db");
			bussinessrulesrepo.save(bussinessRules);
			log.info("Rule details saved in db");
			return true;
		}
	}

	@Override
	public List<BussinessRules> getAllBussinessRules() {
		List<BussinessRules> list = new ArrayList<>();
		bussinessrulesrepo.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public BussinessRules getBussinessRule(long constraintNumber) {
		BussinessRules obj = bussinessrulesrepo.findById(constraintNumber).get();
		return obj;
	}

	public List<String> XMLServiceValidation(String XPath) {
		XPathConverstion xpathconvert = new XPathConverstion();
		List<String>rules=xpathconvert.XPathConversion(XPath);
		return rules;
	}

	@Override
	public List<BussinessRules> getBussinessRulesByTypeandCountryCode(long transactionType, long countryCode) {
		List<BussinessRules>list=bussinessrulesrepo.getBussinessRulesByTypeandCountryCode(transactionType, countryCode);
		return list;
	}
	@Override
	public String  GroupHeaderInterbankSettlementDateRule() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		List<String> names = null;
		Document doc = null;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				doc = builder.parse("employees.xml");
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			XPathFactory xpathFactory = XPathFactory.newInstance();

			XPath xpath = xpathFactory.newXPath();
		String InterbankSettlementDate = null;
		String CreditInterbankSettlementDate = null;
		XPathExpression expr1;
		XPathExpression expr2;
		try {
			expr1 = xpath.compile("/Document/FIToFICstmrCdtTrf/GrpHdr/IntrBkSttlmDt");
			expr2 = xpath.compile("/Document/FIToFICstmrCdtTrf/CdtTrfTxInf/IntrBkSttlmDt");
			InterbankSettlementDate = (String) expr1.evaluate(doc, XPathConstants.STRING);
			CreditInterbankSettlementDate= (String) expr2.evaluate(doc, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(InterbankSettlementDate!=null && CreditInterbankSettlementDate==null){
			return "Success";
		}
		return "Error Description:If GroupHeader/InterbankSettlementDate is present, then CreditTransferTransactionInformation/\r\n" + 
				"InterbankSettlementDate is not allowed.";
	}
	@Override
	public String  InstructedAgentRule() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		List<String> names = null;
		Document doc = null;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				doc = builder.parse("InstructedAgentRule.xml");
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			XPathFactory xpathFactory = XPathFactory.newInstance();

			XPath xpath = xpathFactory.newXPath();
		String InstructedAgent = null;
		String CreditInstructedAgent = null;
		XPathExpression expr1;
		XPathExpression expr2;
		try {
			expr1 = xpath.compile("/Document/FIToFICstmrCdtTrf/GrpHdr/InstdAgt");
			expr2 = xpath.compile("/Document/FIToFICstmrCdtTrf/CdtTrfTxInf/InstdAgt");
			InstructedAgent = (String) expr1.evaluate(doc, XPathConstants.STRING);
			CreditInstructedAgent= (String) expr2.evaluate(doc, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(InstructedAgent!=null && CreditInstructedAgent==null){
			return "Success";
		}
		return "If GroupHeader/InstructedAgent is present, then CreditTransferTransactionInformation/InstructedAgent\r\n" + 
				"is not allowed.";
	}
	

}
