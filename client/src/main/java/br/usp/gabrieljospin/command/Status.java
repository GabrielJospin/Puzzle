package br.usp.gabrieljospin.command;

import br.usp.gabrieljospin.connection.ServerObject;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Status implements Command{

    private List<String> args;
    private String command;
    private Map<String, ServerObject> servers;

    public Status(String command, Map<String, ServerObject> servers) throws Exception {
        this.command = command;
        this.servers = servers;
        this.args = new ArrayList<>(List.of(command.split(" ")));
        String c = this.args.remove(0);
        if(!c.equals("status"))
            throw new Exception(String.format("Wrong Command, %s is not status", c));

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
        System.out.printf("%d status connected\n", servers.size());
        System.out.println("|nickname\t|Port\t|Fullname\t|Status\n");
        for(var entry: servers.entrySet()){
            StringBuilder sb = new StringBuilder();
            sb.append("|").append(entry.getKey()).append("\t");
            sb.append("|").append(entry.getValue().getPort()).append("\t");
            sb.append("|").append(entry.getValue().getConnName()).append("\t");

            if(entry.getValue().getStatus().equals(ServerObject.Status.NEW))
                sb.append("\u001b[33m").append("NEW").append("\u001b[0m");
            if(entry.getValue().getStatus().equals(ServerObject.Status.CONNECT))
                sb.append("\u001b[32m").append("CONNECT").append("\u001b[0m");
            if(entry.getValue().getStatus().equals(ServerObject.Status.NOT_FOUND))
                sb.append("\u001b[31m").append("NOT FOUND").append("\u001b[0m");
            if(entry.getValue().getStatus().equals(ServerObject.Status.OFF))
                sb.append("\u001b[31m").append("OFF").append("\u001b[0m");
            System.out.println(sb);
        }
    }

    @Override
    public void help() {

    }
}

