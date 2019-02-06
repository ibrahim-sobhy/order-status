package com.alten.training.order.status.model;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Order {
    private Long id;
    private String description;
}
