package br.usp.gabrieljospin.command;

import br.usp.gabrieljospin.connection.Connection;
import br.usp.gabrieljospin.connection.ServerObject;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Mount implements  Command{

    private List<String> args;
    private String command;
    private Map<String, ServerObject> servers;

    public Mount(String command, Map<String, ServerObject> servers) throws Exception {
        this.command = command;
        this.servers = servers;
        this.args = new ArrayList<>(List.of(command.split(" ")));
        String c = this.args.remove(0);
        if(!c.equals("mount"))
            throw new Exception(String.format("Wrong Command, %s is not mount", c));

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
        ServerObject newServer = new ServerObject(args.get(1), Integer.parseInt(args.get(0)));
        String serverKey = args.get(2);
        if(serverKey.toLowerCase().equals("collor")){
            System.out.println("Your server is down, I'm not sorry, it's your fault");
            return;
        }
        try {
            Connection conn = Connection.getInstance(newServer.getConnName());
            newServer.setStatus(true);
        } catch (Exception e) {
            newServer.setStatus(false);
        }
        servers.put(serverKey, newServer);

    }

    @Override
    public void help() {
        System.out.println("this method will mount a server in your sys");
        System.out.println("to execute this method you use:");
        System.out.println("mount <port> <Server Name> <Server nickname>");
        System.out.println("The server nickname can be any name you want");
        System.out.println("Except \"Collor\", for obviously reasons");
    }
}
