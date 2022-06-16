package stubs;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.UUID;

public interface PartRepository extends Remote {

    Map<UUID, Part> getPartList() throws RemoteException;
    void addPart(Part part) throws RemoteException;
    Part addPart(String name, String description) throws RemoteException;
    void addSubComponent(Part part, Part subpart, int quant) throws RemoteException;
    Part getPart(UUID id);

}
