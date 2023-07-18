package com.aakhyatech.smartcall.application.utils;

import java.math.BigDecimal;


import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class BigDecimalToDoubleConverter implements Converter<Double, BigDecimal> {

	@Override
	public Result<BigDecimal> convertToModel(Double value, ValueContext context) {
		if(null != value) {
		Result<BigDecimal> result = Result.ok(new BigDecimal(value));
			return result;
		}
		return null;
	}

	@Override
	public Double convertToPresentation(BigDecimal value, ValueContext context) {
		if(null != value)
			return value.doubleValue();
		return null;
	}


}
