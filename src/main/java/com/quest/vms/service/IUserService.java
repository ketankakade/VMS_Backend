package com.quest.vms.service;

import com.quest.vms.customexception.InternalServerError;

import com.quest.vms.customexception.RecordNotFoundException;
import com.quest.vms.dto.UserDto;

public interface IUserService {

	public UserDto create(UserDto user)throws InternalServerError;
	public UserDto getUserById(int id) throws RecordNotFoundException;
	public UserDto update(int id, UserDto user) throws InternalServerError;
	public void delete(int id) throws RecordNotFoundException;

}
