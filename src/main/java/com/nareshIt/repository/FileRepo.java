package com.nareshIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nareshIt.entity.FilesEntity;

@Repository
public interface FileRepo extends JpaRepository<FilesEntity, Long> {
	
}