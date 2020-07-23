package com.quest.vms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.vms.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

	public User findById(long id);
}
