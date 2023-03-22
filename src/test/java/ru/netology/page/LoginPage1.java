package ru.netology.page;
import com.codeborne.selenide.Condition;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage1 {
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }
//    public LoginPage1 invalidLogin(DataHelper.AuthInfo info) {
//        fillLogin(info);
//        $(".notification").shouldBe(Condition.visible);
//        return this;
//    }
//    private void fillLogin(DataHelper.AuthInfo info) {
//        $("[data-test-id=login] input").setValue(info.getLogin());
//        $("[data-test-id=password] input").setValue(info.getPassword());
//        $("[data-test-id=action-login]").click();
//    }
}
