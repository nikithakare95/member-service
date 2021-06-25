package com.dlightsaber.member.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.dlightsaber.member.MemberApplication;
import com.dlightsaber.member.model.Register;
import com.dlightsaber.member.repository.RegistrationRepository;
import com.dlightsaber.member.service.RegistrationService;

@ContextConfiguration(classes = MemberApplication.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RegistrationControllerTests {

	@Autowired
	RegistrationRepository registrationrepo;

	@Autowired
	RegistrationService service;

	@Autowired
	RegistrationController cont;

	@Test
	public void getAllErrorsTest() throws Exception {
		Register reg = new Register();
		reg.setFirstName("123456");
		reg.setLastName("abcd");
		reg.setConfirmPassword("abc");
		reg.setPassword("abc");
		reg.setEmail("abcgmail.com");
		reg.setPhoneNumber("9796959498");
		BindingResult result = mock(BindingResult.class);
		Mockito.when(result.hasErrors()).thenReturn(true);
		ResponseEntity<Object> rs = cont.addUserData(reg, result);
		assertEquals(400, rs.getStatusCode().value());

	}

	@Test
	public void getAllErrorsTest5() throws Exception {
		Register reg = new Register();
		reg.setFirstName("123456");
		reg.setLastName("abcd");
		reg.setConfirmPassword("abc");
		reg.setPassword("abc12");
		reg.setEmail("abcgmail.com");
		reg.setPhoneNumber("99999999");
		service = mock(RegistrationService.class);
		Mockito.when(service.isDuplicatePhoneNumber(reg)).thenReturn(true);
		BindingResult result = mock(BindingResult.class);
		Mockito.when(result.hasErrors()).thenReturn(false);
		ResponseEntity<Object> rs = cont.addUserData(reg, result);
		assertEquals(400, rs.getStatusCode().value());

	}

	@Test
	public void getAllErrorsTest6() throws Exception {
		Register reg = new Register();
		reg.setFirstName("nikita");
		reg.setLastName("abcd");
		reg.setConfirmPassword("abc");
		reg.setPassword("abc");
		service = mock(RegistrationService.class);
		Mockito.when(service.isDuplicatePhoneNumber(reg)).thenReturn(false);
		BindingResult result = mock(BindingResult.class);
		Mockito.when(result.hasErrors()).thenReturn(false);
		ResponseEntity<Object> rs = cont.addUserData(reg, result);
		assertEquals(202, rs.getStatusCode().value());

	}

	@Test
	public void getAllErrorsTest3() throws Exception {
		assertThat(cont).isNotNull();
	}

	@Test
	public void getAllErrorsTest2() throws Exception {
		Register reg = new Register();
		reg.setFirstName("abcdgsjk");
		reg.setLastName("Thakare");
		reg.setConfirmPassword("abc");
		reg.setPassword("abc");
		reg.setEmail("abc@gmail.com");
		reg.setAddress("phase1");
		reg.setCity("Pune");
		reg.setZip("12345");
		reg.set_id(null);
		reg.setPhoneNumber("9411020202");
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("firstName", "Only Characters are allowed");
		BindingResult result = mock(BindingResult.class);
		Mockito.when(result.hasErrors()).thenReturn(false);
		when(cont.addUserData(reg, result));
		ResponseEntity<Object> rs = cont.addUserData(reg, result);
		assertEquals(400, rs.getStatusCode().value());

	}

	@Test
	public void test2() throws Exception {
		Register reg = new Register();
		reg.setFirstName("Nikita");
		reg.setLastName("Thakare");
		reg.setConfirmPassword("abc23");
		reg.setPassword("abc");
		reg.setEmail("abc@gmail.com");
		reg.setAddress("phase1");
		reg.setCity("Pune");
		reg.setZip("12345");
		reg.set_id(null);
		reg.setPhoneNumber("9411020202");
		BindingResult result = mock(BindingResult.class);
		Mockito.when(result.hasErrors()).thenReturn(false);
		when(cont.getAllErrors(reg, result));
		ResponseEntity<Object> rs = cont.addUserData(reg, result);
		assertEquals(400, rs.getStatusCode().value());

	}

	@Test
	public void test7() throws Exception {
		Register reg = new Register();
		reg.setFirstName("xyz");
		reg.setLastName("ywdnsjak");
		reg.setConfirmPassword("xyz");
		reg.setPassword("xyz");
		reg.setEmail("xyz@gmail.com");
		service = mock(RegistrationService.class);
		List<String> em = new ArrayList<>();
		em.add("xyz@gmail.com");
		Mockito.when(service.isDuplicatePhoneNumber(reg)).thenReturn(false);
		Mockito.when(service.getEmailList()).thenReturn(em);
		BindingResult result = mock(BindingResult.class);
		Mockito.when(result.hasErrors()).thenReturn(false);
		ResponseEntity<Object> rs = cont.addUserData(reg, result);
		assertEquals(400, rs.getStatusCode().value());

	}

	@Test
	public void test8() throws Exception {

		String fixedField = "FirstName";
		Object[] arguments = null;
		String[] codes = { "200" };
		Register reg = new Register();
		reg.setFirstName("12345");
		BindingResult bindingResult = new BeanPropertyBindingResult(fixedField, "register");
		FieldError error = new FieldError(bindingResult.getObjectName(), fixedField, "", true, codes, arguments,
				"Field '" + fixedField + "' is required");
		bindingResult.addError(error);
		List<FieldError> errors = bindingResult.getFieldErrors();
		for (FieldError fieldError : errors) {
			assertNotNull(fieldError.getField());
			assertNotNull(fieldError.getDefaultMessage());
			HashMap<String, String> hashmap = new HashMap<String, String>();
			hashmap.put(fieldError.getField(), fieldError.getDefaultMessage());
			assertNotNull(hashmap);
		}
		when(cont.getAllErrors(reg, bindingResult));
	}
}
