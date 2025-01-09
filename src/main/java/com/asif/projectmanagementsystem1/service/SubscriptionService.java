package com.asif.projectmanagementsystem1.service;

import com.asif.projectmanagementsystem1.model.PlanType;
import com.asif.projectmanagementsystem1.model.Subscription;
import com.asif.projectmanagementsystem1.model.User;

public interface SubscriptionService {
    Subscription createSubscription(User user);
    Subscription getUsersSubscription(Long userId)throws Exception;
    Subscription upgradeSubscription(Long userId,PlanType planType) throws Exception;
    boolean isValid(Subscription subscription);
}
