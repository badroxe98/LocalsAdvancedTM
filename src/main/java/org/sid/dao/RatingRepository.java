package org.sid.dao;
import java.util.List;

import org.sid.entities.Rating;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface  RatingRepository extends JpaRepository<Rating, Long>
{
	@Query(value="select * from rating where traing like :x and user like :y",nativeQuery = true)
	public Rating findRatingByUserAndTraining(@Param("x") long id_training,@Param("y") long id_user);





}
