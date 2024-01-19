package com.app.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AppointmentDao;
import com.app.dto.AppointmentReqDto;
import com.app.entities.Appointment;
import com.app.exception.AppointmentException;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private AppointmentDao ad;

	@Override
	public Appointment createAppointment(AppointmentReqDto newAppointment) throws AppointmentException {
		// TODO Auto-generated method stub
		Appointment newAppoint = mapper.map(newAppointment, Appointment.class);
		LocalDateTime time = newAppoint.getDateTime();
		List<Appointment> list = ad.findByDateTime(time);
		if (!list.isEmpty()) {
			if (!list.stream().filter(a -> a.getDoctorName().equals(newAppointment.getDoctorName()))
					.collect(Collectors.toList()).isEmpty()) {
				throw new AppointmentException(
						"No appointment available on this time!!!!please select different time.");
			}

		}
		ad.save(newAppoint);
		return newAppoint;
	}

	@Override
	public Appointment getUpcommingAppointment(int id) throws AppointmentException {
		// TODO Auto-generated method stub
		Appointment ap = ad.findById(id)
				.orElseThrow(() -> new AppointmentException("No any user register with this id!!"));
		if (ap.getDateTime().isAfter(LocalDateTime.now()))
			return ap;
		throw new AppointmentException("No upcomming appointment available for that user!!!");
	}

	@Override
	public Appointment cancelAppointment(int id) throws AppointmentException {
		// TODO Auto-generated method stub
		Appointment ap = ad.findById(id)
				.orElseThrow(() -> new AppointmentException("No any user register with this id!!"));
		ad.deleteById(id);
		return ap;
	}

	@Override
	public List<Appointment> getUpcomingAppointments() throws AppointmentException {
		// TODO Auto-generated method stub

		List<Appointment> list = ad.findAll();
		
		if (list.isEmpty())
			throw new AppointmentException("No upcoming appointments available !!!!");
		list=list.stream().filter(s->s.getDateTime().isAfter(LocalDateTime.now())).collect(Collectors.toList());
		if (list.isEmpty())
			throw new AppointmentException("No upcoming appointments available !!!!");
		return list;
	}

	@Override
	public Appointment updateAppointment(int id, AppointmentReqDto temp) throws AppointmentException {
		// TODO Auto-generated method stub
		Appointment updated=mapper.map(temp, Appointment.class);
		updated.setId(id);
		Appointment appointment=ad.findById(id).orElseThrow(()->new AppointmentException("Invalid appointment id!!"));
		mapper.map(updated, appointment);
		
	
		return appointment;
	}

	
}
