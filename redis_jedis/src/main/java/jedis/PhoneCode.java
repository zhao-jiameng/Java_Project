package jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @PROJECT_NAME: redis_jedis
 * @PACKAGE_NAME: jedis
 * @author: 赵嘉盟-HONOR
 * @data: 2022-09-25 12:07
 * @DESCRIPTION
 */
public class PhoneCode {
    public static void main(String[] args) {
        System.out.println(getCode());
        //模拟验证码发送
        verifyCode("16634116494");
        //校验
        getRedisCode("16634116494", "775852");
    }

    //生成六位数字验证码
    public static String getCode(){
        Random random = new Random();
        String code="";
        for (int i = 0; i < 6; i++) {
            int ran= random.nextInt(10);
            code+=ran;
        }
        return code;
    }

    //每个手机每天只能发三次验证，验证码放在redis中，设置过期时间
    public static void verifyCode(String phone){
        //连接redis
        Jedis jedis = new Jedis("192.168.174.101", 6379);

        //拼接key
        //手机发送次数key
        String countKey="VerifyCode"+phone+":count";
        //验证码key
        String codeKey="VerifyCode"+phone+":code";

        //每个手机每天只能发三次
        String count=jedis.get(countKey);
        if (count==null){
            //没有发送过，设置发送次数为1
            jedis.setex(codeKey, 24*60*60, "1");
        }else if (Integer.parseInt(count)<=2){
            //发送次数+1
            jedis.incr(codeKey);
        }else if (Integer.parseInt(count)>2){
            //发送了三次，不能再发
            System.out.println("今天次数已达三次，请每天再试");
            jedis.close();
            return;
        }

        //发送验证码到redis里面
        String vcode=getCode();
        jedis.setex(codeKey, 120, vcode);
        //关闭redis
        jedis.close();

    }
    //验证码校验
    public static void getRedisCode(String phone,String code){
        //从redis获取验证码
        Jedis jedis = new Jedis("192.168.174.101", 6379);

        //验证码key
        String codeKey="VerifyCode"+phone+":code";
        String redisCode = jedis.get(codeKey);

        //判断
        if(redisCode.equals(code)){
            System.out.println("匹配成功");
        }else{
            System.out.println("匹配失败");
        }
        jedis.close();
    }
}
