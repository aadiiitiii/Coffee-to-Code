package com.loginportal.deregister.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.loginportal.deregister.model.DeRegister;



@Repository("deRegisterRepository")
public interface DeRegisterRepository extends JpaRepository<DeRegister,Long>{
	
	@Query(value="select user_id from deregister where (current_timestamp() - deregister_time) > 2592000000;")
	List<Long> findDeactivated();
	
}
