import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;

public class Srbot {
    public static void main(String[] args) throws InterruptedException, IOException {

        String numenel = "+56994447606";
        String numnacho = "+56953026331";

        //Cargar datos desde cvs a array y prepalarlos para entregar a webdriver


        //WebDriver driver;
        ChromeDriver driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 2);

        //Cargar la página
        driver.get("https://web.whatsapp.com/");

        //inicio sesion
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='rememberMe']")).click();
        Thread.sleep(18000);

        //busqueda de chat, envio de mensajes y descarga de archivos
        driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys(numnacho,Keys.ENTER);
        for (int i = 1; i < 6; i++) {
            driver.findElement(By.xpath("//div[@spellcheck='true']")).sendKeys("hola, estamos probando el bot, mensaje numero: "+i,Keys.ENTER);
        }
        driver.findElement(By.xpath("//a[contains(text(),'https://drive.google.com/uc?export')]")).click();
        driver.findElement(By.xpath("//button[@title[last()]]")).click();

        //Manejo de archivos desde descarga a carpeta seleccionada


    }
}