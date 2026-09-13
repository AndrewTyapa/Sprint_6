package practicum;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


@RunWith(Parameterized.class)

public class OrderPageTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String rent;
    private final String color;
    private final String comment;
    private final String mainTestPageUrl = "https://qa-scooter.praktikum-services.ru";

    public OrderPageTest (String name, String surname, String address, String metro,
                          String phone, String date, String rent, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rent = rent;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] dataForOrder() {
        return new Object[][] {
                {"Иван", "Иванов", "Москва, б-р Яна Райниса, д. 15", "Сокольники", "89033333333", "13.09.2026", "двое суток", "чёрный жемчуг", "Доставить до обеда"},
                {"Ольга", "Петрова", "Москва, ул. Вилиса Лациса, дом 33", "Планерная", "89999999999", "12.09.2026", "трое суток", "серая безысходность", "Оплата картой"},
        };
    }

    @Before
    public void begin() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage", "--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.get(mainTestPageUrl);
    }

    // Тест: позитивный сценарий заказа при клике на кнопку "Заказать" в хэддере страницы. Оба теста FAILED т.к не работет кнопка "ДА"
    // Логика: заполняем все поля тестовыми данными. Ждем статуса "Заказ оформлен"
    @Test
    public void orderAfterClickOnHeaderOrderButton () {
        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver);

        objMainPage.waitForLoad();
        objMainPage.clickOnAcceptCookieButton();
        objMainPage.clickOnHeaderOrderButton();
        objOrderPage.waitForLoad();
        objOrderPage.setInputName(name);
        objOrderPage.setInputSurname(surname);
        objOrderPage.setInputAddress(address);
        objOrderPage.setInputMetro(metro);
        objOrderPage.setInputPhone(phone);
        objOrderPage.clickOnNextButton();
        objOrderPage.waitForLoad();
        objOrderPage.setInputDate(date);
        objOrderPage.setRentPeriod(rent);
        objOrderPage.setScooterColor(color);
        objOrderPage.setInputComment(comment);
        objOrderPage.clickOnOrderButton();
        objOrderPage.clickOnYesButton();
        objOrderPage.getOrderPlacedText();

        MatcherAssert.assertThat( "Не удалось создать новый заказ",
                objOrderPage.getOrderPlacedText());
    }

    // Тест: позитивный сценарий заказа при клике на кнопку "Заказать" в середине страницы. Оба теста FAILED т.к не работет кнопка "ДА"
    // Логика: заполняем все поля тестовыми данными. Ждем статуса "Заказ оформлен"
    @Test
    public void orderAfterClickOnMiddleOrderButton () {
        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver);

        objMainPage.waitForLoad();
        objMainPage.clickOnAcceptCookieButton();
        objMainPage.clickOnMiddleOrderButton();
        objOrderPage.waitForLoad();
        objOrderPage.setInputName(name);
        objOrderPage.setInputSurname(surname);
        objOrderPage.setInputAddress(address);
        objOrderPage.setInputMetro(metro);
        objOrderPage.setInputPhone(phone);
        objOrderPage.clickOnNextButton();
        objOrderPage.waitForLoad();
        objOrderPage.setInputDate(date);
        objOrderPage.setRentPeriod(rent);
        objOrderPage.setScooterColor(color);
        objOrderPage.setInputComment(comment);
        objOrderPage.clickOnOrderButton();
        objOrderPage.clickOnYesButton();
        objOrderPage.getOrderPlacedText();

        MatcherAssert.assertThat("Не удалось создать новый заказ",
                objOrderPage.getOrderPlacedText()
        );
    }

    @After
    public void teardown() {
        driver.quit();
    }
}