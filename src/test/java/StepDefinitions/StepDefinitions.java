package StepDefinitions;


import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepDefinitions {

    static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @BeforeAll

    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("User is on the login page {string}")
    public void user_is_on_the_login_page(String url) {
        driver.navigate().to(url);
        System.out.println("User is on the login page");
    }
    @When("User logs in as Admin by using login {string} and password {string}")
    public void userLogsInAsAdminByUsingLoginAndPassword(String login, String password) {
        driver.findElement(By.id("txtUsername")).sendKeys(login);
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("#divLoginButton input.button")).click();
    }

    @And("User chooses Configuration option from the Admin menu")
    public void user_chooses_configuration_option_from_the_admin_menu() {
        driver.findElement(By.xpath("//*[@id=\"menu_admin_viewAdminModule\"]/b")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"menu_admin_Configuration\"]")));

        WebElement menu = driver.findElement(By.xpath("//*[@id=\"menu_admin_Configuration\"]"));

        if (menu.isEnabled() && menu.isDisplayed()) {
            System.out.println("Menu is enabled and visible");
        } else {
            System.out.println("Menu is not visible");
        }

        menu.click();

    }
    @And("User chooses Location option from the Configuration menu")
    public void user_chooses_location_option_from_the_configuration_menu() {
        WebElement localization = driver.findElement(By.id("menu_admin_localization"));
        localization.click();

    }
    @And("User clicks on the Edit button")
    public void user_clicks_on_the_edit_button() {
        driver.findElement(By.id("btnSave")).click();

    }
    @And("User chooses German language from the dropdown list")
    public void user_chooses_german_language_from_the_dropdown_list() {

        WebElement dropdown = driver.findElement(By.id("localization_dafault_language"));
        dropdown.click();

        if (dropdown.isEnabled() && dropdown.isDisplayed()) {
            System.out.println("Dropdown list is enabled and visible");
        } else {
            System.out.println("Dropdown list is not visible");
        }

        Select select = new Select(dropdown);

        int dropdownListSize = select.getOptions().size();
        System.out.println("Dropdown list size " + dropdownListSize);

        select.selectByVisibleText("German - Deutsch");
        String getText = select.getFirstSelectedOption().getText();
        System.out.println("Option choosen " + getText);

    }
    @And("User click on the Save button")
    public void user_click_on_the_save_button() {
        driver.findElement(By.id("btnSave")).click();

    }
    @Then("Website language changes into German")
    public void website_language_changes_into_german() {
        List<String> pagesTitle1 = new ArrayList<String>(Arrays.asList("Admin", "Personendatenmanagement", "Abwesenheiten", "Zeit", "Bewerbermanagement", "Persönliche Informationen", "Leistung", "Cockpit", "Verzeichnis", "Wartung", "Buzz"));
        List<String> pagesTitle2 = new ArrayList<String>();
        List<WebElement> firstLevelMenuOptions = driver.findElements(By.className("firstLevelMenu"));
        int pages = firstLevelMenuOptions.size();
        System.out.println("Count pages " + pages);
        for (WebElement webElement : firstLevelMenuOptions) {
            pagesTitle2.add(webElement.getText());
        }
        Assert.assertEquals(pagesTitle1, pagesTitle2);

    }
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }



}
