package com.scb.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.scb.model.compositekey.ProcessFlowSequenceCompositeKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @Builder @Entity @Table(name="processflowsequence") @NoArgsConstructor @AllArgsConstructor @ToString @XmlRootElement
public class ProcessFlowSequence implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private ProcessFlowSequenceCompositeKey processflowsequencecompositekey;
	private int status;
	private String createdOn;
	private String updatedOn;
	private long sequence;
	private String url;
}
