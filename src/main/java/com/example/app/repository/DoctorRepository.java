package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.domain.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
