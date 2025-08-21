package com.monthly.blogapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("select b from Blog b where lower(b.title) like lower(concat('%'," +
            ":term,'%') ) or lower(b.category)" +
            " like lower(concat('%',:term,'%')) or lower(b.content) like " +
            "(lower(concat('%',:term,'%') )) ")
    List<Blog> searchByTerm(@Param("term") String term);
}
