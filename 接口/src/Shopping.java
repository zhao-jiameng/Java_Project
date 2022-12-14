import java.util.Scanner;

public class Shopping {
    public Shopping() {
    }

    public static void main(String[] args) {
        int mn = 8;
        System.out.println("妍妍有20块，买完书本还剩8块");
        System.out.println("商店还有商品\n1:铅笔1块\n2:橡皮2块\n3:可乐3块\n4:零食五块");
        System.out.println("你现在还有" + mn + "元");
        while (mn != 0) {
            System.out.println("你可以继续购买商品\n1:铅笔1块\n2:橡皮2块\n3:可乐3块\n4:零食五块");
            System.out.println("你可以输入你要买的商品序号，我帮你计算可以买几件，买完还剩多少钱");
            System.out.println("你也可以选择买还是不买，按q退出");
            Scanner s = new Scanner(System.in);
            String a = s.next();
            switch (a) {
                case "1":
                    System.out.println("你还剩" + mn + "块，做多可以买" + (mn / 1) + "根铅笔\n请选择你要的根数或者按q返回");
                    if (mn / 1 <= 0)
                    {
                        System.out.println("\n你买不起，走吧");
                        return;
                    }
                    else {
                        String 铅笔 = s.next();
                        switch (铅笔) {
                            case "1":
                                System.out.println("你买了1根铅笔，买完还剩" + (mn-=1)+ "块");
                                break;
                            case "2":
                                System.out.println("你买了2根铅笔，买完还剩" + (mn-=2) + "块");
                                break;
                            case "3":
                                System.out.println("你买了3根铅笔，买完还剩" + (mn-=3) + "块");
                                break;
                            case "4":
                                System.out.println("你买了4根铅笔，买完还剩" + (mn-=4) + "块");
                                break;
                            case "5":
                                System.out.println("你买了5根铅笔，买完还剩" + (mn-=5) + "块");
                                break;
                            case "6":
                                System.out.println("你买了6根铅笔，买完还剩" +(mn-=6) + "块");
                                break;
                            case "7":
                                System.out.println("你买了7根铅笔，买完还剩" + (mn-=7) + "块");
                                break;
                            case "8":
                                System.out.println("你买了8根铅笔，买完还剩" + (mn-=8) + "块");
                                break;
                            case "q":
                                break;
                            default:
                                System.out.println("您输入的有误请重新输入");
                                break;
                        }

                    }
                    break;
                case "2":
                    System.out.println("你还剩" + mn + "块，做多可以买" + (mn / 2) + "个橡皮\n请选择你要的个数或者按q返回");
                    if (mn / 2 <= 0)
                    {
                        System.out.println("\n你买不起，走吧");
                        return;
                    }
                    else {
                        String 橡皮 = s.next();
                        switch (橡皮) {
                            case "1":
                                System.out.println("你买了1个橡皮，买完还剩" + (mn-=2) + "块");
                                break;
                            case "2":
                                System.out.println("你买了2个橡皮，买完还剩" + (mn-=4) + "块");
                                break;
                            case "3":
                                System.out.println("你买了3个橡皮，买完还剩" + (mn-=6)+ "块");
                                break;
                            case "4":
                                System.out.println("你买了4个橡皮，买完还剩" + (mn-=8) + "块");
                                break;
                            case "q":
                                break;
                            default:
                                System.out.println("您输入的有误请重新输入");

                        }
                    }
                    break;
                case "3":
                    System.out.println("你还剩" + mn + "块，做多可以买" + (mn / 3) + "瓶可乐\n请选择你要的个数或者按q返回");
                    if (mn / 3 <= 0)
                    {
                        System.out.println("\n你买不起，走吧");
                        return;
                    }
                    else {
                        String 可乐 = s.next();
                        switch (可乐) {
                            case "1":
                                System.out.println("你买了1瓶可乐，买完还剩" + (mn-=3) + "块");
                                break;
                            case "2":
                                System.out.println("你买了2瓶可乐，买完还剩" + (mn-=6) + "块");
                                break;

                            case "q":
                                break;
                            default:
                                System.out.println("您输入的有误请重新输入");

                        }

                    }
                    break;

                case "4":
                    System.out.println("你还剩" + mn + "块，做多可以买" + (mn / 5) + "包零食\n请选择你要的个数或者按q返回");
                    if (mn / 5 <= 0){
                        System.out.println("\n你买不起，走吧");
                        return;
                    }
                    else {
                        String 零食 = s.next();
                        switch (零食) {
                            case "1":
                                System.out.println("你买了1包零食，买完还剩" + (mn-=5 )+ "块");
                                break;
                            case "q":
                                break;
                            default:
                                System.out.println("您输入的有误请重新输入");
                                break;

                        }

                    }
                    break;
                case "q":
                    return;




                default:
                    System.out.println("您输入的有误请重新输入");
            }
        }
    }
}