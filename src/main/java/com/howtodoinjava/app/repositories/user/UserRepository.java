package com.howtodoinjava.app.repositories.user;

import com.howtodoinjava.app.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.status = ?1")
	User findUserByStatus(Integer status);

	@Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
	User findUserByStatusAndName(Integer status, String name);

	@Query(value = "SELECT * FROM Users u WHERE u.status = ?1", nativeQuery = true)
	User findUserByStatusNative(Integer status);

	@Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
	User findUserByStatusAndNameNamedParams(@Param("status") Integer status, @Param("name") String name);

	@Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
	User findUserByUserStatusAndUserName(@Param("status") Integer userStatus, @Param("name") String userName);

	@Query(value = "SELECT * FROM Users u WHERE u.status = :status and u.name = :name", nativeQuery = true)
	User findUserByStatusAndNameNamedParamsNative(@Param("status") Integer status, @Param("name") String name);
}
