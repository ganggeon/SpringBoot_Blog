package com.gang.blog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gang.blog.model.Board;
import com.gang.blog.model.User;


public interface BoardRepository extends JpaRepository<Board, Integer> { 

}
