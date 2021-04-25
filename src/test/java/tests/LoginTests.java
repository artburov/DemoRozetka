package tests;

import config.EmailCredConfig;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@DisplayName("Rozetka login tests")
public class LoginTests extends TestBase {
    EmailCredConfig emailCredentials = ConfigFactory.create(EmailCredConfig.class);

    @BeforeEach
    void openResource() {
        step("Open login page", () -> {
            open("https://rozetka.com.ua");
            $("rz-user[class='header-actions__component']").click();
        });
    }

    @Test
    @Story("Authorization without any data")
    @Tag("login")
    void emptyCredentials() {
        step("Fill in nothing in login and password", () -> {
            $("#auth_email").setValue("");
            $("#auth_pass").setValue("");
            $(".button.auth-modal__submit").click();
            $(".error-message.ng-star-inserted").shouldHave(text("Введен неверный адрес эл.почты или номер телефона"));
            $(".ng-pristine.ng-invalid").shouldBe(visible);
        });
    }

    @Test
    @Story("Authorization using only username in login")
    @Tag("login")
    void loginTest() {
        step("Fill in login using username", () -> {
            $("#auth_email").setValue(emailCredentials.userLogin());
            $(".button.auth-modal__submit").click();
            $(".error-message.ng-star-inserted").shouldHave(text("Введен неверный адрес эл.почты или номер телефона"));
        });
    }

    @Test
    @Story("Authorization using only email in login")
    @Tag("login")
    void loginTestEmail() {
        step("Fill in login using username", () -> {
            $("#auth_email").setValue(emailCredentials.emailLogin());
            $(".button.auth-modal__submit").click();
            $(".ng-pristine.ng-invalid").shouldBe(visible);
        });
    }


    @Test
    @Story("Authorization using only phone in login")
    @Tag("login")
    void loginTestPhone() {
        step("Fill in login using phone-number", () -> {
            $("#auth_email").setValue(emailCredentials.numberLogin());
            $(".button.auth-modal__submit").click();
            $(".ng-pristine.ng-invalid").shouldBe(visible);
        });
    }

    @Test
    @Story("Verify remind password feature")
    @Tag("recovery")
    void loginRemindPassword() {
        step("Click on 'Напомнить пароль' to verify remind password feature", () -> {
            $(".link-dotted.auth-modal__restore-link").shouldHave(text("Напомнить пароль")).click();
            $(".button.auth-modal__get-pass").shouldHave(text("Получить временный пароль"));
        });
    }
}