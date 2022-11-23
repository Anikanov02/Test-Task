package com.test.task.domain.converter;

import com.test.task.GlobalConstants;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, String> {
    @Override
    public String convertToDatabaseColumn(LocalDate attribute) {
        return attribute.format(GlobalConstants.formatter);
    }

    @Override
    public LocalDate convertToEntityAttribute(String dbData) {
        return LocalDate.parse(dbData, GlobalConstants.formatter);
    }
}
