package com.github.m0levich.pages;

import com.github.m0levich.blocks.NavigationMenu;

public abstract class BasePage {
    public final NavigationMenu navigationMenu;

    public BasePage() {
        this.navigationMenu = new NavigationMenu();
    }
}
