package com.dlightsaber.member.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.dlightsaber.member.MemberApplication;
import com.dlightsaber.member.model.Register;
import com.dlightsaber.member.repository.RegistrationRepository;

@ContextConfiguration(classes = MemberApplication.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RegistrationServiceTests {
	@Autowired
	RegistrationRepository registrationrepo;

	@Autowired
	RegistrationService service;

	@Test
	public void test() {
		List<Register> reg = registrationrepo.findAllByemail(null);
		List<Register> emails=service.getEmails(null);
		assertNotNull(reg);
		assertNotNull(emails);
	}

}
