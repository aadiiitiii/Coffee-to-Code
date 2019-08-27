package com.loginportal.deregister.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loginportal.deregister.model.Reviews;
import java.util.List;

@Repository("reviewRepository")
public interface ReviewRepository extends JpaRepository<Reviews,Long> {
		@Modifying
		@Query(value = "update Reviews set firstName=:msg where uid = :id")
	    public void changeName(@Param("id") Long id ,@Param("msg") String msg);
		
		public List<Reviews> findByUid(Long uid);
		@Modifying
		@Query(value="DELETE FROM Reviews r where r.uid = :uid")
		public void deleteByUid(@Param("uid") Long uid);
		
}
