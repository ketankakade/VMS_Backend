package com.quest.vms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_device")
@Data
public class Device {

	@Id
	private long deviceID;
	
	private String deviceType;
	
	private String deviceMake;
	
}
