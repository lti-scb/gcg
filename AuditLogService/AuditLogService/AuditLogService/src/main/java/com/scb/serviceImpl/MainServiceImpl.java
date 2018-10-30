package com.scb.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.scb.model.*;
import com.scb.repository.LogDataRepository;
import com.scb.model.compositekey.LogDataCompositeKey;

import com.scb.repository.LogDataRepository;
import com.scb.service.MainService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2

	public class MainServiceImpl implements MainService {
	
	
	@Autowired
	private LogDataRepository logdatarepositary;
	
	
	@Override
	public boolean saveLog(LogData logdata1) {
		log.info("log data received: " + logdata1);
		LogData DataVar = null;
		try {
			DataVar = (LogData) logdatarepositary.findById(logdata1.getDataCompositeKey()).get();
		} catch (NoSuchElementException ex) {
			log.info("Error in finding log" + ex.getMessage());
		}
		if (DataVar != null) {
			return false;
		} else {
			log.info("Log deatils being saved in db");
			long count=logdatarepositary.findAll().size();
			LogDataCompositeKey pCompositeKey=logdata1.getDataCompositeKey();
			pCompositeKey.setReferenceId(count);
			logdata1.setDataCompositeKey(pCompositeKey);
			logdatarepositary.save(logdata1);
			log.info("log saved in db");
			return true;
		}
	}

	

	
	@Override
	public LogData getLogById(long transactionId) {
		LogData obj = logdatarepositary.findByTransactionId(transactionId).get();
		return obj;
	}

	@Override
	public List<LogData> getLogByType(String transactionType) {
		List<LogData> obj = logdatarepositary.findByType(transactionType);
		return obj;
	}
	
	@Override
	public List<LogData> getLogByStatus(String status) {
		List<LogData> obj = logdatarepositary.findByStatus(status);
		return obj;
	}
	
	@Override
	public List<LogData> getLogByDate(String timeStamp) {
		List<LogData> obj = logdatarepositary.findByDate(timeStamp);
		return obj;
	}
	
	@Override
	public List<LogData> getLogByLogType(String logType) {
		List<LogData> obj = logdatarepositary.findByLogType(logType);
		return obj;
	}



}
