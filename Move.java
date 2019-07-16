class Move {
    protected static boolean flags[] = { false, false, false, false, false, false, false, false, true };// 用于确定九宫格的每个位置是否有按钮，没有为true
    protected static int location;// 用于确定按钮相对于九宫格的位置

    public final static int getLocation(int x, int y) {
        int m;
        switch (x / 75) {
        case 1:
            x = 1;
            break;
        case 3:
            x = 2;
            break;
        case 5:
            x = 3;
            break;
        }
        y = y / 150 - 1;
        m = x + y * 3;
        return m;
    }

    public final static int move(int x, int y) {
        location = getLocation(x, y);
        switch (location) {
        case 1: {
            if (flags[1]) {
                flags[1] = false;
                flags[0] = true;
                location = 2;
            } else if (flags[3]) {
                flags[3] = false;
                flags[0] = true;
                location = 4;
            }
            break;
        }
        case 2: {
            if (flags[0]) {
                flags[0] = false;
                flags[1] = true;
                location = 1;
            } else if (flags[2]) {
                flags[2] = false;
                flags[1] = true;
                location = 3;
            } else if (flags[4]) {
                flags[4] = false;
                flags[1] = true;
                location = 5;
            }
            break;
        }
        case 3: {
            if (flags[1]) {
                flags[1] = false;
                flags[2] = true;
                location = 2;

            } else if (flags[5]) {
                flags[5] = false;
                flags[2] = true;
                location = 6;
            }
            break;
        }
        case 4: {
            if (flags[0]) {
                flags[0] = false;
                flags[3] = true;
                location = 1;

            } else if (flags[4]) {
                flags[4] = false;
                flags[3] = true;
                location = 5;
            } else if (flags[6]) {
                flags[6] = false;
                flags[3] = true;
                location = 7;
            }
            break;
        }
        case 5: {
            if (flags[1]) {
                flags[1] = false;
                flags[4] = true;
                location = 2;

            } else if (flags[3]) {
                flags[3] = false;
                flags[4] = true;
                location = 4;

            } else if (flags[5]) {
                flags[5] = false;
                flags[4] = true;
                location = 6;
            } else if (flags[7]) {
                flags[7] = false;
                flags[4] = true;
                location = 8;
            }
            break;
        }
        case 6: {
            if (flags[2]) {
                flags[2] = false;
                flags[5] = true;
                location = 3;

            } else if (flags[4]) {
                flags[4] = false;
                flags[5] = true;
                location = 5;

            } else if (flags[8]) {
                flags[8] = false;
                flags[5] = true;
                location = 9;
            }
            break;
        }
        case 7: {
            if (flags[3]) {
                flags[3] = false;
                flags[6] = true;
                location = 4;
            } else if (flags[7]) {
                flags[7] = false;
                flags[6] = true;
                location = 8;
            }
            break;
        }
        case 8: {
            if (flags[4]) {
                flags[4] = false;
                flags[7] = true;
                location = 5;
            } else if (flags[6]) {
                flags[6] = false;
                flags[7] = true;
                location = 7;
            } else if (flags[8]) {
                flags[8] = false;
                flags[7] = true;
                location = 9;
            }
            break;
        }
        case 9: {
            if (flags[5]) {
                flags[5] = false;
                flags[8] = true;
                location = 6;
            } else if (flags[7]) {
                flags[7] = false;
                flags[8] = true;
                location = 8;
            }
            break;
        }
        }
        return location;
    }
}