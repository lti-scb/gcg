
package com.scb.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class XPathConverstion {

	public List<String> XPathConversion(String xPathVariabeData) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		List<String> names = null;
		Document doc = null;
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.parse("employees.xml");
			XPathFactory xpathFactory = XPathFactory.newInstance();

			XPath xpath = xpathFactory.newXPath();

		/*	String name = getSingleRecord(doc, xpath,xPathVariabeData);*/
		//	log.info("Xpath Conversion " + name);
			names = fetchMultipleRecord(doc, xpath,xPathVariabeData);

			for (String test : names) {
				log.info(test + "\n");
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return names;
	}

	private static List<String> fetchMultipleRecord(Document doc, XPath xpath,String XPathVariableData) {
		List<String> list = new ArrayList<>();
		try {
			XPathExpression expr = xpath.compile(XPathVariableData);
			NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); i++)
				list.add(nodes.item(i).getNodeValue());
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return list;
	}

/*	private static String getSingleRecord(Document doc, XPath xpath,String XPathVariableData) {
		String name = null;
		try {

			XPathExpression expr = xpath.compile(XPathVariableData);

			log.info("Xpath Exp Single Row" + expr);

			name = (String) expr.evaluate(doc, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return name;
	}
*/	/*private static String  GroupStatusPendingRule(Document doc, XPath xpath) {
		String GrpSts = null;
		String TxSts = null;
		XPathExpression expr1;
		try {
			expr1 = xpath.compile("/Document/FIToFIPmtStsRpt/OrgnlGrpInfAndSts/GrpSts");
			XPathExpression expr2 = xpath.compile("/Document/FIToFIPmtStsRpt/TxInfAndSts/TxSts");
			GrpSts = (String) expr1.evaluate(doc, XPathConstants.STRING);
			TxSts= (String) expr2.evaluate(doc, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(GrpSts!=null && (GrpSts.equals("PDNG") && TxSts.equals("RJCT"))) {
			return "Error Description: If group status is \"PDNG\" then TXN Status should not be \"RJCT\"";
		}
		return "Success";
	}
	private static String  TransactionStatus(Document doc, XPath xpath) {
		String TxSts = null;
		XPathExpression expr1;
		try {
			expr1 = xpath.compile("/Document/FIToFIPmtStsRpt/TxInfAndSts/TxSts");
			TxSts= (String) expr1.evaluate(doc, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(TxSts!=null && (TxSts.equals("ACSP") || TxSts.equals("ACTC") || TxSts.equals("RJCT"))) {
			return "Success";
		}
		return "Error Description: Transaction Status is Mandatory and should have following values:";
	}
	
	private static String  GroupHeaderInterbankSettlementDateRule(Document doc, XPath xpath) {
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
	private static String  InstructedAgentRule(Document doc, XPath xpath) {
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
	}*/
}
	

