package com.example.banksystemclasses.repository;

import com.example.banksystemclasses.model.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Long>
{

}
