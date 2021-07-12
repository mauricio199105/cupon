package com.ml.cupon.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.cupon.dto.CouponDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class CouponControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private MockMvc mock;

	@Test
	public void evaluateItemsWithRetunrOK() throws Exception {

		String[] item = {"MLA803174898", "MLA803174894", "MLA803174788", "MLA803086664", "MLA810645375", "MLA844702264"};
		CouponDTO coupon = CouponDTO.builder().amount(500.00f).item_ids(item).build();

		this.mock.perform(post("/coupon").contentType(APPLICATION_JSON_UTF8).content(asJsonString(coupon)))
				.andExpect(status().isOk());

	}

	@Test
	public void evaluateItemsWithRetunrNotFound() throws Exception {
	
		String[] item = {"MLA1", "MLA2", "MLA3", "MLA4", "MLA6", "MLA5"};
		CouponDTO coupon = CouponDTO.builder().amount(1f).item_ids(item).build();
		this.mock.perform(post("/coupon").contentType(APPLICATION_JSON_UTF8).content(asJsonString(coupon)))
				.andExpect(status().isNotFound());

	}


	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
