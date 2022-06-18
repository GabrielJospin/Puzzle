package br.usp.gabrieljospin.stubs;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Comparator;
import java.util.Map;
import java.util.UUID;

public interface Part extends Remote{

    // Create Object public Getters
    UUID getId() throws RemoteException;
    String getName() throws RemoteException;
    String getDescription() throws RemoteException;
    Map<UUID, Integer> getSubComponents() throws RemoteException;

    //create Public Remote action Methods
    void addSupComponent(Part part)throws RemoteException;
    void addSubComponent(String name, String description, Integer quant) throws  RemoteException;

    public int compare(Part part, Part t1);
}
