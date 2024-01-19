package com.app.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentReqDto {
	@NotBlank(message = "patient name must be required")
	@Length(min = 2,max = 40,message = "name required length of 2 to 40 characters")
	private String patientName;
    @NotBlank(message = "doctor name must be required")
	@Length(min = 2,max = 40,message = "name required length of 2 to 40 characters")
	private String doctorName;
    @Future(message = "appointment date time must be in future")
	private LocalDateTime dateTime;
}
