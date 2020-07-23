package com.quest.vms.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.vms.customexception.InternalServerError;
import com.quest.vms.customexception.RecordNotFoundException;
import com.quest.vms.dao.IUserDao;
import com.quest.vms.dto.ContactPersonDto;
import com.quest.vms.dto.DeviceDto;
import com.quest.vms.dto.TimeSlotDto;
import com.quest.vms.dto.UserDto;
import com.quest.vms.entity.ContactPerson;
import com.quest.vms.entity.Device;
import com.quest.vms.entity.TimeSlot;
import com.quest.vms.entity.User;

@Service
public class UserService implements IUserService {

	private static final Logger logger = LogManager.getLogger(UserService.class);
	
	@Autowired
	IUserDao userDao;

	@Override
	public UserDto create(UserDto userDto)throws InternalServerError {


		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(userDto, User.class);
//		User user = new User();
//		user.setFirstName(userDto.getFirstName());
//		user.setLastName(userDto.getLastName());
//		user.setEmail(userDto.getEmail());
//		user.setContactNo(userDto.getContactNo());
//		user.setIdProof(userDto.getIdProof());
//		user.setVisitorType(userDto.getVisitorType());
//		user.setReasonForVisit(userDto.getReasonForVisit());
//		user.setContactPerson(userDto.getContactPerson());
//		user.setTimeSlot(userDto.getTimeSlot());
//		user.setDevice(userDto.getDevice());
		
		
		if(userDao.save(user)==null)
			throw new InternalServerError("Error WWhile saving data");
		
		return userDto;
	}

	@Override
	public UserDto getUserById(long id) throws RecordNotFoundException {
	
		User user = userDao.getUserById(id);
		if(user==null)
			throw new RecordNotFoundException("Record not found with given ID");
		
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
//		UserDto userDto = new UserDto();
//		userDto.setFirstName(user.getFirstName());
//		userDto.setLastName(user.getLastName());
//		userDto.setEmail(user.getEmail());
//		userDto.setContactNo(user.getContactNo());
//		userDto.setIdProof(user.getIdProof());
//		userDto.setVisitorType(user.getVisitorType());
//		userDto.setReasonForVisit(user.getReasonForVisit());
//		userDto.setContactPerson(user.getContactPerson());
//		userDto.setTimeSlot(user.getTimeSlot());
//		userDto.setDevice(user.getDevice());
		
		return userDto;
	}

	@Override
	public UserDto update(long id, UserDto userDto) throws InternalServerError {
		
		ModelMapper modelMapper = new ModelMapper();
		
		//List<ContactPerson> contactPerson=modelMapper.map(userDto.getContactPerson(), ContactPerson.class);
		
		User existUser = userDao.getUserById(id);
		
		if (existUser== null) {
			
			logger.info("Record not exist");
			
		//	User userToUpdate = new User();
//			userToUpdate = modelMapper.map(userDto, User.class);

			
//			userToUpdate.setFirstName(userDto.getFirstName());
//			userToUpdate.setLastName(userDto.getLastName());
//			userToUpdate.setEmail(userDto.getEmail());
//			userToUpdate.setContactNo(userDto.getContactNo());
//			userToUpdate.setIdProof(userDto.getIdProof());
//			userToUpdate.setVisitorType(userDto.getVisitorType());
//			userToUpdate.setReasonForVisit(userDto.getReasonForVisit());
//			userToUpdate.setContactPerson(userDto.getContactPerson());
//			userToUpdate.setTimeSlot(userDto.getTimeSlot());
//			userToUpdate.setDevice(userDto.getDevice());
			
//			if(userDao.save(userToUpdate)==null)
//				throw new InternalServerError("Error while adding record");
			
		}else {
			//existUser=modelMapper.map(userDto, User.class);
			List<ContactPerson> contactPersonList=new ArrayList<>();
			for(ContactPersonDto contactPersonDto:userDto.getContactPerson()) {
				//contactPersonList.add(modelMapper.map(contactPersonDto, ContactPerson.class));	
				ContactPerson contactPerson=new ContactPerson();
				
				contactPerson.setFirstName(contactPersonDto.getFirstName());
				contactPerson.setLastName(contactPersonDto.getLastName());
				contactPerson.setEmail(contactPersonDto.getEmail());
				contactPerson.setContactNo(contactPersonDto.getContactNo());
				
				contactPersonList.add(contactPerson);	
			}
			
			List<Device> deviceList=new ArrayList<>();
			for(DeviceDto deviceDto:userDto.getDevice()) {
				//deviceList.add(modelMapper.map(deviceDto, Device.class));	
				Device device=new Device();
				device.setDeviceType(deviceDto.getDeviceType());
				device.setDeviceMake(deviceDto.getDeviceMake());
				
				deviceList.add(device);
			}
			
			List<TimeSlot> timeSlotList=new ArrayList<>();
			for(TimeSlotDto timeSlotDto:userDto.getTimeSlot()) {
				//timeSlotList.add(modelMapper.map(timeSlotDto, TimeSlot.class));	
				TimeSlot timeSlot=new TimeSlot();
				timeSlot.setStartTime(timeSlotDto.getStartTime());
				timeSlot.setEndtime(timeSlotDto.getEndTime());
				
				timeSlotList.add(timeSlot);
			}
			
			existUser.setFirstName(userDto.getFirstName());
			existUser.setLastName(userDto.getLastName());
			existUser.setEmail(userDto.getEmail());
			existUser.setContactNo(userDto.getContactNo());
			existUser.setIdProof(userDto.getIdProof());
			existUser.setVisitorType(userDto.getVisitorType());
			existUser.setReasonForVisit(userDto.getReasonForVisit());
			existUser.setPlaceOfVisit(userDto.getPlaceOfVisit());
			existUser.setContactPerson(contactPersonList);
			existUser.setTimeSlot(timeSlotList);
			existUser.setDevice(deviceList);
			
			if(userDao.save(existUser)==null)
				throw new InternalServerError("Error while updating record");
			
		}

		return userDto;
	}

	@Override
	public void delete(long id) throws RecordNotFoundException{
		User userToBeDeleted = null;
		
			userToBeDeleted = userDao.getUserById(id);
			
			if (userToBeDeleted == null)
				throw new RecordNotFoundException("Record not found with id: " + id);
			
			userDao.delete(userToBeDeleted);	
	}
	
}
