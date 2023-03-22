package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage1;
import ru.netology.page.LoginPage2;
import ru.netology.page.LoginPage3;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.getFirstCardDetails;
import static ru.netology.data.DataHelper.getSecondCardDetails;

class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var firstCardDetails = getFirstCardDetails();
        var secondCardDetails = getSecondCardDetails();

        int amount = 1500;
        var expectedBalanceFirstCard = DashboardPage.getCardBalance(firstCardDetails) - amount;
        var expectedBalanceSecondCard = DashboardPage.getCardBalance(secondCardDetails) + amount;

        var transferPage = DashboardPage.selectCardToTransfer(secondCardDetails);
        DashboardPage dashboardPage = transferPage.madeTransfer(Integer.parseInt(String.valueOf(amount)), firstCardDetails);
        var actualBalanceFirstCard = DashboardPage.getCardBalance(firstCardDetails);
        var actualBalanceSecondCard = DashboardPage.getCardBalance(secondCardDetails);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
    @Test
    void shouldTransferFromSecondToFirst() {
        open("http://localhost:9999");
        var loginPage = new LoginPage1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var firstCardDetails = getFirstCardDetails();
        var secondCardDetails = getSecondCardDetails();

        int amount = 100;
        var expectedBalanceFirstCard = DashboardPage.getCardBalance(firstCardDetails) + amount;
        var expectedBalanceSecondCard = DashboardPage.getCardBalance(secondCardDetails) - amount;
        var transferPage = DashboardPage.selectCardToTransfer(firstCardDetails);
        DashboardPage dashboardPage = transferPage.madeTransfer(Integer.parseInt(String.valueOf(amount)), secondCardDetails);
        var actualBalanceFirstCard = DashboardPage.getCardBalance(firstCardDetails);
        var actualBalanceSecondCard = DashboardPage.getCardBalance(secondCardDetails);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void shouldTransferFromSecondToFirstMoreThanBalance() {
        open("http://localhost:9999");
        var loginPage = new LoginPage1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var firstCardDetails = getFirstCardDetails();
        var secondCardDetails = getSecondCardDetails();

        int amount = 20_000;
        var expectedBalanceFirstCard = DashboardPage.getCardBalance(firstCardDetails) + amount;
        var expectedBalanceSecondCard = DashboardPage.getCardBalance(secondCardDetails) - amount;
        var transferPage = DashboardPage.selectCardToTransfer(firstCardDetails);
        DashboardPage dashboardPage = transferPage.madeTransfer(Integer.parseInt(String.valueOf(amount)), secondCardDetails);
        var actualBalanceFirstCard = DashboardPage.getCardBalance(firstCardDetails);
        var actualBalanceSecondCard = DashboardPage.getCardBalance(secondCardDetails);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
    // пожалуйста, не ругайтесь на неудаленный код. он мне нужен для понимания
    //    @Test
//    void shouldTransferMoneyBetweenOwnCardsV2() {
//        open("http://localhost:9999");
//        var loginPage = new LoginPage2();
//        var authInfo = DataHelper.getAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//        verificationPage.validVerify(verificationCode);
//    }
//    @Test
//    void shouldTransferMoneyBetweenOwnCardsV3() {
//        var loginPage = open("http://localhost:9999", LoginPage3.class);
//        var authInfo = DataHelper.getAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//        verificationPage.validVerify(verificationCode);
//    }
    @AfterEach
    void tearDown() {
    }
}