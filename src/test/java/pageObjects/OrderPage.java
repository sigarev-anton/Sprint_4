package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v85.layertree.model.StickyPositionConstraint;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.After;
import org.junit.Test;
import java.time.Duration;
public class OrderPage {
    private WebDriver driver;
    public  OrderPage (WebDriver driver)
    {
        this.driver = driver;
    }

    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //локатор для имени

    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    // локатор для фамилии
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // локатор для адреса
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //локатор для телефона


    public void setName(String name)
    {
        driver.findElement(nameField).sendKeys(name);
    }
    public void setSurname(String surname)
    {
        driver.findElement(surnameField).sendKeys(surname);
    }
    public void setAddress(String address)
    {
        driver.findElement(addressField).sendKeys(address);
    }
    public void setPhone(String phone)
    {
        driver.findElement(phoneField).sendKeys(phone);
    }
    public void fillPersonalInformation(String name, String surname, String address, String phoneNumber)
    {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setPhone(phoneNumber);
        driver.findElement(By.xpath(".//input[@placeholder='* Станция метро']")).click();
        //нажали на выпадашку с выбором станции
        driver.findElement(By.xpath(".//div[@class='select-search__select']/ul/li[@data-value='7']")).click();
        //выбрали седьмое значение из списка
    }
    public void clickNextButton () { //переходим на следующий этап
        driver.findElement(By.className("Button_Middle__1CSJM")).click();
    }


    //следующая страница:
    private By rentDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //локатор для даты
    private By commentForCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //локатор для комментария
    public void setRentDate (String date){
        driver.findElement(rentDate).sendKeys(date);
        driver.findElement(By.xpath(".//div[@tabindex='0' and @role='button']")).click();
        //кликаем на введенную дату
    }
    public void setCommentForCourier (String comment){
        driver.findElement(commentForCourier).sendKeys(comment);
    }
    public void setRentalPeriod (){
        driver.findElement(By.xpath(".//div[@class='Dropdown-control']")).click();
        //нажали на выпадашку со сроком аренды
        int a = 1 + (int) ( Math.random() * 7 );
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div["+a+"]")).click();
        //выбрали случайное число от 1 до 7 и нажали на выпадашку с соответствующим номером
    }
    public void fillRentInformation(String date, String courierComment) {
        setRentDate(date);
        setRentalPeriod();
        driver.findElement(By.xpath(".//input[@id='black']")).click();
        setCommentForCourier(courierComment);
        driver.findElement(By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']")).click();
        driver.findElement(By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']")).click();
        Duration duration =  Duration.ofSeconds(5);
        new WebDriverWait(driver, duration).until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath(".//div[@class='Order_Text__2broi']")), "Номер заказа"));

    }
    public String resultMessage()
    {
        String checkFinalText = driver.findElement(By.xpath(".//div[@class='Order_Text__2broi']")).getText();
        return checkFinalText;
    }

}
