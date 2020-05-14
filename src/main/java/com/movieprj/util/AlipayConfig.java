package com.movieprj.util;

import org.springframework.stereotype.Component;

@Component
public class AlipayConfig {
    public final static String APP_ID = "2016102200740408";//沙箱支付宝环境发起支付请求的应用ID
    //生成的应用私钥
    public final static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCFiWpkOt9GcRbBiYxOAMBOOosoIWn5eURklEY97UYmXd5yo39TkDjwiSZkln4YVtdQFcaxf5dtZGTgZf9FwK7zq23FVuWvH2kMytC+A5JFdY+T5iRgmKC8RUjGOZXSWmC6BbM7b7KXdFR2i3Hden453fPRoX2hL6n/JHmBHzJXx7c3wuJaqYwiZamUBh+dO8aK/e2xlwDl6qgOdZgj/TmIVhzoB7r5GlFNHBJMdbUDtbu0j/bsKOUoidmLA7LL+UVXLY/FxVMg9Cs9ZD1wq0yO35ceCM+qtnwTU+Gl7lBvUBNv6vdi4fXHycCuivrt+Q/8JTWsC46nT1y/jLA2MCQfAgMBAAECggEAOghC/TX8jD9fVVJyb/+xkw2sJg+HX4yM5WzurcDs6IWIw6c8EVOu+xGPZs1kFS3AOqSMCd1aMIcds8t/yY94OJr6hHVEMfvjilSlrL4eliMF949fpb8Os2MkwkiKTLVMLeuGPcFICrvabP4iyM6QBpPnfuXAlAkDxsbSKwQku7KFZ9nzeyjBrhFoxXCavPfcY7vfcLpb/IxT0Zz+NlAx8Gm+g+IE6+pDABc4u17mloLQ+vkxuAUc1liAvEBeC+4lOl4IdTASQdBsVmwlKmCHJoX6EejRr/eskKSNC6Dfk/YdlLVr34tRRV9K3t6yVTnAetWgFWgfVvGwKmSzImcP6QKBgQDFZvvqBSeyWzg+L6Q6DoXUuPmiVL22+Ox1aGaGvoMmnaxMKCP6hT0ulKfrfu/ktr6ZaZNmq7jYT45LG5usc1XUxWU9+NBKkFgaKLKHpvEO3x14eZsk1a0RfMUNsj911ifwh3HfsbGLjr172WFEZKvxK6dROfVhY9S0bpMdw23+wwKBgQCtLShEW3kyPnHAShQuVO/Dmvy04bV2oEqtYKI/EqvuOGhDaNmGUN7BGlFlFBXYTj9Aus0iW2VD7LfdGGWxsLuEsvBECY1yUvFZBpiQaxdERADs9tOU+7FepgtCAT0DapKJZ6sk3EvHBbpRqq2t+uM0AQBg31kt0f2gjvzGtMkndQKBgFVqmHrMvD6a5h1SdBeDDTncyHgLfwPbdkXfZO3NJebhtxpFDDxsxuqO/JIUHS20PYkYe+WjTJqc4g9x5MycI3mr6IEGVurMxrKbBZVZEVnEBXaJ+C3aH8c/W+zvQjJ09ldH5GhM9WxT2X3rtADqJtR2dTHaiGkZz4I7joEEfWYNAoGATQjWbboHKquObExHt+h+k5PBMDFrSG67aW6gHdCPfLPI5Zuhj9cvj9TGOmF56jO8fW3bTJo9bL7Dd22f5zBH6hqzOIDLU/zDcae2fB+3RN2/T6noqtBfsUvJnD3yxy9EKi1ce1QnvUu1+xgilE8TfJGpem5APFq6KQ8SMM5A4PUCgYBmMx7VK8qxkmapMVyapvbdBgndtNBorZbi5/AJfdz0RVzj1p9NUlPvcbr6CQwqqLahMn3Et7W7GH/2XWCq0l+jNLs4UgdWfXOyUbreJV9gtl4o4tFfrvWazLbJnHKGREDiMtaGCywOZnGeF+rH61P0tp0e6rtWpoFLe15k0ntYuw==";//生成的应用私钥
    //支付宝公钥
    public final static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAswGzh5gYf47ZZfvkItLb5/vojRqKr+OJDJmS++pbJ+6bBfFDIs+CUXCMgGbwedioSxMd1OkgpgFX/nmm1s9JrCLUSsPa7s9OZEJDoX8HpwiE2+Dqu10QQJ1j2mFU6X5q8TMpHzJPv/7id4RP0N7c07RMJu24bkPC3JH77rCXCJLV5MjFeglmGeXp4A1nSAXmK9UmBNcbuscCLttXB8u7xgg/mV5kGbLylff5ygs07qbJKGauc7qhFrb/RZDQ+BHssSieTm3+lhijLVbxjBxA408rU+ayiGxBDiivAuC1gWNJG4l1WxMQw0lAT4vsQOUpsM5PC9O5e38xU4cyssVN8QIDAQAB";//支付宝公钥
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    public final static String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";//沙箱请求网关地址
    public final static String CHARSET = "UTF-8";// 编码
    public final static String FORMAT = "JSON";// 返回格式
    public final static String SIGN_TYPE = "RSA2";// 签名方式RSA2

    //支付宝服务器异步通知页面路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    public final static String NOTIFY_URL = "";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    public final static String RETURN_URL = "http://localhost:8080/group_buy";
    //  public final static String RETURN_URL = "http://localhost:28089/alipay/alipayReturnNotice";//本地回调
}
