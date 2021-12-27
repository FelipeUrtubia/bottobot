import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import static java.util.concurrent.TimeUnit.SECONDS;

//loop del bot: hola (saludo/inicio del bot) -> 4 (opción de descarga de boleta) -> 2 (opción para cambiar el numero de cuenta que estamos buscando. no sale en el primer mensaje) -> ingreso numero de cuenta -> descarga de archivo -> chao (cierre del bot, si no se cumple el cierre, automaticamente enviará la boleta de la ultima cuenta usada) -> vuelta a empezar

public class Srbot {
    public static void main(String[] args) throws Exception {

        String numenel = "+56994447606";
        String numchilquinta = "+56935055832";

        //Cargar datos desde cvs a array y prepalarlos para entregar a webdriver

        HashMap<String, HashMap<String, String>> map = new HashMap<String, HashMap<String,String>>();
        map.put("key", new HashMap<String, String>());
        map.get("key").put("key2", "val2");


        ArrayList<String> cuentas = new ArrayList<String>();

        cuentas.add("365958");
        cuentas.add("717152");
        cuentas.add("443434");
        cuentas.add("690539");
        cuentas.add("480490");
        cuentas.add("354587");
        cuentas.add("702380");
        cuentas.add("663300");
        cuentas.add("321466");
        cuentas.add("656423");

        //WebDriver driver;
        ChromeDriver driver = new ChromeDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 2);

        //Cargar la página
        driver.get("https://web.whatsapp.com/");

        //inicio sesion
        driver.manage().timeouts().implicitlyWait(2, SECONDS);
        driver.findElement(By.xpath("//input[@name='rememberMe']")).click();
        driver.manage().timeouts().implicitlyWait(15, SECONDS);

        driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys(numchilquinta,Keys.ENTER);

        //distintos testeos de obtencion de los mensajes de entrada
        //WebElement messagecontainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='_1Gy50']")));
        //WebElement messagecontainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div._1LcQK > div > div._33LGR > div.y8WcF > div")));
        //WebElement messagecontainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div._1LcQK > div > div._33LGR > div.y8WcF > div.message-in > div.cvjcv > div.Nm1g1 > div._22Msk > div.copyable-text > div._1Gy50 > span.i0jNr > span")));
        WebElement messagecontainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div._1LcQK > div > div._33LGR > div.y8WcF > div.message-in > div.cvjcv > div.Nm1g1 > div._22Msk > div.copyable-text > div._1Gy50 > span.i0jNr> span:nth-child(1)")));
        //WebElement messagecontainer = document.querySelector("");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = null;
        element = (WebElement) js.executeScript("return  document.querySelector(\".message-in\");");
        if(element == null)
            throw new Exception("Mensaje no encontrado");

        //messagecontainer.findElement(By.cssSelector(""));

        System.out.println(element);
        System.out.println(element.getSize());
        System.out.println(element.getText());

        Thread.sleep(10000);

        int numerotext = messagecontainer.getSize().height;

        System.out.println(numerotext);

        // div._1LcQK > div > div._33LGR > div.y8WcF > div.message-in:nth-child(i) mensaje entrante, estan todos los mensajes en la misma lista

        //busqueda de chat, envio de mensajes y descarga de archivos en base a un loop que recorre el array de entrada de datos


        for (String cuenta : cuentas){

            //inico del bucle
            //se tiene que obtener la cuenta de los mensajes
            driver.findElement(By.xpath("//div[@spellcheck='true']")).sendKeys("hola", Keys.ENTER);

            String newCommentSelector = "_1Gy50 > span:nth-child(" + numerotext + ")";
            String textselector = "div._1LcQK > div > div._33LGR > div.y8WcF > div.message-in:nth-child(" + numerotext + ")";
            WebElement newComment = (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(newCommentSelector)));
            if(newComment == null) continue;

            numerotext++;

            System.out.println(newComment.getText());


            switch(textselector) {
                case "Hola nuevamente":
                    String newmessage = ""; //obtener el mensaje nuevo
                    switch (newmessage){
                        case "Elige el número de la opción que deseas":
                            //ingresar opción 4
                            driver.findElement(By.xpath("//div[@spellcheck='true']")).sendKeys("4", Keys.ENTER);
                            //obtener respuesta de la opción 4
                            String opc4message = "";
                            switch (opc4message){
                                case "Tu último número de cliente registrado es":
                                    //ingresar opción 2
                                    driver.findElement(By.xpath("//div[@spellcheck='true']")).sendKeys("2", Keys.ENTER);
                                    //obtener respuesta opción 2
                                    String opc2message = "";
                                    switch (opc2message){
                                        case "Por favor, escribe tu número de cleinte":
                                            //ingresar numero de cuenta de este ciclo
                                            driver.findElement(By.xpath("//div[@spellcheck='true']")).sendKeys(cuenta, Keys.ENTER);
                                            //obtener respuesta de la boleta
                                            String boletamessage = "";
                                            if (boletamessage == "Ese numero de cliente está registrado en la dirección"){
                                                //buscar boton para descargar boleta
                                                //descargar la boleta
                                                driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div/div/div/div/div/button[last()]")).click();
                                                //mandar mesage de cierre
                                                driver.findElement(By.xpath("//div[@spellcheck='true']")).sendKeys("adios", Keys.ENTER);
                                                //ckeckear la respuesta
                                                String byemessage = "";
                                                if (byemessage == "¡Hasta pronto!"){
                                                    System.out.println("ciclo de la cuenta: "+cuenta+", Completado con exito");
                                                    break;
                                                }else {
                                                    System.out.println("Error en el checkeo del mensaje de cierre");
                                                    break;
                                                }
                                            } else {
                                                System.out.println("Error en la recolección de la boleta");
                                                break;
                                            }
                                        default:
                                            System.out.println("Error en la recolección de la opción 2");
                                            break;
                                    }
                                    break;
                                default:
                                    System.out.println("Error en la recolección de la opción 4");
                                    break;
                            }
                            break;
                        default:
                          System.out.println("Error en la recolección del mensaje nuevo");
                          break;
                    }
                    break;
                default:
                    System.out.println("Ah ocurrido un error para tomar el primer mensaje");
                    break;
            }

        }

    }

}