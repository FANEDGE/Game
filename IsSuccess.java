import java.awt.*;

class IsSuccess {
    static int location[] = new int[8];//存储每个按钮的相对位置，当该数组为1-8升序排列时，游戏完成

    public final static boolean isSuccess(Button[] bu) {
        int x;
        int y;
        for(int i=0;i<bu.length;i++){
            x=bu[i].getX();
            y=bu[i].getY();
            location[i]=Move.getLocation(x, y);
        }
        for (int i = 0; i < 8; i++) {
            if (location[i] != i + 1) {
                break;
            } else if (i != 7) {
                continue;
            } else if (location[7] == 8) {
                return true;
            }
        }
        return false;
    }
}