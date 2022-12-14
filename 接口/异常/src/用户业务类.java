public class 用户业务类 {
    public void 注册(String name,String passwd) throws 异常 {
        if (null==name||name.length()<6||name.length()>14){
            throw new 异常("用户名错误");
        }else{
            System.out.println("登录成功，欢迎登录");
        }
    }
}
