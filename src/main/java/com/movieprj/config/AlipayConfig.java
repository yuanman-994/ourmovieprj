package com.movieprj.config;

import java.io.FileWriter;
import java.io.IOException;




public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102200740058";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCSjpPUwN7g96qeYqInuwuX7UX2jnCuQ4fBadx8OcjNVOrwd8g2qcqIOjgE3vr5JCxy6zte7Y0WFrO+Yua/maqqZ9UN1h/l3YrQAWkY+NlsK9gwaJUIryDlsFQLCRYsMY5d2evRIjMs1BnE2n+QSJi7q+nRlzo5rLQGB0Guk1+iTR1emWenbp6pBzzVcZ1VhWo3HRAVgWioFqldNTGk2w9SeZF91hkh0o7giFpsgAKy/0b3OQnDoTe1NDCfYqbUrBkaiWrRv9vgc79Ffxb/CB7hMlZghuymFym+jIfUD/txGKhtH/HbThyKM9w2Mt722J0PYIMbrONMXB1M33LOKbD/AgMBAAECggEAY59hpBybJKeWJmX7vjAqWFLk3Q3EUOD8tiAEzbjl45XWwjFaOrY+Q0336G++gH7zbAa06/lkj2BNPJy0KAgH+B/utqIYOsPW6UAcNepquVowce+VZzrMjPXIm1vI46+oC+nnn9i0msNu5N52GhkPIXthzKQU40Gfef2Sy13Ig2UKtQ9GQNSA3rS8awXH9qv2sCQFDZ57bZddtmb1UGvO6vHpoV3BxahtVWIoQJBHAFHYF0V3AsUv/AVLKa1lAZJdrbYKgxv5EnwN9fb+PqkxB1WxAe2zmh+Qz30avnm0/BV4wxJFlYSfOnAV8Ew3i/QSZQZ/4/5ApYIRDOaqtLnSUQKBgQDEoM8o7vSP0JZgozv1xWT0LjFev/wYIEFNM3xXYXVOfjA8VGez7arOdXhumynfZTtRX2JoKnxrU8IvP7MUURGziyHr/6SO1PYYXhQu3HV0SUvKrJTONz5a8YLygY1vm3O6XXL2ET7ICDOWJakOT7X+b/TVviHuo+G/uCkSgGHNGQKBgQC+z04usuX6h8wIeWJlj+LIMotLW+zNfMdElKv3AUca9V6zVWU0tmjddM4u+nvmiXvZosVQwthGHOfLsjDLlpw+4ssHZvjiXVTcYujlrXONDDSI9VHFkgahcZI4xxMhfFF97VE7QTBipNEzvKOJhZ5CSC8TRsNOyYz2tSQ0h9cZ1wKBgQCZsQB8BFtQ1j/UhYLmlkZUvo6Eexf3W67vQyEbw+fHVMcrQOZWM51hB1z9R3cxUJVPA3mN5jeeRLfx6p51cPCfIE+Bgi8EHd3GKjModdWw8WG2p7MghPnOaBxspWsCI8YPk9+6CrzB6fc4k3NS5mdg2+3J7HXlbF+WVcJ03biUcQKBgQCPlGkNZNr/eV4D5msvZ2VMJhSBIWwAeGtt5L2+AX0jAQ5AvfqI/tXMBs/7s8q2ADBs5Tm6+tNJdwjJU426NNvfykLikA6qL3bi257K7Yks0slZYdglZiuNImvYQ5zWrriXnJNqljQ1ljZByyn4Gs6qq6yYwb/HsoxbPJVA3hLzQwKBgQC6bp3oJKziHPIWRFnjsKg5NbSuqv9YSOWMRCkyGRSGIubCpaLNljazW4PYFB8s/auatS+C2+CKWvuJ/eTenKUOITMrkeZqvFcA6+azw6sJkx+IyHJwq87F/klxgU2mReOFakN4UwwhbdntB2uOnutkCKUXkB71y/T0I2GdlR4TDw==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnJzRtuu4Dkk+s2OMshMbmpKOuBsmoBcffma49NP9DI0LtxcO4H2OX9ztDLT/+zsP3j3aujZV0nnLj+GZm4b1hjA8LbUr0OMRofe+3ZxI9uHWDtgL8xO3f5M8UsqNQbN7/xWvyn6rgb7rw4Dc1gAJJ/h/GaGxXGyYcK/56QuW1qYWqClmwY4ySDsgq1g9m2ek3jge4zfKCySat9XdZDuDcWgZsTBR/oAwh2P77iSnlvSCYvMTpGz5N7jmnkkN7plhevytWl0z2H8f7C7LkAi3l57/goOzBz9d7n8Gg+PqiK6SbyMVeRFRQH33llqwF2sfKkWP5dbns404OtCtiCpdXQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/alipayNotifyNotice";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/alipayReturnNotice";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

