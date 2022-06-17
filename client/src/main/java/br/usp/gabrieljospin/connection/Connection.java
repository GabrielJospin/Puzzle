package br.usp.gabrieljospin.connection;

import br.usp.gabrieljospin.stubs.Part;
import br.usp.gabrieljospin.stubs.PartRepository;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.UUID;

public class Connection {
    private PartRepository repository;
    private Part currentPart;
    private Map<UUID, Integer> currentSubparts;
    private String serverName;
    private static Connection instance;

    private Connection(){
        instance = this;
        this.repository = null;
        this.currentPart = null;
    }

    public void updateRepository(String serverName) throws Exception{
        this.serverName = serverName;
        this.repository = (PartRepository) Naming.lookup(serverName);
    }

    public static synchronized Connection getInstance(String serverName) throws Exception {
        if (instance == null)
            instance = new Connection();
        instance.updateRepository(serverName);

        return instance;
    }

    public PartRepository getRepository() {
        return repository;
    }

    public Part getCurrentPart() {
        return currentPart;
    }

    public Map<UUID, Integer> getCurrentSubparts() {
        return currentSubparts;
    }

    public void setCurrentPart(Part currentPart) {
        this.currentPart = currentPart;
    }
}
