package com.couchcoding.querydsl.domain.cafe.repository;

import com.couchcoding.querydsl.domain.cafe.Cafe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRepository extends JpaRepository<Cafe, Long>, CafeRepositoryCustom {
}
