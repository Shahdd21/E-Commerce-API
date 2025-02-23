package com.project.e_commerce_api.enums;

import jakarta.persistence.AttributeConverter;

public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus orderStatus) {
        if(orderStatus == null) return null;

        return orderStatus.name();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String dbValue) {
        if(dbValue == null) return null;

        for(OrderStatus orderStatus : OrderStatus.values()){
            if(orderStatus.name().equalsIgnoreCase(dbValue)) return orderStatus;
        }

        throw new IllegalArgumentException("Unknown role: " + dbValue);
    }
}
