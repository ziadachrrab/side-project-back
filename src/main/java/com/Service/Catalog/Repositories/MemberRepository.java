package com.Service.Catalog.Repositories;

import com.Service.Catalog.Entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    @Query("SELECT m FROM Member m WHERE m.cin = ?1")
    Optional<Member> findByCin(String cin);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Member WHERE cin = ?1")
    void deleteByCin(String cin);
}
