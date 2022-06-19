package br.usp.gabrieljospin.command;

import br.usp.gabrieljospin.connection.Connection;
import br.usp.gabrieljospin.connection.ServerObject;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClearList implements Command {
    List<String> args;
    String command;
    private Map<String, ServerObject> servers;

    public ClearList(String command, Map<String, ServerObject> servers) throws Exception {
        this.command = command;
        this.servers = servers;
        this.args = new ArrayList<>(List.of(command.split(" ")));
        String c = this.args.remove(0);
        if(!c.equals("clearList"))
            throw new Exception(String.format("Wrong Command, %s is not clearList", c));

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
        try {
            String serverName = servers.get(args.get(0)).getConnName();
            Connection conn = Connection.getInstance(serverName);
            conn.getCurrentSubparts().clear();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void help() {

    }
}
