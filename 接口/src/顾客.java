public class 顾客 {

    private 接口 jiekou;
    public 顾客(){

    }
    public 顾客(接口 jiekou){
        this.jiekou=jiekou;
    }

   public void setJIekou(接口 jiekou){
       this.jiekou=jiekou;
   }
   public 接口 getJiekou(){
        return jiekou;
   }
   public void 点餐(){
        接口 cd=this.getJiekou();
        cd.xihongshichaodan();
        cd.yvxiangrousi();
   }
}
