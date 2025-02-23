package com.project.e_commerce_api.enums;

import jakarta.persistence.AttributeConverter;

public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {
    @Override
    public String convertToDatabaseColumn(PaymentStatus paymentStatus) {
        if(paymentStatus == null) return null;

        return paymentStatus.name();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String dbValue) {
        if(dbValue == null) return null;

        for(PaymentStatus paymentStatus : PaymentStatus.values()){
            if(paymentStatus.name().equalsIgnoreCase(dbValue)) return paymentStatus;
        }

        throw new IllegalArgumentException("Unknown role: " + dbValue);
    }
}
