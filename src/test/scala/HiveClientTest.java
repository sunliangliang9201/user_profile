import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * desc
 *
 * @author sunliangliang 2018-03-08 https://github.com/sunliangliang9201/tv_realtime_display2
 * @version 1.0
 */
public class HiveClientTest {
    public static void main(String[] args) throws Exception {
//        String str = "KB@:4?nLj4v(by+-%h2";
//        System.out.println(getMd51(str));
//        System.out.println(getMd52(str));
        cli();
    }

    public static void cli() throws Exception {
        Class.forName("org.apache.hive.jdbc.HiveDriver");
//        Connection conn = DriverManager.getConnection("jdbc:hive2://103.26.158.33:11112/tv", "sunliangliang", "KB@:4?nLj4v(by+-%h2");
        Connection conn = DriverManager.getConnection("jdbc:hive2://103.26.158.182:2181,103.26.158.183:2181/tv;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2_zk", "sll123", "KB@:4?nLj4v(by+-%h2");
        PreparedStatement ps = conn.prepareStatement("select country from tv.tv_heart_new_org where dt='2019-03-03' limit 2");
        ResultSet res = ps.executeQuery();
        while(res.next()){
            System.out.println(res.getString(1));
        }
    }

    public static String getMd51(String str) throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        return new BigInteger(1, md.digest()).toString();
    }

    public static String getMd52(String str) throws Exception{
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','G','K','L','M','N'};
        byte[] btInput = str.getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(btInput);
        byte[] btOutput = md.digest();
        int j = btOutput.length;
        char[] string = new char[j*2];
        int k = 0;
        for(int i = 0; i<j; i++){
            byte byte0 = btInput[i];
            string[k++] = hexDigits[byte0 >>>4 & 0xf];
            string[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(string);
    }
}
