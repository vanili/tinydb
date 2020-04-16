package org.my.tinydb;

public class Statement {
    private StatementType type;
    private String command;

    public Statement() {
    }

    public Statement(StatementType type, String command) {
        this.type = type;
        this.command = command;
    }

    public StatementType getType() {
        return type;
    }

    public void setType(StatementType type) {
        this.type = type;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
