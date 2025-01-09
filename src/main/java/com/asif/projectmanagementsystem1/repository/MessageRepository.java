package com.asif.projectmanagementsystem1.repository;

import com.asif.projectmanagementsystem1.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findByChatIdOrderByCreatedAtAsc(Long chatId);
}
