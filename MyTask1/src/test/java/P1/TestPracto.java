package P1;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import P2.BookSlotPage;
import P2.HomePage;
public class TestPracto extends PractoBase {
     HomePage h=new HomePage();
     BookSlotPage b=new BookSlotPage(); 
     @Test(priority=1)
     public void ValidListDocotrs() throws Exception {
         h.setUp();
         h.openUrl();        
         test = report.createTest("Valid List of Doctors");
         if (h.findDoctors()) {
             test.log(Status.PASS, "Doctors were found and belong to the same profession.");
         } else {
             test.log(Status.FAIL, "No doctors found or they belong to different professions.");
         }
     }
     @Test(priority=2)
     public void ValidTimeSlotAndDateAndName() throws Exception {
         test = report.createTest("Valid Time Slot and Date and Name");
         String bookingStatus = b.bookSlot();
         String expectedStatus = "Successfully Slot is Booked";
         Assert.assertEquals(bookingStatus, expectedStatus, "The booking status is not as expected.");
         test.log(Status.INFO, "Booking Status: " + bookingStatus);
     }
     @Test(priority=3)
     public void validFilteredValues() throws Exception {
         test = report.createTest("Validate Filtered Values");
         if (h.filterfees()) {
             test.log(Status.PASS, "Doctors were found and all fees are above ₹1000.");
         } else {
             test.log(Status.FAIL, "Not all doctors have fees above ₹1000.");
         }
     }
     @AfterSuite
     public void cleanUp() {
         h.closeUrl(); 
         h.tearDown();  
     }
	}
