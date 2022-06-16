package stubs;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface PartRepository extends Remote {

    List<Part> getPartList() throws RemoteException;
    void addPart(Part part) throws RemoteException;
    Part addPart(String name, String description) throws RemoteException;
    void addSubComponent(Part part, Part subpart, int quant) throws RemoteException;
    Part getPart(UUID id);

}
