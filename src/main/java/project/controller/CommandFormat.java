package project.controller;

public enum CommandFormat {


    //Register
    REGISTER("user create", new String[]{"-u", "-p", "-c", "-email", "-n", "-s"}),
    QUESTION_ANSWER("question pick", new String[]{"-q", "-a", "-c"}),

    LOGIN("user login",new String[]{"-u", "-p"}),
    LOGIN_LOGGED_IN("user login",new String[]{"-u", "-p", "--stay-logged-in"}),

    // Profile
    CHANGE_PASSWORD("profile change password",new String[]{"-o", "-n"}),

    // Edit & Create map
    DROP_ROCK("droprock",new String[]{"-x", "-y","-d"}),
    DROP_TREE("droptree",new String[]{"-x", "-y","-t"}),
    SET_TEXTURE("settexture",new String[]{"-x", "-y","-t"}),
    SET_TEXTURE_RECTANGLE("settexture rectangle",new String[]{"-x1", "-y1","-x2","-y2","-t"}),
    SET_GOVERNMENT("set government",new String[]{"-color", "-x","-y"}),
    DROP_UNIT("dropunit",new String[]{"-x", "-y","-t","-c"}),
    DROP_BUILDING("dropbuilding",new String[]{"-x", "-y","-y"}),

    // Map
    SHOW_MAP("show map",new String[]{"-x", "-y"}),
    SHOW_DETAILS("show details",new String[]{"-x", "-y"}),

    // Game
    SELECT_BUILDING("select building",new String[]{"-x", "-y"}),
    SELECT_UNIT("select unit",new String[]{"-x", "-y","-t"}),
    MOVE_UNIT("move unit to",new String[]{"-x", "-y"}),
    PATROL_UNIT("patrol unit",new String[]{"-x1", "-y1","-x2","-y2"}),
    STOP_UNIT("stop unit",new String[]{"-x", "-y"}),
    ATTACK_ENEMY("attack enemy",new String[]{"-x", "-y"}),
    ATTACK_LAUNCH("attack launch",new String[]{"-x", "-y"}),
    DIG_TUNNEL("dig tunnel",new String[]{"-x", "-y"}),
    CLEAR_BLOCK("clear",new String[]{"-x", "-y"}),

    // Shop
    BUY("buy",new String[]{"-i", "-a"}),
    SELL("sell",new String[]{"-i", "-a"}),


    // Trade
    TRADE_REQUEST("trade",new String[]{"-u", "-t","-a","-p","-m"}),
    TRADE_ACCEPT("trade accept",new String[]{"-i", "-m"});

    private final String start;
    private final String[] flags;

    CommandFormat(String start, String[] flags) {
        this.start = start;
        this.flags = flags;
    }

    public String getStart(){
        return start;
    }

    public String[] getFlags(){
        return flags;
    }
}
