package br.usp.gabrieljospin.entinties;

import br.usp.gabrieljospin.stubs.Part;
import br.usp.gabrieljospin.stubs.PartRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class PieceRepository extends UnicastRemoteObject implements PartRepository{

    Map<UUID, Part> partList;

    public PieceRepository() throws RemoteException {
        super();
        this.partList = new HashMap<>();
    }

    @Override
    public Map<UUID, Part> getPartList() throws RemoteException {
        return this.partList;
    }

    @Override
    public void addPart(Part part) throws RemoteException {
        this.partList.put(part.getId(),part);
    }

    @Override
    public Part addPart(String name, String description) throws RemoteException {
        Part part = new PartPiece(name, description);
        this.partList.put(part.getId(), part);
        return part;
    }

    @Override
    public void addSubComponent(Part part, Part subpart, int quant) throws RemoteException {
        this.partList.get(part.getId()).addSubComponent(subpart.getName(), subpart.getDescription(), quant);
    }

    @Override
    public Part getPart(UUID id) {
        return this.partList.get(id);
    }
}
