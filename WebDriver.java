import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WebDriver {
    public static void main(String[] args){
        WebDriver webDriver = new ChromeDriver();
        try{
            new SelniumProgram(webDriver).run();
        }catch (Exception e){

        }
        webDriver.quit();
    }

    private static class SelniumProgram{
        private final WebDriver webDriver;
        public SelniumProgram(WebDriver webDriver){
            this.webDriver = webDriver;
        }

        public void run() throws Exception{
            webDriver.get("https://baidu.com/");
            Thread.sleep(3000);
            click(findElement(By.xpath("xpath to search bar"),0));
            Thread.sleep(3000);
            WebElement upload = webDriver.findElement(By.cssSelector("input[type='file']"));
            click(upload);
            Thread.sleep(3000);
            setText(upload,"C:\\temp\\test.jpg");
            Thread.sleep(3000);
            //验证结果是否正确
            String result = webDriver.findElement(By.className("icon")).getCssValue("display");
            Assert.assertEquals("real value",result);


            //save
            File src = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src,new File("C:\\tmp\\result.jpg"));


        }

        private WebElement findElement(By by,int timeout){
            if(timeout > 0){
                return new WebDriverWait(webDriver, Duration.ofSeconds(timeout)).until(driver -> driver.findElement(by));
            }
            return webDriver.findElement(by);
        }

        private void click(WebElement element){
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",element);

        }
        private void setText(WebElement element,String text){
            element.sendKeys(text);
        }
    }
}
