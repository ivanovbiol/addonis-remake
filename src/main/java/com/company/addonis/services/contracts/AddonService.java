package com.company.addonis.services.contracts;

import com.company.addonis.models.Addon;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AddonService {
    List<Addon> findAll();

    Optional<Addon> findById(int id);

    Optional<?> save(Addon addon);

    void delete(Addon addon);

    Optional<?> approve(Addon addon);

    Optional<?> feature(Addon addon);
}