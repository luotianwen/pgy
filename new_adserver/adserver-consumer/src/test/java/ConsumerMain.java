import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Function:
 *
 * @version $Revision$ $Date$
 *          Date: 2016/5/5
 *          Time: 15:44
 * @author: mm
 * @since 3.0
 */
public class ConsumerMain {
    public static void main(String args[]){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/applicationContext-*.xml");
        context.start();
         synchronized (ConsumerMain.class){
             while(true){
                 try {
                     ConsumerMain.class.wait();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }
    }
}
