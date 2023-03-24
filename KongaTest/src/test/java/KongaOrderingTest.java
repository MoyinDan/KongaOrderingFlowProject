import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class KongaOrderingTest {

    //import the Konga webDriver
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //locate where the chromedriver is
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        //1.Open your Chrome browser
        driver = new ChromeDriver(options);
    }

    @Test(priority = 0)
    public void userSuccessfullyEnteredKongaPage() throws InterruptedException {
        //Visit the Konga URL
        driver.get("https://www.konga.com/");
        // Test1. Verify that the user input the right url and it on the right page
        if (driver.getCurrentUrl().contains("https://www.konga.com/"))
            //pass
            System.out.println("Correct Konga Webpage URL");
        else
            //fail
            System.out.println("Wrong Konga Webpage URL");
        Thread.sleep(5000);
        //Maximize the browser
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void userSuccessfullyLogin() throws InterruptedException {
        //Click on login button to open the login page
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
        //Input your username on the username field
        driver.findElement(By.id("username")).sendKeys("");
        //Input your password on the password field
        driver.findElement(By.id("password")).sendKeys("");
        //Click on the login button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        //Test2. Verify that when user click on the login button he/she is directed to the login page.
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.konga.com";
        if (actualUrl.contains(expectedUrl))
            //pass
            System.out.println("Correct login Url");
        else
            //faIl
            System.out.println("Wrong login Url");
        Thread.sleep(10000);
    }

    @Test(priority = 2)
    public void computerAndAccessoriesIsPresent() throws InterruptedException {
        //From the Categories, click on the “Computers and Accessories”
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();
        //Test3. Verify that "computers and accessories" is present on the category list
        String actualPageUrl = driver.getCurrentUrl();
        String expectedPageUrl = "https://www.konga.com/";
        if (actualPageUrl.equals(expectedPageUrl))
            //pass
            System.out.println("Correct Page Url");
        else
            //fail
            System.out.println("Wrong Page Url");
        Thread.sleep(7000);
        //3.Click on the Laptop SubCategory
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/label/span")).click();
        Thread.sleep(7000);
    }

    @Test(priority = 3)
    public void userSortItemPriceLowToHigh() throws InterruptedException {
        //Click on the Apple MacBooks
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li/a/ul/li[1]/a/label/span")).click();
        //Test 4 verify that user can sort item by price low-high
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[1]/div[1]/div/div[2]/section/ul/li[3]/a")).click();
        Thread.sleep(7000);
    }

    @Test(priority = 4)
    public void userSuccessfullyAddToCart() throws InterruptedException {
        //Get current cart size
        int cartSizeBeforeItemIsAdded = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]/span[2]")).getText());
        //Add an item to the cart
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[1]/div/div/div[2]/form/div[3]/button")).click();
        Thread.sleep(7000);
        //Get Cart size after adding an item to cart
        int cartSizeAfterItemIsAdded = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]/span[2]")).getText());
        //Test5. Verify that on adding new product to the cart the count increases
        if (cartSizeAfterItemIsAdded > cartSizeBeforeItemIsAdded)
            //pass
            System.out.println("New Item (s) added to cart");
        else
            //fail
            System.out.println("No Item (s) added to cart");
    }

    @Test(priority = 5)
    public void userSuccessfullyCheckOut() throws InterruptedException {
        //Click on the My Cart Menu
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]")).click();
        Thread.sleep(7000);
        //Test6 Verify that user successfully checkout
        //click on checkout
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();
        Thread.sleep(7000);
        if (driver.getCurrentUrl().equals("https://www.konga.com/checkout/complete-order"))
            //pass
            System.out.println("User successfully Checked Out");
        else
            //fail
            System.out.println("User not successfully Checked Out");

    }

    @Test(priority = 6)
    public void userSuccessfullyEnterPayOutPage() throws InterruptedException {
        //Click "Pay Now" to enable “Continue to Payment” button - the user must have preselected pick up method
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
        Thread.sleep(5000);
        //Test 7 Verify that user successfully enter payout page
        //click on continue to payment button
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
        Thread.sleep(8000);
        if (driver.getCurrentUrl().equals("https://www.konga.com/checkout/complete-order"))
            //pass
            System.out.println("User successfully enter payout page");
        else
            //fail
            System.out.println("User not successfully enter payout page");
    }

    @Test(priority = 7)
    public void userInputCardDetails() throws InterruptedException {
        //Select the “CARD” payment method
        WebElement checkOutFrame = driver.findElement(By.xpath("//*[@id='kpg-frame-component']"));
        driver.switchTo().frame(checkOutFrame);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"channel-template\"]/div[2]/div/div[2]/button")).click();
        Thread.sleep(3000);
        //Input a wrong card number
        driver.findElement(By.id("card-number")).sendKeys("567398720003");
        //input a wrong date
        driver.findElement(By.id("expiry")).sendKeys("07/04");
        //input a wrong CVV
        driver.findElement(By.id("cvv")).sendKeys("234");
        //Click on the Field for entering card pin
        driver.findElement(By.id("card-pin-new")).sendKeys("2341665478903345");
        //Enter pin randomly
        driver.findElement(By.xpath("//*[@id=\"keypads\"]/button[1]")).click();
        //click second pin
        driver.findElement(By.xpath("//*[@id=\"keypads\"]/button[1]")).click();
        //click third pin
        driver.findElement(By.xpath("//*[@id=\"keypads\"]/button[3]")).click();
        //Click fourth pin
        driver.findElement(By.xpath("//*[@id=\"keypads\"]/button[3]")).click();
    }

    @Test(priority = 8)
    public void userClickPayNow() {
        //click on pay now
        driver.findElement(By.id("validateCardForm")).click();
    }

    @Test(priority = 9)
    public void userPaymentUnsuccessfulWithWrongCardDetail() {
        //Test8 User cannot make successful payment with wrong card details and cannot move to next page
        //get current url after user click on Pay now
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://www.konga.com/checkout/complete-order"))
            System.out.println("Unsuccessful transaction");
        else
            System.out.println("Successful transaction");
        //Print out the error message for the card number field.
        System.out.println(driver.findElement(By.xpath("//*[@id='card-number_unhappy']")).getText());
    }

    @Test(priority = 10)
    public void printOutInvalidCardNumber() {
        //Print out the error message for the card number field.
        String invalidCardErrorMessage = driver.findElement(By.xpath("//*[@id='card-number_unhappy']")).getText();
        //Test9 Card error message is correctly captured and printed on console
        if (invalidCardErrorMessage.equals("Invalid card number"))
            System.out.println("Card error message successfully captured");
        else
            System.out.println("Card error message not successfully captured");
    }


    @Test(priority = 11)
    public void cardIframeClosedSuccessfully() {
        //Close the iFrame that displays the input card Modal
        driver.findElement(By.xpath("/html/body/section/section/section/div[2]/div[1]/aside")).click();
    }

    @AfterTest
    public void quitBrowser() {
        //Quit the browser.
        driver.quit();
    }

}
