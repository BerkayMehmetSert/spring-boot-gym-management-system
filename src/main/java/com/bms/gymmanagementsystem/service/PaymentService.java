package com.bms.gymmanagementsystem.service;

import com.bms.gymmanagementsystem.dto.PaymentDto;
import com.bms.gymmanagementsystem.dto.converter.PaymentDtoConverter;
import com.bms.gymmanagementsystem.exception.PaymentNotFoundException;
import com.bms.gymmanagementsystem.helper.DateHelper;
import com.bms.gymmanagementsystem.model.Payment;
import com.bms.gymmanagementsystem.repository.PaymentRepository;
import com.bms.gymmanagementsystem.request.payment.CreatePaymentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ClientService clientService;
    private final PaymentDtoConverter converter;
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public PaymentService(PaymentRepository paymentRepository,
                          ClientService clientService,
                          PaymentDtoConverter converter) {
        this.paymentRepository = paymentRepository;
        this.clientService = clientService;
        this.converter = converter;
    }

    public void createPayment(final CreatePaymentRequest request) {
        Payment payment = new Payment(
                DateHelper.getCurrentDate(),
                request.getAmount(),
                clientService.getClientById(request.getClientId())
        );

        paymentRepository.save(payment);
        logger.info("Payment created successfully");
    }

    public void updatePaymentAmount(final String id, final Double amount) {
        Payment payment = getPaymentById(id);

        Payment updatedPayment = new Payment(
                payment.getId(),
                payment.getDate(),
                amount,
                payment.getClient()
        );

        paymentRepository.save(updatedPayment);
        logger.info("Payment amount updated successfully to {}. Id: {}", amount, id);
    }

    public void deletePayment(final String id) {
        paymentRepository.delete(getPaymentById(id));
        logger.info("Payment deleted successfully. Id: {}", id);
    }

    public Set<PaymentDto> findPaymentByClientId(final String clientId) {
        Set<Payment> payments = paymentRepository.findByClient_Id(clientId);

        if (payments.isEmpty()) {
            logger.warn("No payments found for client with id: {}", clientId);
            throw new PaymentNotFoundException("No payments found for client with id: " + clientId);
        }

        logger.info("Found {} payments for client with id: {}", payments.size(), clientId);
        return converter.convert(payments);
    }

    public Set<PaymentDto> findAllPayments() {
        Set<Payment> payments = new HashSet<>(paymentRepository.findAll());

        if (payments.isEmpty()) {
            logger.error("Payment list is empty");
            throw new PaymentNotFoundException("Payment list is empty");
        }

        logger.info("Payment list found successfully with size: {}", payments.size());
        return converter.convert(payments);
    }

    private Payment getPaymentById(final String id) {
        return paymentRepository.findById(id).orElseThrow(() -> {
            logger.error("Payment not found. Id: {}", id);
            throw new PaymentNotFoundException("Payment not found. Id: " + id);
        });
    }
}
