package br.usp.gabrieljospin.command;

import br.usp.gabrieljospin.connection.Connection;
import br.usp.gabrieljospin.connection.ServerObject;
import br.usp.gabrieljospin.stubs.Part;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Get implements Command {
    List<String> args;
    String command;
    private Map<String, ServerObject> servers;

    public Get(String command, Map<String, ServerObject> servers) throws Exception {
        this.command = command;
        this.servers = servers;
        this.args = new ArrayList<>(List.of(command.split(" ")));
        String c = this.args.remove(0);
        if(!c.equals("get"))
            throw new Exception(String.format("Wrong Command, %s is not get", c));

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
            String id = args.get(1);
            Connection conn = Connection.getInstance(serverName);
            Part part = conn.getRepository().getPart(UUID.fromString(id));
            conn.setCurrentPart(part);
            System.out.println("part in current part");

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void help() {
        System.out.println("Get a specific Part from a Server by ID and save in \"Client Memory\"");
        System.out.println("to execute this method you use:");
        System.out.println("get <Server nickname> <Part ID>");
    }
}
