package com.quest.vms.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.vms.customexception.InternalServerError;
import com.quest.vms.customexception.RecordNotFoundException;
import com.quest.vms.dao.IUserDao;
import com.quest.vms.dto.UserDto;
import com.quest.vms.entity.User;

@Service
public class UserService implements IUserService {

	private static final Logger logger = LogManager.getLogger(UserService.class);
	
	@Autowired
	IUserDao userDao;

	@Override
	public UserDto create(UserDto userDto)throws InternalServerError {

		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setContactNo(userDto.getContactNo());
		user.setIdProof(userDto.getIdProof());
		user.setVisitorType(userDto.getVisitorType());
		user.setReasonForVisit(userDto.getReasonForVisit());
		user.setContactPerson(userDto.getContactPerson());

		if(userDao.save(user)==null)
			throw new InternalServerError("Error WWhile saving data");
		
		return userDto;
	}

	@Override
	public UserDto getUserById(int id) throws RecordNotFoundException {
	
		User user = userDao.getUserById(id);
		if(user==null)
			throw new RecordNotFoundException("Record not found with given ID");
		
		UserDto userDto = new UserDto();
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		userDto.setContactNo(user.getContactNo());
		userDto.setIdProof(user.getIdProof());
		userDto.setVisitorType(user.getVisitorType());
		userDto.setReasonForVisit(user.getReasonForVisit());
		userDto.setContactPerson(user.getContactPerson());

		
		return userDto;
	}

	@Override
	public UserDto update(int id, UserDto userDto) throws InternalServerError {
		
		UserDto user=null;
		User existUser = userDao.getUserById(id);
		
		if (existUser == null) {
			
			User userToUpdate = new User();
			userToUpdate.setFirstName(userDto.getFirstName());
			userToUpdate.setLastName(userDto.getLastName());
			userToUpdate.setEmail(userDto.getEmail());
			userToUpdate.setContactNo(userDto.getContactNo());
			userToUpdate.setIdProof(userDto.getIdProof());
			userToUpdate.setVisitorType(userDto.getVisitorType());
			userToUpdate.setReasonForVisit(userDto.getReasonForVisit());
			userToUpdate.setContactPerson(userDto.getContactPerson());
			
			if(userDao.save(userToUpdate)==null)
				throw new InternalServerError("Error while adding record");
			
		}else {
			existUser.setFirstName(userDto.getFirstName());
			existUser.setLastName(userDto.getLastName());
			existUser.setEmail(userDto.getEmail());
			existUser.setContactNo(userDto.getContactNo());
			existUser.setIdProof(userDto.getIdProof());
			existUser.setVisitorType(userDto.getVisitorType());
			existUser.setReasonForVisit(userDto.getReasonForVisit());
			existUser.setContactPerson(userDto.getContactPerson());
			
			if(userDao.save(existUser)==null)
				throw new InternalServerError("Error while updating record");
			
		}

		return userDto;
	}

	@Override
	public void delete(int id) throws RecordNotFoundException{
		User userToBeDeleted = null;
		
			userToBeDeleted = userDao.getUserById(id);
			
			if (userToBeDeleted == null)
				throw new RecordNotFoundException("Record not found with id: " + id);
			
			userDao.delete(userToBeDeleted);	
	}
	
}
