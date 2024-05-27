package com.carrefour.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "ds_delivery", schema = "delivery_service")
public class DeliveryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private DeliveryMethodEnum method;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "time_slot_id", referencedColumnName = "id")
    private TimeSlotEntity timeSlot;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;

}
