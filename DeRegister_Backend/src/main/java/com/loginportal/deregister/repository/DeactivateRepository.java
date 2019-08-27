package com.loginportal.deregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loginportal.deregister.model.Deactivation;

@Repository("deactivateRepository")
public interface DeactivateRepository extends JpaRepository<Deactivation,Long>{

}
