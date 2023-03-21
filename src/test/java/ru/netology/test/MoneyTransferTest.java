package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage1;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {

    @BeforeEach
    void setUp() {
    }
@Test
void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }
    @AfterEach
    void tearDown() {
    }
}