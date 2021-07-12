package com.ml.cupon.service;

import java.util.List;
import java.util.Map;

public interface UtilityService {

	List<String> calculate(Map<String, Float> items, Float amount);
}
