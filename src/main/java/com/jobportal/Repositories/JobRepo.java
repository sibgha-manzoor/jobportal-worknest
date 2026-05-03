package com.jobportal.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jobportal.Entities.Job;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {

	Optional<Job> findByJobTitle(String jobTitle);

	List<Job> findByJobPostedBy(String jobPostedBy);

	@Query("SELECT j FROM Job j WHERE " +
			"(:keyword IS NULL OR :keyword = '' OR " +
			"LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
			"LOWER(j.jobCompany) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
			"LOWER(j.jobLoc) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
			"LOWER(j.jobType) LIKE LOWER(CONCAT('%', :keyword, '%')))")
	List<Job> searchJobs(@Param("keyword") String keyword);
}