package com.ml.cupon.consumer.service;

import java.util.List;

import com.ml.cupon.dto.CouponDTO;
import com.ml.cupon.dto.ItemResponseDTO;

public interface ConsumerService {
	
	List<ItemResponseDTO> getItems(CouponDTO coupo);

}
