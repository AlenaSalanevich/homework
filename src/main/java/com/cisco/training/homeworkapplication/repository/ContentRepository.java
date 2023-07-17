package com.cisco.training.homeworkapplication.repository;

import com.cisco.training.homeworkapplication.model.Content;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ContentRepository extends CrudRepository<Content, Integer> {

    @Query("select distinct c from Contents c join c.tags tag join tags.children child where tag.name like :name or child.name like :name")
    Collection<Content> getAllByTag(String name);
}
