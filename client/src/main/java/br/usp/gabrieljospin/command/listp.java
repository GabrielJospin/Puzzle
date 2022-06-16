package br.usp.gabrieljospin.command;

import java.util.ArrayList;
import java.util.List;

public class listp implements command{

    private List<String> args;
    private String command;

    public listp(String command) {
        this.command = command;
        this.args = new ArrayList<>(List.of(command.split(" ")));
        this.args.remove(0);
    }

    @Override
    public List<String> getArgs() {
        return this.args;
    }

    @Override
    public String getCommand() {
        return this.command;
    }

    @Override
    public void execute() {
        //TODO Execute
    }
}
