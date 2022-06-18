package br.usp.gabrieljospin.command;

import br.usp.gabrieljospin.connection.Connection;
import br.usp.gabrieljospin.connection.ServerObject;
import br.usp.gabrieljospin.stubs.Part;
import br.usp.gabrieljospin.stubs.PartRepository;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Addp implements Command {
    List<String> args;
    String command;
    private Map<String, ServerObject> servers;

    public Addp(String command, Map<String, ServerObject> servers) throws Exception {
        this.command = command;
        this.servers = servers;
        this.args = new ArrayList<>(List.of(command.split(" ")));
        String c = this.args.remove(0);
        if(!c.equals("addp"))
            throw new Exception(String.format("Wrong Command, %s is not addp", c));

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
        Scanner sc = new Scanner(System.in);
        System.out.println("creat a new part");
        System.out.print("Name of part: ");
        String name = sc.nextLine();
        System.out.print("Description of part: ");
        String description = sc.nextLine();

        Part newPart = null;
        try{
            if (name.isEmpty() || description.isEmpty()) {
                throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException e){
            System.err.println("name or description can't be null");
        }

        try {
            String serverName = servers.get(args.get(0)).getConnName();
            Connection conn = Connection.getInstance(serverName);
            PartRepository rep = conn.getRepository();
            System.out.println("rep: "+rep);
            newPart = rep.addPart(name, description);
            System.out.println("id="+newPart.getId());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void help() {

    }
}
