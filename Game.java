import java.awt.*;
import java.awt.event.*;

class Game {
    protected String userName = null;// 用户名
    protected int time = 0;// 用户完成时间
    protected boolean flag = false;// 用于判断是否完成游戏
    protected int num = 0;// 用于统计完成人数
    protected People people[] = new People[100];// 该数组用于存放用户对象

    public Game() {
        // 初始化排行榜
        for (int i = 0; i < people.length; i++) {
            people[i] = new People();
        }
        // 创建组件
        Frame frame = new Frame("魔板游戏");
        Label label = new Label();
        Button[] bu = new Button[8];// 创建按钮数组
        Panel panel = new Panel();
        MyTimer mytimer = new MyTimer(label);// 创建计时器
        // 创建下拉菜单
        MenuBar mb = new MenuBar();
        Menu menu = new Menu("Game");
        MenuItem start = new MenuItem("Start", new MenuShortcut(KeyEvent.VK_N));
        MenuItem restart = new MenuItem("Continue", new MenuShortcut(KeyEvent.VK_N));
        MenuItem suspend = new MenuItem("Suspend", new MenuShortcut(KeyEvent.VK_P));
        MenuItem stop = new MenuItem("Stop", new MenuShortcut(KeyEvent.VK_S));
        MenuItem rank = new MenuItem("Rank", new MenuShortcut(KeyEvent.VK_R));
        MenuItem exit = new MenuItem("Exit", new MenuShortcut(KeyEvent.VK_Q));
        // 设置组件
        frame.setSize(600, 700); // 设置窗体大小
        frame.setResizable(false); // 设置大小为不可更改
        frame.setLocationRelativeTo(null); // 居中显示窗体
        frame.setLayout(null); // 设置布局方式为自定义
        for (int i = 0; i < 8; i++) {
            bu[i] = new Button(Integer.toString(i + 1));
            bu[i].setFont(new Font("Serif", Font.PLAIN, 50));
        }
        label.setBounds(200, 70, 180, 40);
        label.setFont(new Font("Serif", Font.PLAIN, 50));
        panel.setBackground(Color.yellow);
        panel.setBounds(75, 150, 450, 450);
        panel.setVisible(false);
        // 下拉菜单相关设置
        restart.setEnabled(false);
        suspend.setEnabled(false);
        stop.setEnabled(false);
        // 按钮监听器，用于移动按钮
        ActionListener buttonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                for (int i = 0; i < 8; i++) {
                    if (cmd.equals(Integer.toString(i + 1))) {
                        int x = bu[i].getX();
                        int y = bu[i].getY();
                        int m = Move.move(x, y);
                        bu[i].setBounds(75 + (int) ((m + 2) % 3) * 150, (int) ((m - 1) / 3 + 1) * 150, 150, 150);
                        flag = IsSuccess.isSuccess(bu);
                        if (flag) {
                            mytimer.flag2 = false;// 停止计时
                            time = mytimer.time - 1;// 锁存完成时间，要与显示时间相符需减一
                            start.setEnabled(true);
                            restart.setEnabled(false);
                            suspend.setEnabled(false);
                            stop.setEnabled(false);
                            gameFinish();
                        }
                    }
                }
            }
        };
        // 菜单监听器
        ActionListener menuListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("Start")) {
                    mytimer.time = 0;
                    // 设置按钮位置
                    new ArrayRandom();
                    int b[] = new int[8];
                    for (int i = 0; i < b.length; i++) {
                        b[i] = ArrayRandom.arr[i];
                    }
                    for (int i = 0; i < 8; i++) {
                        bu[i].setVisible(false);
                        bu[i].setBounds(75 + (int) ((b[i] + 2) % 3) * 150, (int) ((b[i] - 1) / 3 + 1) * 150, 150, 150);
                    }
                    // 设置位置标志
                    for (int i = 0; i < 8; i++) {
                        Move.flags[i] = false;
                    }
                    Move.flags[8] = true;
                    start.setEnabled(false);
                    suspend.setEnabled(true);
                    stop.setEnabled(true);
                    for (int i = 0; i < 8; i++) {
                        bu[i].setVisible(true);
                    }
                    panel.setVisible(true);
                    if (!mytimer.isAlive()) {
                        mytimer.start();
                    }
                    mytimer.flag1 = true;
                    mytimer.flag2 = true;

                } else if (cmd.equals("Continue")) {
                    mytimer.flag2 = true;
                    restart.setEnabled(false);
                    suspend.setEnabled(true);
                    for (int i = 0; i < 8; i++) {
                        bu[i].setVisible(true);
                    }
                    panel.setVisible(true);
                } else if (cmd.equals("Suspend")) {
                    mytimer.flag2 = false;
                    restart.setEnabled(true);
                    suspend.setEnabled(false);
                    for (int i = 0; i < 8; i++) {
                        bu[i].setVisible(false);
                    }
                    panel.setVisible(false);
                } else if (cmd.equals("Stop")) {
                    mytimer.flag2 = false;
                    mytimer.time = 0;
                    label.setText(MyToString.timeToString(time));
                    start.setEnabled(true);
                    restart.setEnabled(false);
                    suspend.setEnabled(false);
                    stop.setEnabled(false);
                    for (int i = 0; i < 8; i++) {
                        bu[i].setVisible(false);
                    }
                    panel.setVisible(false);
                } else if (cmd.equals("Rank")) {
                    if (mytimer.isAlive()) {
                        mytimer.flag2 = false;
                        if (!flag) {
                            restart.setEnabled(true);
                        } else {
                            restart.setEnabled(false);
                        }
                        suspend.setEnabled(false);
                        for (int i = 0; i < 8; i++) {
                            bu[i].setVisible(false);
                        }
                        panel.setVisible(false);
                    }
                    rankPeople();
                } else if (cmd.equals("Exit")) {
                    mytimer.flag1 = false;
                    mytimer.flag2 = false;
                    System.exit(0);
                }
            }
        };
        // 添加组件
        start.addActionListener(menuListener);
        restart.addActionListener(menuListener);
        suspend.addActionListener(menuListener);
        stop.addActionListener(menuListener);
        rank.addActionListener(menuListener);
        exit.addActionListener(menuListener);
        menu.add(start);
        menu.add(restart);
        menu.add(suspend);
        menu.add(stop);
        menu.add(new MenuItem("-"));
        menu.add(rank);
        menu.add(new MenuItem("-"));
        menu.add(exit);
        mb.add(menu);
        frame.setMenuBar(mb);
        frame.add(label);
        for (int i = 0; i < 8; i++) {
            bu[i].addActionListener(buttonListener);
        }
        for (int i = 0; i < 8; i++) {
            frame.add(bu[i]);
        }
        frame.add(panel);
        // 设置窗体关闭方式
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mytimer.flag1 = false;
                mytimer.flag2 = false;
                System.exit(0);
            }
        });
        // 设置窗体显示
        frame.setVisible(true);
    }

    // 排名
    protected void rankPeople() {
        StringBuffer sb = new StringBuffer();
        String str = null;
        int length = 0;
        for (int i = 0; i < people.length; i++) {
            if (people[i].time == 0) {
                break;
            } else {
                length++;
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (people[i].getTime() > people[j].getTime()) {
                    People temp = new People();
                    temp = people[i];
                    people[i] = people[j];
                    people[j] = temp;
                }
            }
        }

        for (int i = 0; i < length; i++) {
            sb.append((i + 1) + ".   " + people[i].name + "   " + MyToString.timeToString(people[i].time) + "\n");
        }
        str = sb.toString();
        Frame frame = new Frame();
        TextArea ta = new TextArea();
        frame.setSize(500, 700);// 设置窗体大小
        frame.setResizable(false); // 设置大小为不可更改
        frame.setLocationRelativeTo(null); // 居中显示窗体
        frame.setLayout(null); // 设置布局方式为自定义
        ta.setBounds(10, 30, 500, 700);
        ta.setBackground(Color.lightGray);
        ta.setEditable(false);
        ta.setFont(new Font("Serif", Font.PLAIN, 20));
        ta.setText(str);
        // 添加组件
        frame.add(ta);
        // 设置窗体关闭方式
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        // 设置窗体显示
        frame.setVisible(true);
    }

    // 游戏结束后弹窗获取用户名
    protected void gameFinish() {// 3种退出方式：点击confirm按钮，在单行文本框内按回车，按关闭按钮
        Frame frame = new Frame();
        Label label = new Label("Please Intput Your Name:");
        TextField tf = new TextField();
        Button bu = new Button("comfirm");
        frame.setSize(400, 250);// 设置窗体大小
        frame.setResizable(false); // 设置大小为不可更改
        frame.setLocationRelativeTo(null); // 居中显示窗体
        frame.setLayout(null); // 设置布局方式为自定义
        label.setBounds(95, 70, 210, 20);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        tf.setBounds(95, 110, 210, 45);
        tf.setFont(new Font("Serif", Font.PLAIN, 30));
        tf.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    userName = tf.getText();
                    People p = new People(userName, time);
                    people[num] = p;
                    num++;
                    frame.dispose();
                }
            }
        });
        bu.setBounds(170, 180, 60, 35);
        bu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userName = tf.getText();
                People p = new People(userName, time);
                people[num] = p;
                num++;
                frame.dispose();
            }
        });
        frame.add(label);
        frame.add(tf);
        frame.add(bu);
        // 设置窗体关闭方式
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                userName = tf.getText();
                People p = new People(userName, time);
                people[num] = p;
                num++;
                frame.dispose();
            }
        });
        // 设置窗体显示
        frame.setVisible(true);
    }
}