package org.sid.dao;

import java.util.*;

import org.sid.entities.Client;
import org.sid.entities.Formation;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;
@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
	@Query("select c from Client c where email like :x")
	public Client findByEmail(@Param("x") String email);

	@Query("select c from Client c ")
	public List<Client> findTrainers();

	@Query("select c from Client c where type like :x")
	public List<Client> findByType(@Param("x") String type);

}
