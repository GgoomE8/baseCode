package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Id> {
    Optional<Member> findById(Long id);
    Optional<Member> findByUsername(String username);
}
