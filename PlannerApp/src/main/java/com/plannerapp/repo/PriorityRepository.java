package com.plannerapp.repo;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.PriorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    Priority findByName(PriorityName priority);
}
