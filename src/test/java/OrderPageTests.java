import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.After;



@RunWith(Parameterized.class)
public class OrderPageTests {
    String name;
    String surname;
    String address;
    String phoneNumber;
    String comment;
    String date;

    public OrderPageTests(String name, String surname, String address, String phoneNumber, String comment, String date) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.comment = comment;


    }

    @Parameterized.Parameters
    public static Object[][] getPersonInformation() {
        return new Object[][]{
                {"Котик", "Котиков", "Городской округ Котово", "79829999999", "21.01.2024", "я сам не могу, у меня лапки"},
                {"Собака", "Псовна", "Городской округ Чертаново", "79826667788", "21.06.2026", "вуф вуф"},

        };
    }



    WebDriver driver = new FirefoxDriver();


    @Test
    public void checkOrderPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/order");
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.fillPersonalInformation(name, surname, address, phoneNumber);
        objOrderPage.clickNextButton();
        objOrderPage.fillRentInformation(date, comment);
        assertThat(objOrderPage.resultMessage(), containsString("Номер заказа"));

    }
    @After
    public void teardown() {
        driver.quit();
    }
}


