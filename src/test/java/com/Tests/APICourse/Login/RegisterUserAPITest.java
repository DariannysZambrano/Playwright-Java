package com.Tests.APICourse.Login;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Tests.APICourse.Domain.User;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.RequestOptions;

@UsePlaywright
public class RegisterUserAPITest {

    private APIRequestContext request;
@BeforeEach
    void setup(Playwright playwright) {
        request = playwright.request().newContext(
            new APIRequest.NewContextOptions()
                .setBaseURL("https://api.practicesoftwaretesting.com")
        );
    }

    @AfterEach
    void tearDown() {
        if (request != null) {
            request.dispose();
        }
    }

@Test
    void should_register_user() {
        User validUser = User.generateRandomUser();

        var response = request.post("/users/register",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(validUser)
        );

        assertThat(response.status()).isEqualTo(201);
    }


}

