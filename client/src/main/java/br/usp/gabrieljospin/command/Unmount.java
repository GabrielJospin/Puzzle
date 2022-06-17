package br.usp.gabrieljospin.command;

import br.usp.gabrieljospin.connection.ServerObject;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Unmount implements Command{

    List<String> args;
    String command;
    private Map<String, ServerObject> servers;

    public Unmount(String command, Map<String, ServerObject> servers) throws Exception {
        this.command = command;
        this.servers = servers;
        this.args = new ArrayList<>(List.of(command.split(" ")));
        String c = this.args.remove(0);
        if(!c.equals("unmount"))
            throw new Exception(String.format("Wrong Command, %s is not unmount", c));

        if(args.get(0).equals("--help")){
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
        if(args.size() != 1) {
            System.err.println("Wrong args for command ummout");
            return;
        }
        String serverNick = args.get(0);
        Optional<ServerObject> o = Optional.of(servers.get(serverNick));
        ServerObject server = o.get();
        servers.remove(serverNick,server);
    }

    @Override
    public void help() {
        System.out.println("this method will remove a server that already mount");
        System.out.println("to execute this method you use:");
        System.out.println("unmount  <Server nickname>");
    }
}
