package com.scb.model;

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

@Getter @Setter @Builder @Entity @Table(name="bussinessrule") @NoArgsConstructor @AllArgsConstructor @ToString @XmlRootElement
public class BussinessRules {
@Id
private long constraintNumber;
private int transactionType;
private int countryCode;
private char applicability;
private String changeInTransformation;
private String changeInValue;
private String constraintVariable;
private String xPath;
}
