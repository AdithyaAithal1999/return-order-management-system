//package com.returnordermanag.packaginganddeliveryservice.controller;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.returnordermanag.packaginganddeliveryservice.AbstractTest;
//import com.returnordermanag.packaginganddeliveryservice.exception.ExceptionResponse;
//
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//class PackagingAndDeliveryControllerTest extends AbstractTest {
//
//	@Test
//	public void GetPackagingDeliveryChargeTest() throws Exception {
//		String uri = "/packaginganddelivery/packaginganddeliverycharge/{componentType}/{count}";
//
//		MvcResult mvcResult = mvc
//				.perform(MockMvcRequestBuilders.get(uri, "Integral", 1).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk()).andReturn();
//		System.out.println(mvcResult.getResponse().toString());
//
//		String content = mvcResult.getResponse().getContentAsString();
//		assertEquals("350.00", content);
//		System.out.println(content);
//		// assertEquals(res.getUserName(), "Ganesh");
//	}
//
//	@Test
//	public void GetPackagingDeliveryChargeInvalidComponentTypeTest() throws Exception {
//		String cmp = "Integra0l";
//		String uri = "/packaginganddelivery/packaginganddeliverycharge/" + cmp + "/{count}";
//
//		MvcResult mvcResult = mvc
//				.perform(MockMvcRequestBuilders.get(uri, 1).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isNotFound()).andReturn();
//
//		ExceptionResponse exRes = mapFromJson(mvcResult.getResponse().getContentAsString(), ExceptionResponse.class);
//		String content = mvcResult.getResponse().getContentAsString();
//		System.out.println("RESOPONSE: " + content);
//		assertEquals("Invalid Component type: " + cmp, exRes.getMessage());
//		System.out.println(content);
//		// assertEquals(res.getUserName(), "Ganesh");
//	}
//
//	@Test
//	public void GetPackagingDeliveryChargeNegativeCountValueTest() throws Exception {
//		String cmp = "Integral";
//		String uri = "/packaginganddelivery/packaginganddeliverycharge/" + cmp + "/{count}";
//
//		MvcResult mvcResult = mvc
//				.perform(MockMvcRequestBuilders.get(uri, -1).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isNotFound()).andReturn();
//
//		ExceptionResponse exRes = mapFromJson(mvcResult.getResponse().getContentAsString(), ExceptionResponse.class);
//		String content = mvcResult.getResponse().getContentAsString();
//		System.out.println("RESOPONSE: " + content);
//		assertEquals("Item count should be greater than zero", exRes.getMessage());
//		System.out.println(content);
//		// assertEquals(res.getUserName(), "Ganesh");
//	}
//}
