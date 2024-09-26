package P2;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class BookSlotPage extends HomePage {
    By bookButton = By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[6]/div[1]/div/div[2]/div/div/div[2]/div/button");
    By slots = By.xpath("//div[@data-qa-id='date_selector']");
    By beforeName = By.xpath("//*[@id='container']/div/div[4]/div/div[1]/div/div[6]/div[1]/div/div[1]/div[2]/a/div/h2");
    By afterName = By.xpath("//div[@data-qa-id='doctor_name']"); 
    By afterTime = By.xpath("//*[@id='container']/div[2]/div/div[1]/div/div[1]/div[2]/div[1]/div[2]/span[2]");        
    By afterDate_locator = By.xpath("(//span[@class=\"u-bold\"])[1]");  
    String doctorNameBefore;

    // Compare doctor details
    public String compareDetails() {
        doctorNameBefore = driver.findElement(beforeName).getText().trim();
        return doctorNameBefore;
    }
    
    
    public boolean bookSlot() throws InterruptedException {
    	System.out.println("doc name"+doctorNameBefore);
        driver.findElement(bookButton).click();
        
       
        
        Thread.sleep(2000);
        List<WebElement> timesFrames = driver.findElements(slots);
        String beforeTimeString = "";
        String afterTimeString = "";
        String selectedDate = "";
        List<WebElement>selectedDate_list=driver.findElements(By.xpath("//div[@class='u-t-capitalize']"));
        int i=0;
        for (WebElement seat : timesFrames) {
            
        	if (!(seat.getText().contains("No"))) {
                seat.click();
                selectedDate =selectedDate_list.get(i).getText();
                Thread.sleep(4000);
                System.out.println("Selected Date: " + selectedDate);
                break;
            }
        	i+=1;
        }
        // Capture the time slots and proceed
        List<WebElement> Slots = driver.findElements(By.xpath("//div[@data-qa-id='slot_time']"));
        for (WebElement slot : Slots) {
            beforeTimeString = slot.getText(); 
            slot.click();
            break;
        }
        boolean doctor_matched=false;
        String doctorNameAfter = driver.findElement(afterName).getText().trim();
        if (doctorNameBefore.equals(doctorNameAfter)) {
        	doctor_matched=true;
            System.out.println("Doctor name matches.");
        } else {
            System.out.println("Doctor name does not match!");
        }
        
        
        afterTimeString = driver.findElement(this.afterTime).getText(); // Capture the after time as String
        System.out.println("Before Time: " + beforeTimeString + " | After Time: " + afterTimeString);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH); // Time formatter
        LocalTime beforeTime = LocalTime.parse(beforeTimeString, timeFormatter); 
        LocalTime afterTime = LocalTime.parse(afterTimeString, timeFormatter);  
        boolean time_matched=false;
        if (beforeTime.equals(afterTime)) {
        	 time_matched=true;
            System.out.println("Time has been matched.");
        } else {
            System.out.println("Time has not been matched.");
        }

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        String formattedDate = "";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);

        if (selectedDate.contains("Today")) {
            formattedDate = today.format(dateFormatter); 
        } else if (selectedDate.contains("Tomorrow")) {
            formattedDate = tomorrow.format(dateFormatter); 
        } else {
            formattedDate = selectedDate;
        }
        
        String afterDate=driver.findElement(afterDate_locator).getText();
        Thread.sleep(2000);
        System.out.println("Formatted Selected Date: " + formattedDate);
        if (formattedDate.equals(afterDate)) {
        	System.out.println("Dates same");
        }
        else {
        	System.out.println("Dates  not same");
        }

      
      
        
    
        return doctor_matched && time_matched;
        

      
        
    		   

}

      
}