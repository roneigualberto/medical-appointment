package com.example.app.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.app.domain.Doctor;
import com.example.app.repository.DoctorRepository;

@RunWith(MockitoJUnitRunner.class)
public class DoctorServiceTest {

	@InjectMocks
	private DoctorService doctorService;

	@Mock
	private DoctorRepository doctorRepository;

	@Test
	public void shouldSaveDoctor() {

		Doctor doctor =  Doctor.builder().name("Doctor").speciality("pediatrics").build();
		
		
		Doctor doctorRet =  Doctor.builder()
						.id(1L)
						.name("Doctor").speciality("pediatrics").build();
				
		given(doctorRepository.save(Mockito.any())).willReturn(doctorRet);
		
		Doctor doctorSaved = doctorService.save(doctor);
		
		assertThat(doctorSaved.getId(), is(doctorRet.getId()));

	}
	
	

}
