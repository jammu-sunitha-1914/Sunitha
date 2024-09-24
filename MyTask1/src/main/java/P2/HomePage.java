package P2;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import P1.PractoBase;
public class HomePage extends PractoBase {
    By locationInput = By.xpath("//*[@id=\"c-omni-container\"]/div/div[1]/div[1]/input");
    By intoIcon = By.xpath("//*[@id=\"c-omni-container\"]/div/div[1]/div[1]/span[2]/span/i");
    By hyderabdLocation = By.xpath("//*[@id=\"c-omni-container\"]/div/div[1]/div[2]/div[2]/div");    
    By cityList = By.xpath("//*[@id=\"c-omni-container\"]/div/div[1]/div[2]/div[2]/div");
    By doctorInput = By.xpath(" //*[@id=\"c-omni-container\"]/div/div[2]/div[1]/input");
    By doctors = By.xpath("//*[@id=\"c-omni-container\"]/div/div[2]/div[2]/div/div");
    By gynacologist = By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div");
    By profession = By.xpath("//div[@class=\"u-d-flex\"]/span");
    By filter = By.xpath("//*[@id=\"container\"]/div/div[3]/div/div/header/div[1]/div/div[4]/i");
    By fee = By.xpath("//*[@id=\"container\"]/div/div[3]/div/div/header/div[2]/div/div[1]/div/label[3]/div");     
    public boolean findDoctors() throws InterruptedException {
        try {
            driver.findElement(locationInput).click();
            Thread.sleep(3000);
            driver.findElement(intoIcon).click();
            Thread.sleep(3000);
            driver.findElement(locationInput).sendKeys("hyedrabad");
            Thread.sleep(3000);
            List<WebElement> hyderabdLocationList = driver.findElements(hyderabdLocation);
            Thread.sleep(3000);
            hyderabdLocationList.get(1).click();
            Thread.sleep(3000);
            driver.findElement(doctorInput).click();
            Thread.sleep(1000);
            List<WebElement> doctorsList = driver.findElements(doctors);           
            Thread.sleep(1000);
            doctorsList.get(1).click();
            Thread.sleep(3000);
            String firstDoctorProfession = driver.findElement(profession).getText();  // Get the first doctor's profession
            List<WebElement> gynacologistList = driver.findElements(gynacologist);
            boolean allSameProfession = true;
            for (int i = 1; i < gynacologistList.size(); i++) {
                String currentDoctorProfession = driver.findElement(profession).getText();                
                if (!currentDoctorProfession.equals(firstDoctorProfession)) {
                    allSameProfession = false;
                    System.out.println("Doctor " + (i + 1) + " has a different profession: " + currentDoctorProfession);
                    break; 
                }
            }
            if (allSameProfession) {
                System.out.println("All doctors listed belong to the same profession: " + firstDoctorProfession);
                return true;
            } else {
                System.out.println("Not all doctors belong to the same profession.");
                return false;
            }
        }  catch (Exception e) {
            System.out.println("An error occurred while finding doctors: " + e.getMessage());
            return false;
        }
    }      
    public boolean filterfees() throws InterruptedException {
        try {
            driver.findElement(filter).click();
            Thread.sleep(2000); 
            driver.findElement(fee).click();
            Thread.sleep(2000); 
            List<WebElement> doctorsList = driver.findElements(By.xpath("//div[@class='doctor-list']//div[@class='doctor-fee']")); // Adjust XPath as needed
            boolean allFeesAbove1000 = true; 
            for (WebElement doctor : doctorsList) {
                String feeText = doctor.getText().replace("₹", "").replace(",", "").trim(); // Remove currency symbol and commas
                int consultationFee = Integer.parseInt(feeText);
                if (consultationFee <= 1000) {
                    allFeesAbove1000 = false; // If any fee is less than or equal to ₹1000, set flag to false
                    System.out.println("Doctor has a fee of ₹" + consultationFee + ", which is not above ₹1000.");
                    break;
                }
            }
            if (allFeesAbove1000) {
                System.out.println("All doctors listed have consultation fees above ₹1000.");
                return true;
            } else {
                System.out.println("Not all doctors have consultation fees above ₹1000.");
                return false;
            }

        } catch (Exception e) {
            System.out.println("An error occurred while filtering fees: " + e.getMessage());
            return false; 
        }
    }
  
}
