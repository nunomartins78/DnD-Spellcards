package SpellCards.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;


@RestController
public class Scraper {
    public static void main(String[] args) {
        getSpells();
    }
    public static void getSpells() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64\\chromedriver.exe");

        // Initialize ChromeDriver
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
        chromeOptions.addArguments("--disable-extensions"); // disabling extensions
        chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
        chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
        WebDriver driver = new ChromeDriver(chromeOptions);
        // Create a new WebDriver instance for the additional URL
        WebDriver additionalDriver = new ChromeDriver(chromeOptions);
//        Flower flowerInfo = new Flower();

        try {
            String url = "http://dnd5e.wikidot.com/spells";
            driver.get(url);

            // Find all tabs (div elements with ids wiki-tab-0-0 to wiki-tab-0-9)
            List<WebElement> tabs = driver.findElements(By.xpath("//div[starts-with(@id, 'wiki-tab-0-')]"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            int spellLevel = 0;

            // Iterate through each tab
            for (int tabIndex = 0; tabIndex < tabs.size(); tabIndex++) {
                ((JavascriptExecutor) driver).executeScript(
                        "document.getElementById('wiki-tab-0-" + tabIndex + "').style.display = 'block';"
                );

                List<WebElement> spellRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//div[starts-with(@id, 'wiki-tab-0-" + tabIndex + "')]/descendant::tr")
                ));

                // Iterate through each tr element within the current tab
                for (WebElement spellRow : spellRows) {
                    List<WebElement> spellData = spellRow.findElements(By.tagName("td"));
                    if (!spellData.isEmpty()) {
                        String spellName = spellData.get(0).getText();
                        String school = spellData.get(1).getText();
                        String castingTime = spellData.get(2).getText();
                        String range = spellData.get(3).getText();
                        String duration = spellData.get(4).getText();
                        String components = spellData.get(5).getText();

                        System.out.println("Spell Name: " + spellName);
                        System.out.println("Spell Level: " + spellLevel);
                        System.out.println("School: " + school);
                        System.out.println("Casting Time: " + castingTime);
                        System.out.println("Range: " + range);
                        System.out.println("Duration: " + duration);
                        System.out.println("Components: " + components);

                        spellName = spellName.replaceAll("\\([^\\)]*\\)", "")
                                .replaceAll(" ", "-")
                                .replaceAll("/", "-")
                                .replaceAll("'", "");

                        String additionalUrl = "http://dnd5e.wikidot.com/spell:" + spellName;

                        try {
                            System.out.println("Navigating to: " + additionalUrl);
                            additionalDriver.get(additionalUrl);

                            // Use JavascriptExecutor to execute the JavaScript code
                            JavascriptExecutor jsExecutor = (JavascriptExecutor) additionalDriver;

                            // Execute the JavaScript to get the desired element
                            WebElement additionalInfoElement = (WebElement) jsExecutor.executeScript(
                                    "return document.querySelector('#page-content > p:nth-child(5)');");

                            // Now you can use additionalInfoElement as needed
                            if (additionalInfoElement != null) {
                                String additionalInfo = additionalInfoElement.getText();
                                System.out.println("Additional Information: " + additionalInfo);
                            } else {
                                System.out.println("NULL NULL NULL NULL NULL");
                            }

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("-".repeat(50));

                    } else {
                        System.out.println("No spell data found for the current row");
                    }
                }
                spellLevel++;
            }
        } finally {
            driver.quit();
            additionalDriver.quit();
        }
//        return flowerInfo;
    }
}
