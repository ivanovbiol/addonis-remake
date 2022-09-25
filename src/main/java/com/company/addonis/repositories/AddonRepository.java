package com.company.addonis.repositories;

import com.company.addonis.models.Addon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddonRepository extends JpaRepository<Addon, Integer> {
    List<Addon> findByCreatorId(int id);

    Optional<Addon> findByName(String name);
}