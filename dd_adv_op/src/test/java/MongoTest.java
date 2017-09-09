import com.kkgame.feeop.detail.bean.AdvFeebackListClickModelVO;
import com.kkgame.feeop.detail.dao.AdvFeebackListClickModelDAO;
import com.kkgame.feeop.util.DatabaseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


/** 
 * TODO 
 * @author cuiran 
 * @version TODO 
 */  
public class MongoTest {  
  
    private static Log log = LogFactory.getLog(MongoTest.class.getName());
      
    private AdvFeebackListClickModelDAO pr=null;
      
    /**
     *  
     *<b>function:</b> 
     * @author cuiran 
     * @createDate 2012-12-12 16:08:02 
     */  
    public void init(){  
          System.out.println("开始启动");  
         ApplicationContext ctx = new ClassPathXmlApplicationContext("/conf/spring/applicationContext.xml");
          pr= (AdvFeebackListClickModelDAO)ctx.getBean("advFeebackListClickModelDAO");
           
          
          
    }  

    private void query(){
        AdvFeebackListClickModelVO b=new AdvFeebackListClickModelVO();
        b.setImei("1");
        b.setTable("adv_feeback_list_click_20160602");
        List<AdvFeebackListClickModelVO> a= null;
        try {
            a = pr.getAdvFeebackListClickModelVOList(b);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        for (AdvFeebackListClickModelVO as: a
             ) {
            System.out.println(as.getImei()+as.getCdate()+"ssss");
        }

    }
    /**
     *
     *<b>function:</b>测试方法
     * @author cuiran
     * @createDate 2012-12-12 16:11:37
     */
    public void start(){
        init();
        query();



    }
      
    /** 
     *<b>function:</b>main函数 
     * @author cuiran 
     * @createDate 2012-12-12 11:54:30 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        MongoTest t=new MongoTest();  
        t.start();  
    }  
  
} 