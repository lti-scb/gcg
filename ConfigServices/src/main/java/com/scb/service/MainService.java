package com.scb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scb.model.ProcessFlow;
import com.scb.model.ProcessFlowSequence;
import com.scb.model.ServiceDetail;
@Service
public interface MainService {
/*	List<ProcessFlowSequence> getAllProcessFlowSequenceService();

	ProcessFlowSequence getProcessFlowSequenceById(long processId);
*/
	boolean addProcessFlowSequence(ProcessFlowSequence processflowsequence);
	boolean addProcessFlow(ProcessFlow processflow);
	boolean addServiceDetail(ServiceDetail servicedetail);
	
	List<ProcessFlowSequence> getAllProcessFlowSequence();
	List<ProcessFlow> getAllProcessFlow();
	List<ServiceDetail> getAllServiceDetail();
	
	
	List<ProcessFlowSequence> getProcessFlowSequenceById(long processId);
	
	ProcessFlow getProcessFlowByName(String processName);
	ServiceDetail getServiceDetailByName(String serviceName);
	
	
}
