package com.github.m0levich.pages;

import com.github.m0levich.blocks.FinancialFreedom;

public class OverviewPage extends BasePage {
    private final FinancialFreedom financialFreedom;

    public OverviewPage() {
        this.financialFreedom = new FinancialFreedom();
    }

    public FinancialFreedom getFinancialFreedom() {
        return financialFreedom;
    }
}
