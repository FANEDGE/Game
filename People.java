class People {
    protected String name = null;
    protected int time = 0;

    public People() {
        name = null;
        time = 0;
    }

    public People(String user, int myTime) {
        name = user;
        time = myTime;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }
}