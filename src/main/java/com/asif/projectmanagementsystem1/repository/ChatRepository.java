package com.asif.projectmanagementsystem1.repository;

import com.asif.projectmanagementsystem1.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {
}
