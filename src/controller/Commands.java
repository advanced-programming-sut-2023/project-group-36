package controller;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public enum Commands {

    //REGISTER & LOGIN
    REGISTER("user create -u (?<username>[^\n]+) -p (?<password>[^\n]+) (?<password confirmation>[^\n]+) –email (?<email>[^\n]+) (-s <slogan>[^\n]+)?"),
    RANDOM_QUESTION("question pick -q (<question-number>[\\d]+) -a <answer> -c (?<answerConfirm>[^\n]+)"),
    RANDOM_PASSWORD("user create -u (?<username>[^\n]+) -n (?<nickname>[^\n]+) -p random -e (?<email>[^\n]+)"),
    RANDOM_SLOGAN("user create -u (?<username>[^\n]+) -p random -s random -n (?<nickname>[^\n]+)"),
    LOGIN("user login -u (?<username>[^\n]+) -p (?<password>[^\n]+)"),
    LOGIN_LOGGEDIn("user login -u (?<username>[^\n]+) -p (?<password>[^\n]+)--stay-logged-in"),
    FORGET_PASSWORD("forgot my password"),
    LOGOUT("user logout"),

    //PROFILE
    CHANGE_USERNAME("profile change -u (?<username>[^\n]+)"),
    CHANGE_NICKNAME("profile change -n (?<nickname>[^\n]+)"),
    CHANGE_PASSWORD("profile change password -o (?<old-password> -n <new-password>\n"),

    //Map menu
    SHOW_MAP("show map -x (?<x>\\d+) -y (?<y>\\d+)"),
    MAP_TRANSFORMATION("map (?<dir1>\\S+ (\\d)+)(?<dir2> \\S+ (\\d)+)?(?<dir3> \\S+ (\\d)+)?(?<dir4> \\S+ (\\d)+)?"),
    SHOW_DETAILS("show details -x (?<x>\\d+) -y (?<y>\\d+)"),
    EXIT("exit"),

    //Game menu
    SHOW_POPULARITY_FACTORS("show popularity factors"),
    SHOW_POPULARITY("show popularity"),
    SHOW_FOOD("show food list"),
    FOOD_RATE_SET("food rate -r (?<rate-number>\\d+)"),
    FOOD_RATE_SHOW("food rate show"),
    TAX_RATE_SET("tax rate -r (?<rate-number>\\d+)"),
    TAX_RATE_SHOW("tax rate show"),
    FEAR_RATE_SET("fear rate -r (?<rate-number>\\d+)"),
    DROP_BUILDING("dropbuilding -x (?<x>\\d+) -y (?<y>\\d+) -type (<type>[^\n]+)"),
    SELECT_BUILDING("select building -x (?<x>\\d+) -y (?<y>\\d+)"),
    CREATE_UNIT("createunit -t (?<type>.+) -c (?<count>\\d+)"),
    REPAIR("repair"),
    SELECT_UNIT("select unit -x (?<x>\\d+) -y (?<y>\\d+)"),
    MOVE_UNIT("move unit to -x (?<x>) -y (?<y>)"),
    PATROL_UNIT("patrol unit -x1 (?<x1>\\d+) -y1 (?<y1>\\d+) -x2 (?<x2>\\d+) -y2 (?<y2>\\d+)"),
    STOP_UNIT("stop unit -x (?<x>) -y (?<y>)"),
    SET_CONDITION("set -x (?<x>\\d+) -y (?<y>\\d+) -s (?<condition>.+)"),
    ATTACK_ENEMY("attack -e [enemy’s x] [enemy’s y]"),
    ATTACK_LAUNCH("attack -x (?<x>\\d+) -y (?<y>\\d+)"),
    POUR_OIL("pour oil -d (?<direction>\\S+)"),
    DIG_TUNNEL("dig tunnel -x (?<x>\\d+) -y (?<y>\\d+)"),
    DISBAND_UNIT("disband unit"),
    SET_TEXTURE("settexture -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>\\d+)"),
    SET_TEXTURE_RECTANGLE("settexture -x1 (?<x1>\\d+) -y1 (?<y1>\\d+) -x2 (?<x2>\\d+) -y2 (?<y2>\\d+) -t [type]"),
    CLEAR("clear -x (?<x>\\d+) -y (?<y>\\d+)"),
    DROP_ROCK("droprock -x (?<x>\\d+) -y (?<y>\\d+) -d (?<direction>[^\n])"),
    DROP_TREE("droptree -x (?<x>\\d+) -y (?<y>\\d+) -t [type]"),
    DROP_UNIT("dropbuilding -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>[^\n]+) -c (?<count>\\d+)"),

    // Trade menu
    TRADE_REQUEST("trade -t (?<resourceType>[^\n]+) -a (?<resourceAmount>[^\n]+) -p (?<price>\\d+) -m (?<message>[^\n]+)"),
    TRADE_LIST("trade list"),
    TRADE_ACCEPT("trade accept -i (?<id>\\d+) -m (?<message>[^\n]+)"),
    TRADE_HISTORY("trade history"),
    SHOW_PRICE_LIST("show price list"),
    BUY("buy -i (?<item’s name>[^\n]+) -a (?<item’s amount>\\d+)"),
    SELL("sell -i (?<item’s name>[^\n]+) -a (?<item’s amount>\\d+)");

    private final String regex;

    Commands(String regex){
        this.regex = regex;
    }

    public String getRegex(){
        return this.regex;
    }
}
