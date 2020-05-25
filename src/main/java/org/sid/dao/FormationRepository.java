package org.sid.dao;


import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import javax.persistence.Table;

import org.sid.entities.Client;
import org.sid.entities.Formation;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
public interface FormationRepository extends JpaRepository<Formation, Long> {

	@Query(value="select f.*,avg(r.count_stars) from formation f, rating r where traing=id group by traing",nativeQuery =true)
	public  Page<Formation> findAllAndRating(Pageable paging);

	@Query("select f from Formation f where user_id like :x")
	public List<Formation> findByUserId(@Param("x") Long id);

	@Query("select f from Formation f where Article_Cat like :x")
	public Page<Formation> findByArticleCat(@Param("x") String cat,Pageable paging);

	@Query("select count(*) from Formation where Article_Cat like :x")
	public Long countByArticleCat(@Param("x") String city);

	@Query(value="select * from Formation where local in (select id from local where ville like :x)",nativeQuery = true)
	public Page<Formation> findByArticleCity(@Param("x") String city,Pageable paging);

	@Query(value="select count(*) from Formation f where local in (select id from local where ville like :x)", nativeQuery = true)
	public Long countByArticleCity(@Param("x") String city);

	@Transactional
	@Modifying
	@Query(value="insert into Formationreservee (user_id,training_id) values (:userId,:trainingId)",nativeQuery = true)
	public void insertIntoReservation(@Param("userId") Long uId,@Param("trainingId") Long tId);

	@Query(value="select count(*) from Formationreservee f where training_id like :x",nativeQuery = true)
	public Long countByIdFormation(@Param("x") Long id);

	@Query(value="select training_id from Formationreservee f where training_id like :x and  user_id like :y",nativeQuery = true)
	public List<Long> verifyIfAlreadyExist(@Param("y") Long uId,@Param("x") Long tId);

	@Query(value="select * from Formation fo  where  id in (select training_id from formationreservee f where user_id like :x)",nativeQuery = true)
	public List<Formation> findReservedTraining(@Param("x") Long id);

	@Transactional
	@Modifying
	@Query(value="delete from formationreservee where training_id like :x and user_id like :y",nativeQuery = true)
	public void deleteMyReservation(@Param("x") Long tId,@Param("y") Long uId);

	@Query(value="select * from formation f where f.user_id in (select c.id from client c where expertise like :x ) and f.local in (select l.id from local l where ville like:y) and ( f.article_cat like :z)",nativeQuery=true)
	public Page<Formation> rechercherformation(@Param("x") String Trainer, @Param("y") String Local,@Param("z") String Category,Pageable paging);

	@Query(value="select * from formation f where f.user_id in (select c.id from client c where expertise like :x ) and f.local in (select l.id from local l where ville like:y) and ( f.article_cat like :z)",nativeQuery=true)
	public List<Formation> findNomberTrainings(@Param("x") String Trainer, @Param("y") String Local,@Param("z") String Category);


	@Query(value="select * from formation where first_day like DATE_ADD(CURDATE(), INTERVAL 1 DAY) ", nativeQuery = true)
	public List<Formation> trendyTrainings();

	@Query(value="select email from client where id in (select user_id from formationreservee where training_id like :x)", nativeQuery = true)
	public List<String> findParticipants(@Param("x") Long TrainingId);

	@Transactional
	@Modifying
	@Query(value="delete from formationreservee where training_id like :x", nativeQuery = true)
	public void deleteRequests(@Param("x") Long id);

	@Query(value="select * from formation f where(  (f.first_day <= :a and f.last_day >= :b) and (f.article_cat like :c) and (f.difficulty like :d) and ((select avg(count_stars) from rating r) >= :e) and (f.local in (select l.id from local l where ville like:f and l.category like :g )) and (f.user_id in (select c.id from client c where expertise like :h )) and (f.prix between :i and :j)  )   ",nativeQuery=true)
	public Page<Formation> rechercherformationAvancee(@Param("a")Date StartDate,@Param("b") Date EndDate,@Param("c") String Category,@Param("d") String Difficulty,@Param("e") int Rating,@Param("f")String City,@Param("g") String TypeLocal,@Param("h") String Trainer,@Param("i") int MinPrice,@Param("j")int MaxPrice,Pageable paging);

	@Query(value="select * from formation f where(  (f.first_day <= :a and f.last_day >= :b) and (f.article_cat like :c) and (f.difficulty like :d) and ((select avg(count_stars) from rating r) >= :e) and (f.local in (select l.id from local l where ville like:f and l.category like :g )) and (f.user_id in (select c.id from client c where expertise like :h )) and (f.prix between :i and :j)  )   ",nativeQuery=true)
	public List<Formation> countResultFormation (@Param("a")Date StartDate,@Param("b") Date EndDate,@Param("c") String Category,@Param("d") String Difficulty,@Param("e") int Rating,@Param("f")String City,@Param("g") String TypeLocal,@Param("h") String Trainer,@Param("i") int MinPrice,@Param("j")int MaxPrice);

	@Query(value="select * from formation where first_day>=CURDATE() ", nativeQuery = true)
	public List<Formation> findByTodaysDate();




}
