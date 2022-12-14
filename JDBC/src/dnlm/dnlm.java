package dnlm;

public class dnlm {

    public static void main(String[] ages) {
        int [] arr={99,888,4,2,8};
        System.out.println("排序前");
        print(arr);
        xunhuan(arr);
        System.out.println("排序后");
        print(arr);
    }

    public static void print(int [] arr) {
        for(int i=0;i< arr.length ;i++){
            System.out.println(arr[i]+" ");
        }
        System.out.println("\n");
    }

    public static void xunhuan(int [] arr) {
        for(int i=0;i< arr.length-1;i++){
            for(int j=0;j< arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int t=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=t;
                }
            }
            System.out.println("第"+(i+1)+"轮排序");
            print(arr);
        }
    }
}
