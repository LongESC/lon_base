package lon.demo;

/**
 * @projectName: lon_base
 * @package: lon.demo
 * @className: HMAC_SHA1
 * @author: LONZT
 * @description: TODO
 * @date: 2024/4/8 14:33
 * @version: 1.0
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.net.util.Base64;

/**
 * Title: 图片前面工具类
 */
public class HMAC_SHA1 {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1" ;
    private Mac mac = null ;

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String datastr = getImageStr("D:/test01.jpg");

        HMAC_SHA1 sha1 = new HMAC_SHA1("12345678abcdefgh87654321");
        String res = sha1.genHMAC(datastr); //这里是签名后的数据
        String res1 = URLEncoder.encode(res,"UTF-8"); //为了正确传输，签名后的数据一定要做encode
        System.out.println("签名后的数据：" + res);
    }

    public static String getImageStr(String imgFile) {
        InputStream in = null ;
        byte[] data = null ;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byte2hex(data);
    }
    public static String byte2hex(byte[] b) {
        try {
            if ((b == null) || (b.length <= 0)) {
                return null ;
            }
            StringBuffer sb = new StringBuffer();
            String stmp = "" ;
            for (int n = 0; n < b.length ; ++n) {
                stmp = Integer.toHexString(b[n] & 0xFF);
                if(stmp.length() == 1){
                    sb.append("0" + stmp);
                } else {
                    sb.append(stmp);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    public HMAC_SHA1(String HMAC_SHA1_KEY) throws NoSuchAlgorithmException, InvalidKeyException{
//根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKeySpec signinKey = new SecretKeySpec(HMAC_SHA1_KEY.getBytes(), HMAC_SHA1_ALGORITHM);

//生成一个指定 Mac 算法 的 Mac 对象
        mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
//用给定密钥初始化 Mac 对象
        mac.init(signinKey);
    }

    public String genHMAC(String data) {
        byte[] result = null ;
        if (mac==null) {
            return null ;
        }
        byte[] rawHmac = mac.doFinal(data.getBytes());
        result = Base64.encodeBase64(rawHmac);
        if (null != result) {
            return new String(result);
        } else {
            return null ;
        }
    }
}
