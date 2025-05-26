package group20.example.demo.controller;

import org.springframework.stereotype.Controller;

import group20.example.demo.entity.ATM;
import group20.example.demo.service.ATMService;

@Controller
public class ATMController {

    private ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    /**
     * Get ATM details (fixed ATM ID = 1)
     */
    public ATM getATM() {
        return atmService.findByAtmId();
    }

    /**
     * Update the ATM amount by adding the given amount
     */
    public void updateAmount(double amount) {
        atmService.updateAmount(amount);
    }

    /**
     * Check if ATM has enough balance for the given money amount
     */
    public boolean checkAmount(double money) {
        return atmService.checkAmountATM(money);
    }
}
