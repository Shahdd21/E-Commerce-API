package com.project.e_commerce_api.enums;

import jakarta.persistence.AttributeConverter;

public class PaymentMethodConverter implements AttributeConverter<PaymentMethod, String> {
    @Override
    public String convertToDatabaseColumn(PaymentMethod paymentMethod) {
        if(paymentMethod == null) return null;

        return paymentMethod.name();
    }

    @Override
    public PaymentMethod convertToEntityAttribute(String dbValue) {
        if(dbValue == null) return null;

        for(PaymentMethod paymentMethod : PaymentMethod.values()){
            if(paymentMethod.name().equalsIgnoreCase(dbValue)) return paymentMethod;
        }

        throw new IllegalArgumentException("Unknown role: " + dbValue);
    }
}
