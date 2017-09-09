import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/5/5
 *          Time: 15:33
 * @author: mm
 * @since 3.0
 */
public class ProviderMain {
    public static void main(String args[]){
        com.alibaba.dubbo.container.Main.main(args);
       /* ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/applicationContext-*.xml");
        context.start();
        synchronized (ProviderMain.class){
            while(true){
                try {
                    ProviderMain.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }
}
