package br.usp.gabrieljospin.command;

import br.usp.gabrieljospin.connection.Connection;
import br.usp.gabrieljospin.connection.ServerObject;
import br.usp.gabrieljospin.stubs.Part;
import br.usp.gabrieljospin.stubs.PartRepository;

import java.rmi.RemoteException;
import java.util.*;

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
            newPart = rep.addPart(name, description);
            for(UUID id : conn.getCurrentSubparts().keySet()){
                Optional<Part> optionalPart = findPart(id, conn);
                if(optionalPart.isPresent()){
                    conn.getRepository().addSubComponent(newPart, optionalPart.get(),
                            conn.getCurrentSubparts().get(id));
                }
            }
            System.out.println("id="+newPart.getId());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public Optional<Part> findPart(UUID partId, Connection conn) {
        return Optional.of(conn.getRepository().getPart(partId));
    }

    @Override
    public void help() {
        System.out.println("this method will add a part to a select server");
        System.out.println("to execute this method you use:");
        System.out.println("addp <Server nickname>");
    }
}
