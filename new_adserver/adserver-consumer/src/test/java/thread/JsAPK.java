package thread;

import com.kokmobi.server.util.DesUtils;
import com.kokmobi.server.util.HttpUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Function:
 *
 * @version dd_adserver 3.0
 *          Date: 2016/3/1
 *          Time: 17:07
 * @author: Xiao Jia
 */
public class JsAPK {
    private JsAPK(){}

    private static final String str = "coo_id=1234567&imei=1&";

    private static final String SEPARATOR="&";

    /*
    * http://127.0.0.1:8080/newjsApk.action?
    * coo_id=1234567&
    * pkgid=1&
    * imei=123888888553
    * */
    public static void main(String[] args) throws IOException {
        // 从 pkgId.txt 文件中读取文件
        new Thread(){
            @Override
            public void run() {
                try {
                    GetPkgList.getData();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
        // 开辟多个线程访问
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        String pkgStr;
                        while (null != (pkgStr = GetPkgList.getPkg())) {
                            StringBuilder reqSB = new StringBuilder(str);
                            // pkgid
                            reqSB.append("pkgid=");
                            reqSB.append(pkgStr);

                            System.out.println(reqSB.toString());
                            String resp = HttpUtils.sentPost("http://192.168.199.172:8082/newservice/newjsApk.action", reqSB.toString());
                            try {
                                System.out.println(DesUtils.decryptDES(resp));
                            } catch (Exception e) {
                                System.out.println(resp);
                            }
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.start();
        }
    }
}


class GetPkgList {
    private static boolean isReadEnd = false;
    private static LinkedList<String> list = new LinkedList<>();

    public static void getData() throws IOException {
        FileReader reader = null;
        reader = new FileReader("C:\\Users\\Administrator\\Desktop\\pkgId.txt");
        BufferedReader bReader = new BufferedReader(reader);
        String line;
        while (null != (line = bReader.readLine())) {
            list.addLast(line);
        }
        isReadEnd = true;
    }

    public synchronized static String getPkg() throws InterruptedException {
        if (isReadEnd && 0 == list.size()) return null;

        while (!isReadEnd && 0 == list.size()) {
            Thread.sleep(100L);
        }
        String first = list.getFirst();
        list.removeFirst();
        return first;
    }
}
