package com.loginportal.deregister.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.loginportal.deregister.model.Deactivation;


@Repository("deactivateRepository")
public interface DeactivateRepository extends JpaRepository<Deactivation,Long>{
	
	
	@Query(value="select user_id from deactivate where (to_days(current_timestamp())-to_days(deactivate_time)) > 30",nativeQuery = true)
	List<Long> findDeactivated();

}
