package com.example.domain.user;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;

/**
 * Created by yshpyluk on 5/2/17.
 */
@Converter
public class BirthdayConverter implements AttributeConverter<LocalDate, String> {
	private final static String SEPARATOR = ":";

	@Override
	public String convertToDatabaseColumn(LocalDate attribute) {
		if (attribute == null) return null;
		StringBuilder sb = new StringBuilder();
		sb.append(attribute.getYear())
				.append(SEPARATOR)
				.append(attribute.getMonthValue())
				.append(SEPARATOR)
				.append(attribute.getDayOfMonth());
		return sb.toString();
	}

	@Override
	public LocalDate convertToEntityAttribute(String dbData) {
		if (dbData==null) return null;
		String[] temp = dbData.split(SEPARATOR);
		return LocalDate.of(
				Integer.parseInt(temp[0]),
				Integer.parseInt(temp[1]),
				Integer.parseInt(temp[2])
		);
	}
}
