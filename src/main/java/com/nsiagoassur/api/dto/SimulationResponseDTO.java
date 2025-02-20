package com.nsiagoassur.api.dto;

import java.math.BigDecimal;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
public class SimulationResponseDTO {
    private String quoteReference;
    private String endDate;
    private double price;

    public SimulationResponseDTO(String quoteReference, String endDate, double price) {
        this.quoteReference = quoteReference;
        this.endDate = endDate;
        this.price = price;
    }

    public String getQuoteReference() {
        return quoteReference;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getPrice() {
        return price;
    }
}

