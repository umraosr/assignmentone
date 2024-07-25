package com.dws.challenge.controller;

import com.dws.challenge.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<String> transfer(@RequestParam String accountFromId, 
                                           @RequestParam String accountToId,
                                           @RequestParam double amount) {
        transferService.transfer(accountFromId, accountToId, amount);
        return ResponseEntity.ok("Transfer successful");
    }
}
