//package com.techelevator.tenmo.checker;
//
//import com.techelevator.tenmo.model.TransferDTO;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import javax.validation.Valid;
//import java.math.BigDecimal;
//
//@Component
//public class TransferChecks {
//
////    public void performTransfer(TransferDTO transferDTO) {
////        //1. check transfer is valid, e.g. insufficient funds etc
////        if (isTransferValid()) {//you can use boolean or custom exceptions
////            //2. deducat amount from sender
////            deductMoney(transferDTO.getSender(), transferDTO.getAmount());
////            //3. add amount to receiver
////            addMoney(transferDTO.getSender(), transferDTO.getAmount());
////            //4. add transaction record to transfer table to db
////            addTransferRecord(transferDTO);
////        }
////    }
//
//    private boolean isTransferValid() {
//        // all checks
//        //series of checks
//        //not sufficient funds
//        return false;
//    }
////    private void deductMoney(String sender, BigDecimal amount) {
////        // get current balance
////        // subtract amount to current money
////        //update balance
////        transactionDao.update(newBalance);
////    }
////    private void addMoney(String receiver, BigDecimal amount) {
////        // get current balance
////        // add amount to current money
////        //update balance
////        transactionDao.update(newBalance);
////    }
//    private void addTransferRecord(TransferDTO transferDTO) {
//    }
//    private TransferChecks checker;
//
//    public TransferChecks(TransferChecks checker) {
//            this.checker = checker;
//        }
//    }
//
//   // public void makeTransfer(@Valid @RequestBody inputs)
