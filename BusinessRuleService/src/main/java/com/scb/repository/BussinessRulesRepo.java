package com.scb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.scb.model.BussinessRules;

@RepositoryRestResource
public interface BussinessRulesRepo extends JpaRepository<BussinessRules, Long> {

	@Query(value="SELECT * FROM bussinessrule sd WHERE sd.transactionType = ?1 AND sd.countryCode=?2 AND sd.applicability='Y'",nativeQuery=true)
	List<BussinessRules> getBussinessRulesByTypeandCountryCode(long transactionType,long countryCode);
}