package com.carrefour.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DeliveryMethodEnum implements Serializable {
    DRIVE,
    DELIVERY,
    DELIVERY_TODAY,
    DELIVERY_ASAP
}
