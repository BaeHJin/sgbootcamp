package com.example.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUserId(String userId);	//userId를 기반으로 db에서 조회가능
}
