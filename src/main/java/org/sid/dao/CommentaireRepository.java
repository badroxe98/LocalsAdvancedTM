package org.sid.dao;

import java.util.List;

import org.sid.entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

	@Query(value="select Distinct * from commentaire c where id_client  is not null order by date_ecriture limit :x", nativeQuery = true)
	public List<Commentaire> findRecent(@Param("x") int n);

}
