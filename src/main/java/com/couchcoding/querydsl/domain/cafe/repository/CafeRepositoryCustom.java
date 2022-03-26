package com.couchcoding.querydsl.domain.cafe.repository;

import com.couchcoding.querydsl.domain.cafe.Cafe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CafeRepositoryCustom {
    Page<Cafe> findBySearchOption(Pageable pageable, String name, String city, String gu);
}
