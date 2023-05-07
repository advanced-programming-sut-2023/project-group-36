package project.controller;

public enum CommandFormat {


    //Register

    REGISTER("user create", new String[]{"-u", "-p", "-c", "-email", "-n", "-s"}),
    QUESTION_ANSWER("question pick", new String[]{"-q", "-a", "-c"}),
    LOGIN("user login -u (?<username>[^\n]+) -p (?<password>[^\n]+)"),
    LOGIN_LOGGED_IN("user login -u (?<username>[^\n]+) -p (?<password>[^\n]+)--stay-logged-in"),
    CHANGE_USERNAME("profile change -u (?<username>[^\n]*)"),
    CHANGE_NICKNAME("profile change -n (?<nickname>[^\n]*)"),
    CHANGE_PASSWORD("profile change password -o (?<old-password>[^\n]*) -n (?<new-password>[^\n]*)"),
    CHANGE_EMAIL("profile change -e (?<email>[^\n]*)"),
    EMAIL("(?<part1>[^\\n]*)@(?<part2>[^\\n]*)\\.(?<part3>[^\\n]*)"),
    CHANGE_SLOGAN("profile change slogan -s (?<slogan>[^\n]*)"),
    DROP_ROCK("droprock -x (?<x>\\d+) -y (?<y>\\d+) -d (?<direction>[^\n]+)"),
    DROP_TREE("droptree -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>[^\n]+)"),
    SET_TEXTURE("settexture -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>\\d+)"),
    SET_TEXTURE_RECTANGLE("settexture -x1 (?<x1>\\d+) -y1 (?<y1>\\d+) -x2 (?<x2>\\d+) -y2 (?<y2>\\d+) -t [type]"),
    //EditMap Menu
    SET_GOVERNMENT("set government -color (?<color>\\w+) -x (?<x>\\d+) -y (?<y>\\d+)"),
    DROP_UNIT("dropunit -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>[^\n]+) -c (?<count>\\d+)"),
    DROP_BUILDING("dropbuilding -x (?<x>\\d+) -y (?<y>\\d+) -type (<type>[^\n]+)"),
    SHOW_MAP("show map -x (?<x>\\d+) -y (?<y>\\d+)"),
    MAP_TRANSFORMATION("map (?<dir1>[a-z]+ [0-9]*) (?<dir2>[a-z]+ [0-9]*)"),
    SHOW_DETAILS("show details -x (?<x>\\d+) -y (?<y>\\d+)"),
    SELECT_BUILDING("select building -x (?<x>\\d+) -y (?<y>\\d+)"),
    CREATE_UNIT("createunit -t (?<type>.+) -c (?<count>\\d+)"),
    SELECT_UNIT("select unit -x (?<x>\\d+) -y (?<y>\\d+)"),
    MOVE_UNIT("move unit to -x (?<x>\\d+) -y (?<y>\\d+)"),
    PATROL_UNIT("patrol unit -x1 (?<x1>\\d+) -y1 (?<y1>\\d+) -x2 (?<x2>\\d+) -y2 (?<y2>\\d+)"),
    STOP_UNIT("stop unit -x (?<x>\\d+) -y (?<y>\\d+)"),
    SET_CONDITION("set -x (?<x>\\d+) -y (?<y>\\d+) -s (?<condition>.+)"),
    ATTACK_ENEMY("attack -e (?<enemy’s x>\\d+) (?<enemy’s y>\\d+)"),
    ATTACK_LAUNCH("attack -x (?<x>\\d+) -y (?<y>\\d+)"),
    DIG_TUNNEL("dig tunnel -x (?<x>\\d+) -y (?<y>\\d+)"),
    CLEAR_BLOCK("clear -x (?<x>\\d+) -y (?<y>\\d+)"),
    TRADE_REQUEST("trade -u (?<username>[^\n]+) -t (?<resourceType>[^\n]+) -a (?<resourceAmount>\\d+) -p (?<price>\\d+) -m (?<message>[^\n]+)"),
    BUY("buy -i (?<item’s name>[^\n]+) -a (?<item’s amount>\\d+)"),
    SELL("sell -i (?<item’s name>[^\n]+) -a (?<item’s amount>\\d+)"),
    TRADE_ACCEPT("trade accept -i (?<id>\\d+) -m (?<message>[^\n]+)");
    private final String start;
    private final String[] flags;

    CommandFormat(String start, String[] flags) {
        this.start = start;
        this.flags = flags;
    }

    public CommandFormat getCommandFormat(){
        return this;
    }

    public String getStart(){
        return start;
    }

    public String[] getFlags(){
        return flags;
    }
}
