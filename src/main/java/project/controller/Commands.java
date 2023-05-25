package project.controller;

public enum Commands {

    //REGISTER & LOGIN
    REGISTER("user create -u (?<username>[^\n]*) -p (?<password>[^\n]*) -c (?<passwordConfirmation>[^\n]*) -email (?<email>[^\n]*) -n (?<nickname>[^\n]*)(-s (?<slogan>[^\n]*))?"),
    QUESTION_ANSWER("question pick -q (?<questionNumber>[\\d]+) -a (?<answer>[^\n]+) -c (?<answerConfirm>[^\n]+)\\s*"),

    LOGIN("user login -u (?<username>[^\n]+) -p (?<password>[^\n]+)\\s*"),
    LOGIN_LOGGED_IN("user login -u (?<username>[^\n]+) -p (?<password>[^\n]+) --stay-logged-in"),
    FORGET_PASSWORD("forgot my password"),

    //PROFILE
    CHANGE_USERNAME("profile change -u (?<username>[^\n]*)"),
    CHANGE_NICKNAME("profile change -n (?<nickname>[^\n]*)"),
    CHANGE_PASSWORD("profile change password -o (?<oldPassword>[^\n]*) -n (?<newPassword>[^\n]*)"),
    CHANGE_EMAIL("profile change -e (?<email>[^\n]*)"),
    EMAIL("(?<part1>[^\\n]*)@(?<part2>[^\\n]*)\\.(?<part3>[^\\n]*)"),
    CHANGE_SLOGAN("profile change slogan -s (?<slogan>[^\n]*)"),
    REMOVE_SLOGAN("profile remove slogan"),
    DISPLAY_HIGH_SCORE("profile display highScore"),
    DISPLAY_RANK("profile display rank"),
    DISPLAY_SLOGAN("profile display slogan"),
    DISPLAY("profile display"),

    //Map menu
    SHOW_MAP("show map -x (?<x>\\d+) -y (?<y>\\d+)"),
    MAP_TRANSFORMATION("map (?<dir1>[a-z]+) (?<a>[0-9\\-]*) (?<dir2>[a-z]+) (?<b>[0-9\\-]*)"),
                       // "map (?<dir1>[a-z]+ [0-9]*) (?<dir2>[a-z]+ [0-9]*)"
    SHOW_DETAILS("show details -x (?<x>\\d+) -y (?<y>\\d+)"),
    EXIT("exit"),

    //Main menu
    START_GAME("start new game -n (?<usersNumber>\\d+)"),
    OPEN_GAME("open game"),
    PROFILE_MENU("profile menu"),
    LOGOUT("user logout"),
    CREATE_MAP("create map"),


    //CreateNewGame Menu
    ADD_USER("add user (?<username>[^\n]+)"),
    REMOVE_USER("remove user (?<username>[^\n]+)"),
    CHOOSE_MAP("choose map (?<mapName>[^\n]+)"),

    MAP_PREPARATION("map preparation"),

    //CreateNewMap Menu

    SET_MAP_NAME("set map name (?<name>\\S+)"),
    DROP_ROCK("droprock -x (?<x>\\d+) -y (?<y>\\d+) -d (?<direction>[^\n]+)"),
    DROP_TREE("droptree -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>[^\n]+)"),
    SET_TEXTURE("settexture -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>[^\n]+)"),
    SET_TEXTURE_RECTANGLE("settexture rectangle -x1 (?<x1>\\d+) -y1 (?<y1>\\d+) -x2 (?<x2>\\d+) -y2 (?<y2>\\d+) -t (?<type>[^\n]+)"),

    //EditMap Menu
    SET_GOVERNMENT("set government -color (?<color>\\w+) -x (?<x>\\d+) -y (?<y>\\d+)"),
    DROP_UNIT("dropunit -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>[^\n]+) -c (?<count>\\d+)"),
    DROP_BUILDING("drop building -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>[^\n]+)"),


    //Game menu
    SHOW_POPULARITY_FACTORS("show popularity factors"),
    SHOW_POPULARITY("show popularity"),
    SHOW_FOOD_LIST("show food list"),
    FOOD_RATE_SET("food rate -r (?<rateNumber>[-]?\\d+)"),
    FOOD_RATE_SHOW("food rate show"),
    TAX_RATE_SET("tax rate -r (?<rateNumber>[-]?\\d+)"),
    TAX_RATE_SHOW("tax rate show"),
    FEAR_RATE_SET("fear rate -r (?<rateNumber>[-]?\\d+)"),
    FEAR_RATE_SHOW("fear rate show"),

    SELECT_BUILDING("select building -x (?<x>\\d+) -y (?<y>\\d+)"),
    UNSELECT_BUILDING("unselect building"),
    CREATE_UNIT("createunit -t (?<type>.+) -c (?<count>\\d+)"),
    REPAIR("repair"),
    SELECT_UNIT("select unit -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>[^\n]+)"),
    UNSELECT_UNIT("unselect unit"),
    MOVE_UNIT("move unit to -x (?<x>\\d+) -y (?<y>\\d+)"),
    PATROL_UNIT("patrol unit -x1 (?<x1>\\d+) -y1 (?<y1>\\d+) -x2 (?<x2>\\d+) -y2 (?<y2>\\d+)"),
    STOP_UNIT("stop unit -x (?<x>\\d+) -y (?<y>\\d+)"),
    SET_CONDITION("set -x (?<x>\\d+) -y (?<y>\\d+) -s (?<condition>.+)"),
    ATTACK_ENEMY("attack -e (?<x>\\d+) (?<y>\\d+)"),
    ATTACK_LAUNCH("attack -x (?<x>\\d+) -y (?<y>\\d+)"),
    POUR_OIL("pour oil -d (?<direction>\\S+)"),
    DIG_TUNNEL("dig tunnel -x (?<x>\\d+) -y (?<y>\\d+)"),
    DISBAND_UNIT("disband unit"),
    CLEAR_BLOCK("clear -x (?<x>\\d+) -y (?<y>\\d+)"),


    // Trade menu
    TRADE_REQUEST("trade -u (?<username>[^\n]+) -t (?<resourceType>[^\n]+) -a (?<resourceAmount>\\d+) -p (?<price>\\d+) -m (?<message>[^\n]+)"),
    TRADE_LIST("trade list"),
    TRADE_ACCEPT("trade accept -i (?<id>\\d+) -m (?<message>[^\n]+)"),
    TRADE_HISTORY("trade history"),

    // Shop menu
    SHOW_PRICE_LIST("show price list"),
    BUY("buy -i (?<itemName>[^\n]+) -a (?<itemAmount>[-]?\\d+)"),
    SELL("sell -i (?<itemName>[^\n]+) -a (?<itemAmount>[-]?\\d+)"),
    QUIT_GAME("quit");


    private final String regex;

    Commands(String regex){
        this.regex = regex;
    }

    public String getRegex(){
        return this.regex;
    }
}
