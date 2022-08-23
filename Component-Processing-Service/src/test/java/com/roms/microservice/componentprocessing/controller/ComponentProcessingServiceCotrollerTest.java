//package com.roms.microservice.componentprocessing.controller;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.math.BigDecimal;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentMatchers;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.roms.microservice.componentprocessing.AbstractTest;
//import com.roms.microservice.componentprocessing.exception.TokenInvalidException;
//import com.roms.microservice.componentprocessing.feignclient.AuthFeignClient;
//import com.roms.microservice.componentprocessing.payload.DefectiveComponentDetail;
//import com.roms.microservice.componentprocessing.payload.ReturnRequestPayload;
//import com.roms.microservice.componentprocessing.payload.ReturnResponsePayload;
//import com.roms.microservice.componentprocessing.service.ReturnComponentServiceImpl;
//
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//class ComponentProcessingServiceCotrollerTest extends AbstractTest {
//
//	private String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJHYW5lc2giLCJpYXQiOjE2NTI2MjQ5MjYsImV4cCI6OTIyMzM3MjAzNjg1NDc3NX0.-kpj4_dVZz8NJ2bi1Qn5-h8bXsjuA_WhzyTphhDFA_o";
//
//	@MockBean
//	private ReturnComponentServiceImpl service;
//
//	@MockBean
//	private AuthFeignClient client;
//
//	@BeforeEach
//	public void beforeTestSetup() throws TokenInvalidException {
//		when(client.validateAndReturnUser(ArgumentMatchers.anyString())).thenReturn(true);
//	}
//
//	@Test
//	public void createReturnRequestTest() throws Exception {
//
//		String uri = "/return-component/createReturnRequest";
//		ReturnRequestPayload request = new ReturnRequestPayload();
//		request.setContactNumber(1234567890l);
//		request.setName("Ganesh");
//		DefectiveComponentDetail defectiveComponentDetail = new DefectiveComponentDetail();
//		defectiveComponentDetail.setComponentName("Component Name");
//		defectiveComponentDetail.setComponentType("INTEGRAL");
//		defectiveComponentDetail.setQuantity(1);
//		request.setDefectiveComponentDetail(defectiveComponentDetail);
//		
//		String inputJson = super.mapToJson(request);
//		
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
//				.header("Authorization", this.token).content(inputJson)).andReturn();
//		
//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(201, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		ReturnResponsePayload res = super.mapFromJson(content, ReturnResponsePayload.class);
//		System.out.println(res);
//		assertEquals(res.getProcessingCharge(), new BigDecimal(500));
//	}
//}
