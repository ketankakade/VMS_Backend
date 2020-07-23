package com.quest.vms.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.quest.vms.entity.ContactPerson;
import com.quest.vms.entity.Device;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)

public class UserDto {
	
	@JsonIgnore
	private long id;
	
	private String firstName;
	private String lastName;
	private String email;
	private long contactNo;
	private String idProof;
	private String placeOfVisit;
	private String reasonForVisit;
	private String visitorType;
	private List<ContactPersonDto> contactPerson;
	private List<TimeSlotDto> timeSlot;
	private List<DeviceDto> device;

}
