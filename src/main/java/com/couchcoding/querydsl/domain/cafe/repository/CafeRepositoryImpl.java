package com.couchcoding.querydsl.domain.cafe.repository;

import com.couchcoding.querydsl.domain.cafe.Cafe;
import static com.couchcoding.querydsl.domain.cafe.QCafe.cafe;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CafeRepositoryImpl extends QuerydslRepositorySupport implements CafeRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    public CafeRepositoryImpl() {
        super(Cafe.class);
    }
    
    @Override
    public Page<Cafe> findBySearchOption(Pageable pageable, String name, String city, String gu) {
        JPQLQuery<Cafe> query =  queryFactory.selectFrom(cafe)
                                .where(eqCity(city), eqGu(gu), containName(name));

        List<Cafe> cafes = this.getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<Cafe>(cafes, pageable, query.fetchCount());
    }

    private BooleanExpression eqCity(String city) {
        if(city == null || city.isEmpty()) {
            return null;
        }
        return cafe.city.eq(city);
    }

    private BooleanExpression containName(String name) {
        if(name == null || name.isEmpty()) {
            return null;
        }
        return cafe.name.containsIgnoreCase(name);
    }

    private BooleanExpression eqGu(String gu) {
        if(gu == null || gu.isEmpty()) {
            return null;
        }
        return cafe.gu.eq(gu);
    }
}
  