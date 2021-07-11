package com.ml.cupon.consumer.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ml.cupon.consumer.service.ConsumerService;
import com.ml.cupon.dto.CouponDTO;
import com.ml.cupon.dto.ItemResponseDTO;

@Component
public class ConsumerServiceImpl implements ConsumerService {
	
	public static final String URL_BASE = "https://api.mercadolibre.com/items?ids=";  

	private RestTemplate restTemplate;

	public ConsumerServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<ItemResponseDTO> getItems(CouponDTO cupon) {

		List<ItemResponseDTO> itemsResponse = restTemplate.exchange(buildUrl(cupon), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<ItemResponseDTO>>() {
				}).getBody();
		return itemsResponse;
	}

	private String buildUrl(CouponDTO cupon) {
		StringBuilder url = new StringBuilder();
		url.append(URL_BASE);
		for (String id : cupon.getItem_ids()) {
			url.append(id);
			url.append(",");
		}
		url.deleteCharAt(url.length() - 1);
		url.append("&attributes=id,price");
		return url.toString();
	}

}
