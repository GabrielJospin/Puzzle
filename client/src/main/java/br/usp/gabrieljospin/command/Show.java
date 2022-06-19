package br.usp.gabrieljospin.command;

import br.usp.gabrieljospin.connection.Connection;
import br.usp.gabrieljospin.connection.ServerObject;
import br.usp.gabrieljospin.stubs.Part;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Show implements Command {
    List<String> args;
    String command;
    private Map<String, ServerObject> servers;

    public Show(String command, Map<String, ServerObject> servers) throws Exception {
        this.command = command;
        this.servers = servers;
        this.args = new ArrayList<>(List.of(command.split(" ")));
        String c = this.args.remove(0);
        if(!c.equals("show"))
            throw new Exception(String.format("Wrong Command, %s is not show", c));

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
            Part part = conn.getCurrentPart();
            StringBuilder sb = new StringBuilder();
            sb.append("{").append("\n");
            sb.append("\t\"id\": \"").append(part.getId()).append("\",\n");
            sb.append("\t\"Name\": \"").append(part.getName()).append("\",\n");
            sb.append("\t\"Description\": \"").append(part.getDescription()).append("\",\n");
            sb.append("\t\"subComponents\":{\n");
            for(var entry :part.getSubComponents().entrySet())
                sb.append("\t\t\"id:\"").append(entry.getKey()).append("\",\n");
            sb.append("\t}\n}");
            System.out.println(sb);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void help() {

    }
}
