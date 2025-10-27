package com.nareshIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nareshIt.entity.BooksEntity;

@Repository
public interface BookRepository extends JpaRepository<BooksEntity, Long>{

}
