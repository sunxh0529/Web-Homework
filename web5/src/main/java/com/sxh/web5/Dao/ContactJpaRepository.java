package com.sxh.web5.Dao;

import com.sxh.web5.Data.ContactInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContactJpaRepository extends JpaRepository<ContactInfor, Long> {
    @Transactional
    void deleteByContactname(String cname);
    List<ContactInfor> findByContactname(String cname);
}
