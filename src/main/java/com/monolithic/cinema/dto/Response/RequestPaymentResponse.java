package com.monolithic.cinema.dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestPaymentResponse {
    String bookingId;
    String paymentUrl;
}