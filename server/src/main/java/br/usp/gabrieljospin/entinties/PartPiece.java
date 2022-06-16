package br.usp.gabrieljospin.entinties;

import br.usp.gabrieljospin.stubs.Part;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PartPiece implements Part {

    private final UUID id;
    private String name;
    private String description;
    private Map<UUID, Integer> subComponents;

    PartPiece(String name, String description){
        this.id = UUID.fromString(name);
        this.name = name;
        this.description = description;
        this.subComponents = new HashMap<>();
    }

    @Override
    public UUID getId() throws RemoteException {
        return this.id;
    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    @Override
    public String getDescription() throws RemoteException {
        return this.description;
    }

    @Override
    public Map<UUID, Integer> getSubComponents() throws RemoteException {
        return this.subComponents;
    }

    @Override
    public void addSupComponent(Part part) throws RemoteException {
        subComponents.put(part.getId(), 1);
    }

    @Override
    public void addSubComponent(String name, String description, Integer quant) throws RemoteException {
        Part part = new PartPiece(name, description);
        subComponents.put(part.getId(), quant );
    }

    @Override
    public int compare(Part part, Part t1) {
        try {
            return part.getId().compareTo(t1.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
