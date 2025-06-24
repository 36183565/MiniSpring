import com.minis.ClassPathXmlApplicationContext;
import com.minis.test.AService;

public class Test {

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
            AService aService = (AService) classPathXmlApplicationContext.getSingleton("aservice");
            aService.sayHello();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

