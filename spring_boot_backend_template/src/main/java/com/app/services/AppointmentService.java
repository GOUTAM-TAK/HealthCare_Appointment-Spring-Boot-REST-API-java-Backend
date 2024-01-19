package com.app.services;

import java.util.List;

import com.app.dto.AppointmentReqDto;
import com.app.entities.Appointment;
import com.app.exception.AppointmentException;

public interface AppointmentService {
        Appointment createAppointment(AppointmentReqDto newAppointment) throws AppointmentException;
        Appointment getUpcommingAppointment(int id)throws AppointmentException;
        Appointment cancelAppointment(int id)throws AppointmentException;
        List<Appointment> getUpcomingAppointments()throws AppointmentException;
        Appointment updateAppointment(int id,AppointmentReqDto temp)throws AppointmentException;
}
