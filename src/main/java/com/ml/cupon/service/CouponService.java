package com.ml.cupon.service;

import com.ml.cupon.dto.CouponDTO;
import com.ml.cupon.dto.CouponDtoResponse;

public interface CouponService {

	CouponDtoResponse evaluateCoupon(CouponDTO coupon);

}
