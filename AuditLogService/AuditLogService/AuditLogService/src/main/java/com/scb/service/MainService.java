package com.scb.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.scb.model.LogData;

@Service
public interface MainService {
	
	boolean saveLog(LogData logdata);

	

	LogData getLogById(long transactionId);

	List<LogData> getLogByType(String transactionType);
	
	List<LogData> getLogByDate(String timestamp);
	
	List<LogData> getLogByStatus(String status);
	
	List<LogData> getLogByLogType(String logType);
}
