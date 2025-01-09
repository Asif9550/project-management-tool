package com.asif.projectmanagementsystem1.repository;

import com.asif.projectmanagementsystem1.model.Invitation;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation,Long> {
    Invitation findByToken(String token);
    Invitation findByEmail(String email);

}
