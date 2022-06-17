package br.usp.gabrieljospin.command;

import br.usp.gabrieljospin.connection.Connection;
import br.usp.gabrieljospin.stubs.Part;

import java.rmi.RemoteException;
import java.util.*;

public class listp implements command{

    private List<String> args;
    private String command;
    private Connection conn;

    public listp(String command) throws Exception {
        this.command = command;
        this.args = new ArrayList<>(List.of(command.split(" ")));
        String c = this.args.remove(0);
        if(!c.equals("listp"))
            throw new Exception(String.format("Wrong Command, %s is not listp", c));
        this.conn = Connection.getInstance(args.get(0));
    }

    @Override
    public List<String> getArgs() {
        return this.args;
    }

    @Override
    public String getCommand() {
        return this.command;
    }

    @Override
    public void execute() throws RemoteException {
        Optional<Map<UUID, Part>> optionalMap = Optional.of(conn.getRepository().getPartList());
        Map<UUID, Part> mapPart = optionalMap.get();
        StringBuilder sb = new StringBuilder();
        for(var entry: mapPart.entrySet())
            sb.append(entry.getKey()).append("\t").append(entry.getValue().getName()).append("\n");
        System.out.println(sb);

    }
}
