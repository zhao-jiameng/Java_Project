import java.util.Arrays;
import java.util.Random;

public class Test01 {
    public static void main(String[] args) {
        Random er=new Random();
        int [] arr=new int[5];
        for (int i=0;i<arr.length;i++){
            arr[i]=-1;
        }
        int index=0;
       while(index<arr.length){
        int a= er.nextInt(6);
        if (!contaains(arr,a)){
            arr[index++]=a;

        }
       }
       for (int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }

    }
    public static boolean contaains(int []arr,int key){

        for (int i=0;i<arr.length;i++){
            if(arr[i]==key){
                return true;
            }

        }
        return false;

    }
}
