package com.scb.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scb.model.ProcessFlow;
import com.scb.model.ProcessFlowSequence;
import com.scb.model.ServiceDetail;
import com.scb.repository.ProcessFlowRepository;
import com.scb.repository.ProcessFlowSequenceRepository;
import com.scb.repository.ServiceDetailRepository;
import com.scb.service.MainService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MainServiceImpl implements MainService{
	@Autowired
	private ProcessFlowSequenceRepository processflowsequencerepository;
	@Autowired
	private ProcessFlowRepository processflowrepository;
	@Autowired
	private ServiceDetailRepository servicedetailrepository;



	@Override
	public boolean addProcessFlowSequence(ProcessFlowSequence processflowsequence) {
		log.info("ProcessFlowSequence received: " + processflowsequence);
		ProcessFlowSequence processFlowSequence = null;
		try {
		processFlowSequence = (ProcessFlowSequence) processflowsequencerepository.findById(processflowsequence.getProcessflowsequencecompositekey()).get();
		}catch (NoSuchElementException ex) {
			log.info("Error in finding Process Flow Sequence : " + ex.getMessage());
			//ex.printStackTrace();
		}
		if (processFlowSequence != null) {
			return false;
		} else {
			log.info("ProcessFlow being saved in db");
			processflowsequencerepository.save(processflowsequence);
			log.info("ProcessFlow saved in db");
			return true;
		}	}

	@Override
	public boolean addProcessFlow(ProcessFlow processflow) {
		log.info("ProcessFlow received: " + processflow);
		ProcessFlow processFlow = null;
		try {
			 processFlow = (ProcessFlow) processflowrepository.findById(processflow.getProcessId()).get();
		}catch (NoSuchElementException ex) {
			log.info("Error in finding Process Flow : " + ex.getMessage());
			//ex.printStackTrace();
		}
		
		
		if (processFlow != null) {
			return false;
		} else {
			log.info("ProcessFlow being saved in db");
			processflowrepository.save(processflow);
			log.info("ProcessFlow saved in db!");
			return true;
		}
	}

	@Override
	public boolean addServiceDetail(ServiceDetail servicedetail) {
	log.info("ServiceDetail received: " + servicedetail);
		
		ServiceDetail serviceDetail = null;
		try {
			serviceDetail = (ServiceDetail) servicedetailrepository.findById(servicedetail.getServiceId()).get();
		} catch (NoSuchElementException ex) {
			log.info("Error in finding service detail : " + ex.getMessage());
			//ex.printStackTrace();
		}
		
		log.info("ServiceDetail from db: " + serviceDetail);
		
		if (serviceDetail != null ) {
			return false;
		} else {
			log.info("ServiceDetail being saved in db");
			servicedetailrepository.save(servicedetail);
			log.info("ServiceDetail saved in db!");
			return true;
		}

	}

	@Override
	public List<ProcessFlowSequence> getAllProcessFlowSequence() {
		List<ProcessFlowSequence> list = new ArrayList<>();
		processflowsequencerepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public List<ProcessFlow> getAllProcessFlow() {
		List<ProcessFlow> list = new ArrayList<>();
		processflowrepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public List<ServiceDetail> getAllServiceDetail() {
		List<ServiceDetail> list = new ArrayList<>();
		servicedetailrepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public List<ProcessFlowSequence> getProcessFlowSequenceById(long processId) {
		List<ProcessFlowSequence> obj = processflowsequencerepository.getProcessFlowSequenceById(processId);
		return obj;

	}

	@Override
	public ProcessFlow getProcessFlowByName(String processName) {
		ProcessFlow obj = processflowrepository.findProcessFlowByName(processName).get(0);
		return obj;
	}

	@Override
	public ServiceDetail getServiceDetailByName(String serviceName) {
		ServiceDetail obj = servicedetailrepository.findServiceDetailByName(serviceName).get(0);
		return obj;
	}
	

}
