package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AppointmentReqDto;
import com.app.entities.Appointment;
import com.app.services.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	@Autowired
	private AppointmentService as;

	
	@PostMapping
	public ResponseEntity<?> createAppointment(@RequestBody @Valid AppointmentReqDto newAppointment)
	{
		Appointment ap=null;
		try {
		ap=	as.createAppointment(newAppointment);
		}
		catch(Exception ex)
		{
		return	ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
		}
		return	ResponseEntity.status(HttpStatus.CREATED).body("Successfully appointment register !! your details are : "+ap.toString());
	}
	@GetMapping("/{userId}/upcoming")
	public ResponseEntity<?> getUpcomingAppointment(@PathVariable int userId)
	{
		Appointment ap=null;
		try {
		ap=	as.getUpcommingAppointment(userId);
		}
		catch(Exception ex)
		{
		return	ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		return	ResponseEntity.status(HttpStatus.OK).body("your upcoiming appointment details are : "+ap.toString());
	}
	@GetMapping("/upcoming")
	public ResponseEntity<?> getUpcomingAppointments()
	{
		List<Appointment> list=null;
		try {
		list=as.getUpcomingAppointments();
		}
		catch(Exception ex)
		{
		return	ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		return	ResponseEntity.status(HttpStatus.OK).body(list);
	}
	@DeleteMapping("/{appointmentId}")
	public ResponseEntity<?> deleteAppointment(@PathVariable int appointmentId)
	{
		Appointment ap=null;
		try {
		ap=	as.cancelAppointment(appointmentId);
		}
		catch(Exception ex)
		{
		return	ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		return	ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully Cancel !!cancel appointment details are : "+ap.toString());
	}
	@PutMapping("/{userId}")
	public ResponseEntity<?> updateAppointment(@PathVariable int userId,@RequestBody @Valid AppointmentReqDto temp)
	{
		Appointment ap=null;
		try {
		ap=	as.updateAppointment(userId, temp);
		}
		catch(Exception ex)
		{
		return	ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		return	ResponseEntity.status(HttpStatus.OK).body("your appointment updated!! details are : "+ap.toString());
	}
	
}
