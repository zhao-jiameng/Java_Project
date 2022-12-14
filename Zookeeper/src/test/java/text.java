public class text {
    public static void main(String[] args) {
        int []nums=new int[]{1,3,22,4,56,7,8,23,11,43,5,0,33,12,4,8,34,22,89};
        int evenCount=0;
        int oddCount=0;
        for (int i = 0; i < nums.length; i++) {
            if((nums[i]&1)==1){
                oddCount++;
            }else {
                evenCount++;
            }
        }
        System.out.println("奇数的个数:"+oddCount);
        System.out.println("偶数的个数:"+evenCount);
    }
}
