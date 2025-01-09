package com.asif.projectmanagementsystem1.repository;

import com.asif.projectmanagementsystem1.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository  extends JpaRepository<Subscription,Long> {
    Subscription findByUserId(Long userId);
}
