package com.monolithic.cinema.controller;

import com.monolithic.cinema.dto.Response.RequestPaymentResponse;
import com.monolithic.cinema.dto.Response.VNPayReturnOrderResponse;
import com.monolithic.cinema.service.BookingService;
import com.monolithic.cinema.service.VNPayService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/v1/payments/vnpay")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VNPayController {
    VNPayService vnPayService;
    BookingService bookingService;

    @GetMapping
    public RequestPaymentResponse createOrder(@RequestParam int amount, @RequestParam String bookingId) throws UnsupportedEncodingException {
        bookingService.startPayment(bookingId);
        return vnPayService.createOrder(amount, bookingId);
    }

    @GetMapping("/return-order")
    public VNPayReturnOrderResponse returnOrder(
            @RequestParam Integer vnp_Amount,
            @RequestParam String vnp_OrderInfo,
            @RequestParam String vnp_ResponseCode,
            @RequestParam String vnp_TransactionNo
    ) {
        return vnPayService.returnOrder(vnp_Amount, vnp_OrderInfo, vnp_ResponseCode, vnp_TransactionNo);
    }
}
