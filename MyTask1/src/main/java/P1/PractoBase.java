package P1;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.edge.EdgeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class PractoBase {
    protected static EdgeDriver driver;
    protected  static ExtentSparkReporter htmlReporter;
    protected  static ExtentReports report;
    protected  static ExtentTest test;
    public void report() {
    	 htmlReporter = new ExtentSparkReporter(new File("C:\\Users\\SunithaJ\\Desktop\\MavenReport"));
         htmlReporter.config().setReportName("Practo Report");
         htmlReporter.config().setDocumentTitle("PractoTask Automation Report");
         htmlReporter.config().setTheme(Theme.STANDARD);
         report = new ExtentReports();
         report.setSystemInfo("Environment", "QA");
         report.setSystemInfo("Tester", "Sunitha");
         report.attachReporter(htmlReporter);
    	
    }
    public void setUp() {    	
   	
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        System.out.println("System setup DONE");
    }
    public void openUrl() throws IOException, InterruptedException {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream("src/main/java/Config/Config.properties");
        prop.load(input);
        String url = prop.getProperty("url");        
        driver.get(url);
    }
    public void closeUrl() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
    public void tearDown() {
        
        if (report != null) {
            report.flush(); 
            System.out.println("Report generated");
        }
    }    
}
