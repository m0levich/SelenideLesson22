package com.github.m0levich;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.m0levich.pages.LoginPage;
import com.github.m0levich.pages.OverviewPage;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BankSpbTest {
    private static final Logger LOG = LoggerFactory.getLogger(BankSpbTest.class);

    @BeforeMethod(description = "Переход на страницу интернет-банка")
    public void initDriver () {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        open("https://idemo.bspb.ru");
    }

    @Owner("Molkov Egor")
    @Epic("Страница Обзор")
    @Story("Я как клиент банка хочу просматривать блок Финансовая свобода")
    @TmsLink("1234")
    @Issue("issue-21")
    @Test(description = "Тест на проверку отображения блока Финансовая свобода")
    @Severity(SeverityLevel.CRITICAL)
    public void myAssetsTest(){

        LoginPage loginPage = new LoginPage();
        ReadingFromFile readingFromFile = new ReadingFromFile();
        loginPage.checkLanguage();
        title().contains("Интернет банк - Банк Санкт-Петербург");
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
        checkFinancialFreedomOnMatcher(overviewPage.getFinancialFreedom().getFinancialFreedomBlock(), overviewPage.getFinancialFreedom().getMatcher());
        Actions act = new Actions(getWebDriver());
        moveToBlock(overviewPage, act);
        checkMyAssetsVisible(overviewPage);
        checkMyAssetsOnMather(overviewPage);
    }

    @Step("Перемещение курсора на блок")
    private void moveToBlock(OverviewPage overviewPage, Actions act) {
        act.moveToElement(overviewPage.getFinancialFreedom().getFinancialFreedomBlock()).perform();
    }

    @Step("Проверка на матчер Мои средства")
    private void checkMyAssetsOnMather(OverviewPage overviewPage) {
        String myAssetsMatcher = "Моих средств " + overviewPage.getFinancialFreedom().getMatcher();
        overviewPage.getFinancialFreedom().getMyAssets().should(matchText(myAssetsMatcher));
    }

    @Step("Проверка на видимость элемента")
    private void checkMyAssetsVisible(OverviewPage overviewPage) {
        overviewPage.getFinancialFreedom().getMyAssets().shouldBe(visible);
    }

    @Step("Проверка на матчер суммы в блоке Финансовая свобода")
    private void checkFinancialFreedomOnMatcher(SelenideElement financialFreedomBlock, String matcher) {
        financialFreedomBlock.should(matchText(matcher));
    }
}
