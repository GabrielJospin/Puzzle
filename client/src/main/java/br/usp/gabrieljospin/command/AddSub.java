package br.usp.gabrieljospin.command;

import br.usp.gabrieljospin.connection.Connection;
import br.usp.gabrieljospin.connection.ServerObject;
import br.usp.gabrieljospin.stubs.Part;

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
            String servernameMaster = servers.get(args.get(0)).getConnName();
            UUID idMaster = UUID.fromString(args.get(1));
            String servernameSub = servers.get(args.get(2)).getConnName();
            UUID idSub = UUID.fromString(args.get(3));

            System.out.println("["+servernameMaster+", "+servernameSub+", "+idMaster+", "+ idSub+ "]");


            Connection conn = Connection.getInstance(servernameSub);
            Part partSub = conn.getRepository().getPart(idSub);

            conn.updateRepository(servernameMaster);

            conn.getRepository().getPart(idMaster).addSupComponent(partSub);

            System.out.println("Out:" +conn.getRepository().getPart(idMaster).getSubComponents());


        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void help() {

    }
}
