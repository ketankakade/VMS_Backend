package com.quest.vms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)

public class ContactPersonDto {
	
	//private long id;

	private String firstName;
	
	private String lastName;

	private String email;

	private long contactNo;

	private String idProof;

	private String reasonForVisit;

	private String visitorType;

}
