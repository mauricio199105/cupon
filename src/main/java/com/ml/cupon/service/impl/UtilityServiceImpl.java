package com.ml.cupon.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ml.cupon.model.Backpack;
import com.ml.cupon.model.ElementBackpack;
import com.ml.cupon.service.UtilityService;

@Component
public class UtilityServiceImpl implements UtilityService {

	public List<String> calculate(Map<String, Float> items, Float amount) {

		ElementBackpack[] elements = generateStructure(items);

		Backpack backpack_base = new Backpack(amount, elements.length);
		Backpack backpack_opt = new Backpack(amount, elements.length);

		fillBackpack(backpack_base, elements, backpack_opt, false);

		List<String> result = generateList(backpack_opt);

		return result;
	}

	private List<String> generateList(Backpack backpack_opt) {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < backpack_opt.getElements().length; i++) {
			if (backpack_opt.getElements()[i] != null) {
				result.add(backpack_opt.getElements()[i].getId());
			}

		}

		result.add(String.valueOf(backpack_opt.getWeight()));
		return result;
	}

	private ElementBackpack[] generateStructure(Map<String, Float> items) {
		ElementBackpack[] elements = new ElementBackpack[items.size()];
		int count = 0;
		for (Map.Entry<String, Float> entry : items.entrySet()) {
			elements[count] = new ElementBackpack(entry.getValue(), count, entry.getKey());
			count++;
		}
		return elements;
	}

	private void fillBackpack(Backpack backpack_base, ElementBackpack[] elements, Backpack backpack_opt,
			boolean status) {
		// Validate if the backpack is full
		if (status) {
			//check if one backpack has more benefit than the other
			if (backpack_base.getBenefit() > backpack_opt.getBenefit()) {
				
				ElementBackpack[] elementsBases = backpack_base.getElements();
				backpack_opt.clearBakcpack();
				
				for (ElementBackpack element : elementsBases) {
					if (element != null) {
						backpack_opt.addElement(element);
					}
				}
			}
		} else {
			for (int i = 0; i < elements.length; i++) {
				// Lo controlo sin mas de uno
				if (!backpack_base.existElement(elements[i])) {
					//Si el peso de la mochila se supera, quiere decir que esta llena
					if (backpack_base.getMaxWeight() >= backpack_base.getWeight() + elements[i].getWeight()) {
						backpack_base.addElement(elements[i]);
						fillBackpack(backpack_base, elements, backpack_opt, false);
						// Lo quito para que haga las combinaciones
						backpack_base.deleteElement(elements[i]);
					} else if(backpack_base.getMaxWeight() == elements[i].getWeight()) {
						backpack_opt.clearBakcpack();
						backpack_opt.addElement(elements[i]);
						break;
						
					} else {
						fillBackpack(backpack_base, elements, backpack_opt, true);
					}
				}
			}
		}

	}

}
