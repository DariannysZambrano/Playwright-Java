package com.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

public class SetUpPlaywright implements OptionsFactory {

    @Override
    public Options getOptions() {
        return new Options()
                .setLaunchOptions(new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(1500))

                        
                .setContextOptions(new Browser.NewContextOptions()
                        .setBaseURL("https://practicesoftwaretesting.com/"));
    }
}
