package org.glsid.dao;

import org.glsid.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	@Query("select c from Contact c where c.firstName like :x or c.lastName like :x")
	public Page<Contact> getNames(@Param("x")String name, Pageable pageable);
}
