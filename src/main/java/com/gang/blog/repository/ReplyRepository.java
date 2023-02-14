package com.gang.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gang.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
