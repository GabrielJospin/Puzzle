package br.usp.gabrieljospin.command;

import br.usp.gabrieljospin.connection.Connection;
import br.usp.gabrieljospin.connection.ServerObject;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AddSub implements Command {
    List<String> args;
    String command;
    private Map<String, ServerObject> servers;

    public AddSub(String command, Map<String, ServerObject> servers) throws Exception {
        this.command = command;
        this.servers = servers;
        this.args = new ArrayList<>(List.of(command.split(" ")));
        String c = this.args.remove(0);
        if(!c.equals("addSub"))
            throw new Exception(String.format("Wrong Command, %s is not addSub", c));

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
            String servername = servers.get(args.get(0)).getConnName();
            Integer quant = Integer.getInteger(args.get(1));
            Connection conn = Connection.getInstance(servername);

            if(conn.getCurrentPart() == null){
                System.out.println("Current part in this server is null");
                return;
            }

            UUID id = conn.getCurrentPart().getId();
            conn.getCurrentSubparts().put(id, conn.getCurrentSubparts().getOrDefault(id,0) + quant);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void help() {

    }
}
