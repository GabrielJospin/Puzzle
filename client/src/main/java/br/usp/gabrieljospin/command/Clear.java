package br.usp.gabrieljospin.command;

import br.usp.gabrieljospin.connection.ServerObject;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Clear implements Command{

    List<String> args;
    String command;
    private Map<String, ServerObject> servers;

    public Clear(String command, Map<String, ServerObject> servers) throws Exception {
        this.command = command;
        this.servers = servers;
        this.args = new ArrayList<>(List.of(command.split(" ")));
        String c = this.args.remove(0);
        if(!c.equals("clear"))
            throw new Exception(String.format("Wrong Command, %s is not clear", c));

        if(args.size() > 0 && args.get(0).equals("--help")){
            help();
        }
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public void execute() throws RemoteException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void help() {

    }
}
