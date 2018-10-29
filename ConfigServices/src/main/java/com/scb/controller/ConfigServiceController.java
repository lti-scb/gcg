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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.scb.model.ProcessFlow;
import com.scb.model.ProcessFlowSequence;
import com.scb.model.ServiceDetail;
import com.scb.service.MainService;
import com.scb.serviceImpl.MainServiceImpl;

import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
public class ConfigServiceController {
	@Autowired
	private MainService mainservice;


	@PostMapping("/AddProcessFlowSequence")
	public ResponseEntity<Void> addProcessFlowSequence(@RequestBody ProcessFlowSequence processflowsequence, UriComponentsBuilder builder) {
				System.out.println("done");
                boolean flag = mainservice.addProcessFlowSequence(processflowsequence);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/ProcessFlowSequence/{id}").buildAndExpand(processflowsequence).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PostMapping("/AddServiceDetail")
	public ResponseEntity<Void> addServiceDetail(@RequestBody ServiceDetail servicedetail, UriComponentsBuilder builder) {
                boolean flag = mainservice.addServiceDetail(servicedetail);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/ServiceDetail/{id}").buildAndExpand(servicedetail).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PostMapping("/AddProcessFlow")
	public ResponseEntity<Void> addProcessFlow(@RequestBody ProcessFlow processflow, UriComponentsBuilder builder) {
                boolean flag = mainservice.addProcessFlow(processflow);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/ProcessFlow/{id}").buildAndExpand(processflow).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/ProcessFlowSequences")
	public ResponseEntity<List<ProcessFlowSequence>> getAllProcessFlowSequence() {
		log.info(" Get All Process Flow Sequence received: ");
		List<ProcessFlowSequence> list = mainservice.getAllProcessFlowSequence();
		log.info("Process Flow Sequence received: " + list);
		return new ResponseEntity<List<ProcessFlowSequence>>(list, HttpStatus.OK);
	}

	
	
	@GetMapping("/ProcessFlows")
	public ResponseEntity<List<ProcessFlow>> getAllProcessFlow() {
		log.info(" Get All Process Flow received: ");
		List<ProcessFlow> list = mainservice.getAllProcessFlow();
		log.info("Process Flow received: " + list);
		return new ResponseEntity<List<ProcessFlow>>(list, HttpStatus.OK);
	}

	
	@GetMapping("/ServiceDetails")
	public ResponseEntity<List<ServiceDetail>> getAllServiceDetail() {
		log.info(" Get All ServiceDetail received: ");
		List<ServiceDetail> list = mainservice.getAllServiceDetail();
		log.info("ServiceDetail received: " + list);
		return new ResponseEntity<List<ServiceDetail>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/ProcessFlowSequence/{processId}")
	public ResponseEntity<List<ProcessFlowSequence>> getProcessFlowSequenceById(@PathVariable("processId") long processId) {
		log.info(" Get Process Flow Sequence received: "+processId);
		List<ProcessFlowSequence> processflowsequence = mainservice.getProcessFlowSequenceById(processId);
		log.info("Process Flow Sequence "+processId+" received: " + processflowsequence);
		return new ResponseEntity<List<ProcessFlowSequence>>(processflowsequence, HttpStatus.OK);
	}

	@GetMapping("/ProcessFlow/{processname}")
	public ResponseEntity<ProcessFlow> getProcessFlowByName(@PathVariable("processname") String processname) {
		log.info(" Get Process Flow received: "+processname);
		ProcessFlow processflow = mainservice.getProcessFlowByName(processname);
		log.info("Process Flow "+processname+" received: " + processflow);
		return new ResponseEntity<ProcessFlow>(processflow, HttpStatus.OK);
	}
	
	@GetMapping("/ServiceDetail/{servicename}")
	public ResponseEntity<ServiceDetail> getServiceDetailByName(@PathVariable("servicename") String servicename) {
		log.info(" Get Service Detail received: "+servicename);
		ServiceDetail servicedetail = mainservice.getServiceDetailByName(servicename);
		log.info("Service Detail "+servicename+" received: " + servicedetail);
		return new ResponseEntity<ServiceDetail>(servicedetail, HttpStatus.OK);
	}
	
	
	
}
