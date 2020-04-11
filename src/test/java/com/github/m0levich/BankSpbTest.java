package com.github.m0levich;

import com.github.m0levich.pages.LoginPage;
import com.github.m0levich.pages.OverviewPage;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BankSpbTest {
    private static final Logger LOG = LoggerFactory.getLogger(BankSpbTest.class);

    @BeforeMethod
    public void initDriver () {
        open("https://idemo.bspb.ru");
    }

    @Test
    public void myAssetsTest(){

        LoginPage loginPage = new LoginPage();
        ReadingFromFile readingFromFile = new ReadingFromFile();
        loginPage.checkLanguage();
        try {
            loginPage
                    .login(readingFromFile.getLogin(), readingFromFile.getPassword())
                    .twoFactorsAuth(readingFromFile.getSmsPin())
                    .getNavigationMenu()
                    .selectMenu("Обзор");
        } catch (FileNotFoundException e) {
            LOG.info("Файл не найден в корне проекта");
            e.printStackTrace();
        }
        OverviewPage overviewPage = new OverviewPage();
        overviewPage.getFinancialFreedom().getFinancialFreedomBlock().should(matchText(overviewPage.getFinancialFreedom().getMatcher()));
        Actions act = new Actions(getWebDriver());
        act.moveToElement(overviewPage.getFinancialFreedom().getFinancialFreedomBlock()).perform();
        overviewPage.getFinancialFreedom().getMyAssets().shouldBe(visible);
        String myAssetsMatcher = "Моих средств " + overviewPage.getFinancialFreedom().getMatcher();
        overviewPage.getFinancialFreedom().getMyAssets().should(matchText(myAssetsMatcher));
    }
}
