package com.home.todoList.common.utilities.persistance;

import javax.persistence.AttributeConverter;

/**
 * Created by andrew on 26.10.15.
 */
public class BooleanToStringJpaConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return  (attribute == null || !attribute) ? "N" : "Y";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Y".equals(dbData);
    }
}
