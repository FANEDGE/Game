class MyToString {
    // 时间格式化为hh:mm:ss
    public final static String timeToString(int myTime) {
        String str = null;
        int hour = (int) (myTime / (60 * 60));
        int minute = (int) ((myTime / 60) % 60);
        int second = (int) (myTime % 60);
        if (hour < 10) {
            str = "0" + hour;
        } else {
            str = "" + hour;
        }
        str+=":";
        if (minute < 10) {
            str += "0" + minute;
        } else {
            str += "" + minute;
        }
        str+=":";
        if (second < 10) {
            str += "0" + second;
        } else {
            str += "" + second;
        }
        return str;
    }
}