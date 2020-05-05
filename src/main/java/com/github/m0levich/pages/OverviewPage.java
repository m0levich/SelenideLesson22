package com.github.m0levich.pages;

import com.github.m0levich.blocks.FinancialFreedom;

public class OverviewPage extends BasePage {
    public final FinancialFreedom financialFreedom;

    public OverviewPage() {
        this.financialFreedom = new FinancialFreedom();
    }
}
