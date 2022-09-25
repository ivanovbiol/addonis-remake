package com.company.addonis.controllers;

import com.company.addonis.models.Addon;
import com.company.addonis.services.contracts.AddonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/addon")
public class AddonController {

    @Autowired
    private AddonService addonService;

    @GetMapping
    public ResponseEntity<List<Addon>> findAll() {
        return ResponseEntity.ok(addonService.findAll());
    }

    @GetMapping("/{id}")
    @RolesAllowed("authorised-user")
    public ResponseEntity<Addon> findById(@Valid @PathVariable int id) {
        return ResponseEntity.of(addonService.findById(id));
    }

    @PostMapping
    @RolesAllowed("authorised-user")
    public ResponseEntity<?> create(@Valid @RequestBody Addon addon) {
        return ResponseEntity.of(addonService.save(addon));
    }

    @PutMapping
    @RolesAllowed("admin")
    public ResponseEntity<?> update(@Valid @RequestBody Addon addon) {
        return ResponseEntity.of(addonService.save(addon));
    }

    @DeleteMapping
    @RolesAllowed("admin")
    public void delete(@Valid @RequestBody Addon addon) {
        addonService.delete(addon);
    }

    @PutMapping("/approve")
    @RolesAllowed("admin")
    public ResponseEntity<?> approve(@Valid @RequestBody Addon addon) {
        return ResponseEntity.of(addonService.approve(addon));
    }

    @PutMapping("/feature")
    public ResponseEntity<?> feature(@Valid @RequestBody Addon addon) {
        return ResponseEntity.of(addonService.feature(addon));
    }
}