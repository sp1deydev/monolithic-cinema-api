package com.monolithic.cinema.dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VNPayReturnOrderResponse {
    String id;
    String bookingId;
    String status;
    Integer amount;
    String paymentMethod;
}