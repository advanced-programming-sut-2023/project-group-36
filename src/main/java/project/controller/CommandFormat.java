package project.controller;

public enum CommandFormat {


    //Register

    REGISTER("user create", new String[]{"-u", "-p", "-c", "-email", "-n", "-s"}),
    QUESTION_ANSWER("question pick", new String[]{"-q", "-a", "-c"});








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
