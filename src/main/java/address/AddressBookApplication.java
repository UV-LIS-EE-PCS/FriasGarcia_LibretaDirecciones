package address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
/***
 * @author Kevin Sebastián Frias García
 * Class in charge to start the application
 */
@SpringBootApplication
public class AddressBookApplication extends Menu{
    public static void main(String[] args) throws IOException {
        SpringApplication mainApplication = new SpringApplication(AddressBookApplication.class);
        mainApplication.setWebApplicationType(WebApplicationType.NONE);
        ApplicationContext context = mainApplication.run(args);

        // Obtener el bean de tipo Menu y llamar a displayMenu
        Menu menu = context.getBean(Menu.class);
        menu.displayMenu();
    }
}