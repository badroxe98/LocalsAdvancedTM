package org.sid.dao;

import java.util.List;

import org.sid.entities.Admin;
import org.sid.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	@Query("select a from Admin a where email like :x")
	public Admin findByEmail(@Param("x") String email);

	@Query(value="select count(*) from client",nativeQuery = true)
	public long countUsers();

	@Query(value="select count(count_stars) from rating where count_stars like :x",nativeQuery = true)
	public double countByStarRatings(@Param("x") int countStar);

	@Query(value="select count(user) from rating",nativeQuery = true)
	public long countUsersFromRating();




}
