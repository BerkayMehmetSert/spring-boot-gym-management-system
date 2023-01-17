package com.bms.gymmanagementsystem.service;

import com.bms.gymmanagementsystem.dto.TransactionDto;
import com.bms.gymmanagementsystem.dto.converter.TransactionDtoConverter;
import com.bms.gymmanagementsystem.exception.TrainerNotFoundException;
import com.bms.gymmanagementsystem.helper.DateHelper;
import com.bms.gymmanagementsystem.model.Client;
import com.bms.gymmanagementsystem.model.Payment;
import com.bms.gymmanagementsystem.model.Transaction;
import com.bms.gymmanagementsystem.repository.TransactionRepository;
import com.bms.gymmanagementsystem.request.transaction.CreateTransactionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ClientService clientService;
    private final TransactionDtoConverter converter;
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(TransactionRepository transactionRepository,
                              ClientService clientService,
                              TransactionDtoConverter converter) {
        this.transactionRepository = transactionRepository;
        this.clientService = clientService;
        this.converter = converter;
    }

    public void createTransaction(final CreateTransactionRequest request) {
        Client client = clientService.getClientById(request.getClientId());

        Transaction transaction = new Transaction(
                request.getName(),
                calculateAmount(client),
                DateHelper.getCurrentDate(),
                client
        );

        transactionRepository.save(transaction);
        logger.info("Transaction created successfully");
    }

    public void updateTransactionName(final String id, final String name) {
        Transaction transaction = getTransactionById(id);

        Transaction updatedTransaction = new Transaction(
                transaction.getId(),
                name,
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getClient(),
                transaction.getReports()
        );

        transactionRepository.save(updatedTransaction);
        logger.info("Transaction name updated successfully to {}, for transaction with id {}", name, id);
    }

    public void deleteTransaction(final String id) {
        transactionRepository.delete(getTransactionById(id));
        logger.info("Transaction deleted successfully");
    }

    public Set<TransactionDto> findAllTransactions() {
        Set<Transaction> transactions = new HashSet<>(transactionRepository.findAll());

        if (transactions.isEmpty()) {
            logger.warn("Transaction list is empty");
            throw new TrainerNotFoundException("Transaction list is empty");
        }

        logger.info("Transaction list retrieved successfully with size {}", transactions.size());
        return converter.convert(transactions);
    }

    protected Transaction getTransactionById(final String id) {
        return transactionRepository.findById(id).orElseThrow(() -> {
            logger.error("Transaction with id {} not found", id);
            throw new TrainerNotFoundException("Transaction with id " + id + " not found");
        });
    }

    private Double calculateAmount(Client client) {
        double amount = Objects.requireNonNull(client.getPayments()).stream()
                .mapToDouble(Payment::getAmount)
                .sum();
        final double tax = 0.18;

        return amount + (amount * tax);
    }
}
