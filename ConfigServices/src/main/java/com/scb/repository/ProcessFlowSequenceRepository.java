package com.scb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.scb.model.ProcessFlowSequence;
import com.scb.model.ServiceDetail;
import com.scb.model.compositekey.ProcessFlowSequenceCompositeKey;
@RepositoryRestResource
public interface ProcessFlowSequenceRepository extends JpaRepository<ProcessFlowSequence, ProcessFlowSequenceCompositeKey>  {
	@Query(value="SELECT * FROM processflowsequence sd WHERE sd.processId = ?1",nativeQuery=true)
	List<ProcessFlowSequence> getProcessFlowSequenceById(long processId);
}
