package com.m6.javadevjournal.springbooth2.repository;

import java.util.List;

import com.m6.javadevjournal.springbooth2.model.ModelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelTypeJpaRepository extends JpaRepository<ModelType, Long> {
	List<ModelType> findByNameIsNull();
}
