package com.scb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scb.model.PersistanceData;

@Service
public interface MainService {
	
	boolean saveTrancation(PersistanceData persistdata);

	List<PersistanceData> getAllTransations();

	PersistanceData getTransactionById(long transactionId);

	List<PersistanceData> getTransactionByType(String transactionType);

}
