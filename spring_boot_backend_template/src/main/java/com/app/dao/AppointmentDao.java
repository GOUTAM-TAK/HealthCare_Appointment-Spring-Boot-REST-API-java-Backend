package com.app.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Appointment;

public interface AppointmentDao extends JpaRepository<Appointment, Integer> {

 Optional<List<Appointment>> findByDoctorName(String doctorName);

List<Appointment> findByDateTime(LocalDateTime time);



}
