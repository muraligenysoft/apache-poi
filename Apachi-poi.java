package crw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testpass {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		File file=new File(System.getProperty("user.dir") + "\\excel\\" + "logins16" + ".xlsx");
		 FileInputStream inputstream=new FileInputStream(file);
		 XSSFWorkbook wb=new XSSFWorkbook(inputstream);
		  XSSFSheet sheet=wb.getSheet("Sheet1");
		  
		  XSSFRow row=null;
		  XSSFCell cell=null;
		  String Username=null;
		  String Password=null;
		  
		  for (int i=1; i<=sheet.getLastRowNum();i++ )
		  {
			  row=sheet.getRow(i);
			  for ( int j=0;j<row.getLastCellNum();j++ )
			  {
				  cell=row.getCell(j);
				  if(j==0)
				  {
					  Username=cell.getStringCellValue();
				  }
				  if(j==1)
				  {
					  Password=cell.getStringCellValue();  
				  }
			  }
				  //System.out.println("Username: " + Username + "---> " + "Password: " + Password);
				  System.setProperty("webdriver.chrome.driver", "./toola/chromedriver.exe");
				  WebDriver driver = new ChromeDriver();
				  driver.manage().window().maximize();
				  FileInputStream fis=new FileInputStream("E:\\eclipse\\eclipse-jee-2021-12-R-win32-x86_64\\eclipse\\f\\maven\\Cofig.properties");
				  Properties prop=new Properties();
				  prop.load(fis);				  
				  driver.get(prop.getProperty("URL"));
				  driver.findElement(By.id("mat-input-0")).sendKeys(Username);
				  driver.findElement(By.id("mat-input-1")).sendKeys(Password);
				  driver.findElement(By.xpath("//*[@id=\"mat-tab-content-0-0\"]/div/app-login/div/div[2]/form/button")).click();
					Thread.sleep(2000);
					String result=null;
					try {
						Boolean isLoggedin=driver.findElement(By.id("Ecommercepricing")).isDisplayed();
						if(isLoggedin==true)
						{
							result="Pass";
							cell=row.createCell(2);
							cell.setCellValue(result);
							
							
							
						}
							
						System.out.println("Username: " + Username + "---> " + "Password: " + Password + "-->Login Sucess--> " + result );
						Thread.sleep(2000);
						driver.findElement(By.xpath("//div[@id='vdezi-intro-step1']/button/span/span")).click();
				        Thread.sleep(3000);
				        driver.findElement(By.xpath("//div[@id='vdezi-intro-step1']/div/a[6]/span")).click();
					}
					catch(Exception e)
					{
						Boolean isError=driver.findElement(By.id("vdezi-login-login-1")).isDisplayed();
						if(isError==true)
						{
							result="Fail";
							cell=row.createCell(2);
							cell.setCellValue(result);
						}
						Thread.sleep(3000);
						System.out.println("Username: " + Username + "---> " + "Password: " + Password + "-->Login Sucess--> " + result);
					}
					
					FileOutputStream fos=new FileOutputStream(file);
					wb.write(fos);
					fos.close();
			        
				  
				  
		  }
		  

		  

	}

}
