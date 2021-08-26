package com.kodilla.accounts.service;

import com.kodilla.accounts.exception.SenderAccountNotFoundException;
import com.kodilla.commons.Transfer;
import com.kodilla.commons.TransferMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferListener {

    private final AccountService accountService;

    @KafkaListener(topics = "created-transfers")
    public void onTransferCreated(@Payload TransferMessage message) {
        Transfer transfer = message.getTransfer();
        try {
            accountService.updateBalances(transfer.getAmount(), transfer.getSenderAccount(),
                    transfer.getRecipientAccount());
        } catch (SenderAccountNotFoundException e) {
            log.error("Sender account not found for transfer: " + transfer, e);
        }
    }
}
