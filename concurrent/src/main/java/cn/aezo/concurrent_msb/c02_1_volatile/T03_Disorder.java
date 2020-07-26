package cn.aezo.concurrent_msb.c02_1_volatile;

/**
 * 验证进行了指令重排(执行时间可能较长)
 *
 * 感官上(假设没有进行指令重排)，one、two两个线程不管谁先运行，谁后运行，都不可能出现x=0,y=0
 * 实际是可以出现x=0,y=0这种情况的，因此进行了指令重排
 *
 * @author smalle
 * @date 2020-07-02 23:04
 */
public class T03_Disorder {
    private static int x = 0, y = 0;
    private static int a = 0, b =0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for(;;) {
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            Thread one = new Thread(new Runnable() {
                public void run() {
                    //由于线程one先启动，下面这句话让它等一等线程two. 读着可根据自己电脑的实际性能适当调整等待时间.
                    //shortWait(100000);
                    a = 1;
                    x = b;
                }
            });

            Thread two = new Thread(new Runnable() {
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            one.start();two.start();
            one.join();two.join();
            String result = "第" + i + "次 (" + x + "," + y + "）"; // 第32948次 (0,0）
            if(x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {
                //System.out.println(result);
            }
        }
    }


    public static void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do{
            end = System.nanoTime();
        }while(start + interval >= end);
    }
}
