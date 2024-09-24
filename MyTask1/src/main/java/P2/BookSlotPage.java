package P2;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BookSlotPage extends HomePage {
    By bookButton = By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[6]/div[1]/div/div[2]/div/div/div[2]/div/button");
    By slots = By.xpath("//div[@data-qa-id='date_selector']");
    
    By afterName = By.xpath("//*[@id=\"container\"]/div[2]/div/div[1]/div/div[1]/div[3]/div/div[2]/div[1]");
    By beforeName = By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[7]/div[1]/div/div[1]/div[2]/a/div/h2");
  
    By aftertime = By.xpath("//*[@id=\"container\"]/div[2]/div/div[1]/div/div[1]/div[2]/div[1]/div[2]/span[2]");
    By beforetime = By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[7]/div[2]/div/div/div/div[2]/div[2]/div/div[2]/div[1]/span");
  
  

    public String bookSlot() throws InterruptedException {
    	
        driver.findElement(bookButton).click();
        Thread.sleep(3000);
        //check available dates
        List<WebElement> timesFrames = driver.findElements(slots);
        String date_availability = "";
        String before_date = "";
        //navigate to available dates
        for (WebElement seat : timesFrames) {
            if (!(seat.getText().contains("No"))) {
                date_availability = seat.getText();
                System.out.println(date_availability);
                seat.click();
                break;
            } else {
                System.out.println(seat.getText());
                before_date = driver.findElement(By.xpath("//button[@data-qa-id='next_slots']")).getText();
                System.out.println(before_date);
            }
        }
  

        Thread.sleep(1000);
        //check available timeslots
        List<WebElement> slotElements = driver.findElements(By.xpath("//div[@data-qa-id='slot_time']"));

        // Click on the first available slot
        for (WebElement slot : slotElements) {
            slot.click();
            break;
        }
        
 
        String after_date = driver.findElement(By.xpath("(//span[@class='u-bold'])[1]")).getText();
        System.out.println(before_date + "  " + after_date);
       
        String aftername1=driver.findElement(afterName).getText().trim();
        String beforename1=driver.findElement(beforeName).getText().trim();
        
        boolean name_matched=aftername1.equals(beforename1);
        if(aftername1.contains(beforename1)){
        	System.out.println("BeforeName and afterName is  same");
        	
        	
        }
        else {
        	System.out.println("BeforeName AND afterName is not same");
        }
        
        String before_time1 = driver.findElement(By.xpath("//div[@data-qa-id='slot_time']/span")).getText();
	 	String after_time1 = driver.findElement(By.xpath("(//span[@class='u-bold'])[2]")).getText();
	 	System.out.println(before_date+"  "+after_date);
	 	
	 	Boolean time_matched = false;
	 	if(before_time1.contains(after_time1)) {
	 		System.out.println("Time is been Matched");
	 		time_matched=true;
	 	}
	 	else {
	 		System.out.println("Time is not matched");
	 	}
        // Return status message based on matching
        if ( name_matched&&time_matched) {
            System.out.println("Successfully Slot is Booked");
            return "Successfully Slot is Booked";
        } else {
            System.out.println("Slot is not Booked");
            return "Slot is not Booked";
        }
    }
   

   
}
