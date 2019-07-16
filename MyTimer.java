import java.awt.*;

class MyTimer extends Thread {
    public int time;// 该变量用于计时
    public boolean flag1;// 该变量用于终止线程，原理为run()方法真代码执行完
    public boolean flag2;// 该变量用于暂停线程
    Label myLabel = new Label();

    public MyTimer(Label label) {
        time = 0;
        flag1 = true;
        flag2 = true;
        myLabel = label;
    }

    public void run() {
        while (flag1) {
            while (flag2) {
                myLabel.setText(MyToString.timeToString(time));
                time++;
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                }
            }
            try {
                sleep(100);// 当flag2被置false后每0.1s查一次flag2（人类最快反应时间>0.1s）
            } catch (InterruptedException e) {
            }
        }
    }
}