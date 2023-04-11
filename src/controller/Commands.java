package controller;

public enum Commands {
    //REGISTER & LOGIN
    REGISTER("user create -u (?<username>[^\n]+) -p (?<password>[^\n]+) (?<password confirmation>[^\n]+) –email (?<email>[^\n]+) (-s <slogan>[^\n]+)."),
    RANDOM_QUESTION("question pick -q (<question-number>[\\d]+) -a <answer> -c (?<answerconfirm>[^\n]+)"),
    RANDOM_PASSWORD("user create -u (?<username>[^\n]+) -n (?<nickname>[^\n]+) -p random -e (?<email>[^\n]+)"),
    RANDOM_SLOGAN("user create -u (?<username>[^\n]+) -p random -s random -n (?<nickname>[^\n]+)"),
    LOGIN("user login -u (?<username>[^\n]+) -p (?<password>[^\n]+)"),
    LOGIN_LOGGED("user login -u (?<username>[^\n]+) -p (?<password>[^\n]+)--stay-logged-in"),
    FORGET_PASSWORD("forgot my password"),
    LOGOUT("user logout"),
    //PROFILE
    CHANGE_USERNAME("profile change -u (?<username>[^\n]+)"),
    CHANGE_NICKNAME("profile change -n (?<nickname>[^\n]+"),
    CHANGE_PASSWORD("profile change password -o (?<old-password> -n <new-password>\n");
    String regex;
    Commands(String regex) {
        this.regex=regex;
    }
}
