package com.scb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @Builder @Entity  @Table(name="processflow") @NoArgsConstructor @AllArgsConstructor @ToString @XmlRootElement
public class ProcessFlow implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private long processId;
	@Column(name="processName",unique=true,nullable=false)
	private String processName;
	private String description;
	private int status;
	private String createdOn;
	private String updatedOn;
}
