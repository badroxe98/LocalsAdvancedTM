package org.sid.dao;
import java.sql.Date;
import java.util.List;


import org.sid.entities.Local;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface  LocalRepository extends JpaRepository<Local, Long>
{
	@Query(value="select * from Local l where owner like :x",nativeQuery = true)
	public List<Local> findByUserId(@Param("x") Long id);

	@Query(value="select * from Local l where ville like :x",nativeQuery = true)
	public List<Local> findByCity(@Param("x") String ville);

	@Query(value="select email from client where id in (select user_id from formation where local like :x)", nativeQuery = true)
	public List<String> findParticipants(@Param("x") Long Id);

	@Transactional
	@Modifying
	@Query(value="update formation set local = NULL where local like :x",nativeQuery = true)
	public void  localDeleted(@Param("x") Long id);
	
	@Query(value="select * from local where disponibilite_from>=CURDATE() ", nativeQuery = true)
	public List<Local> findByTodaysDate();
	
	@Query(value="select * from local l where ( (l.disponibiliteBegin <= :a  and l.disponibiliteEnd >= :b) and (l.ville like :c) and (l.category like :d) and (l.nb_places between :e and :f) and (l.prix_par_heure between :g and :h))",nativeQuery=true)
	public List<Local> rechercherLocaux(@Param("a")Date StartDate,@Param("b") Date EndDate,@Param("c")String City,@Param("d")String TypeLocal,@Param("e")int MinNbrPlaces,@Param("f")int MaxNbrPlaces,@Param("g")int MinPrice,@Param("h")int MaxPrice);
}
