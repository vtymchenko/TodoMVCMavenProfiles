package com.herokuapp.todomvc4tasj.features;

import com.codeborne.selenide.Configuration;

/**
 * Created by Valentyn on 12.04.2016.
 */
public class BaseTest {

    {
        Configuration.browser = System.getProperty("driver.browser");

    }
}
