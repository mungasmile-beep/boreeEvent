// package com.example.demo.repository;
// import com.example.demo.model.Couple;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
// @Repository
// public interface CoupleRepository extends JpaRepository<Couple, Long> {}
package com.example.demo.repository;

import com.example.demo.model.Couple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoupleRepository extends JpaRepository<Couple, Long> {
    // JpaRepository fournit déjà findById, save, delete, etc.
}