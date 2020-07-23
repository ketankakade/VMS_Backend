package com.quest.vms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.vms.entity.User;
import com.quest.vms.repository.UserRepository;

@Service
public class UserDao implements IUserDao {
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public User save(User user){
		return userRepo.save(user);
	}

	@Override
	public User getUserById(long id) {
		return userRepo.findById(id);
	}

//	@Override
//	public User update(int id, User user) throws InternalServerError {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void delete(User user) {
		userRepo.delete(user);
	}	
}
