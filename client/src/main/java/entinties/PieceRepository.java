package entinties;

import stubs.Part;
import stubs.PartRepository;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PieceRepository implements PartRepository {

    List<Part> partList;

    public PieceRepository() {
        this.partList = new ArrayList<>();
    }

    @Override
    public List<Part> getPartList() throws RemoteException {
        return this.partList;
    }

    @Override
    public void addPart(Part part) throws RemoteException {
        this.partList.add(part);
    }

    @Override
    public Part addPart(String name, String description) throws RemoteException {
        Part part = new PartPiece(name, description);
        this.partList.add(part);
        return part;
    }

    @Override
    public void addSubComponent(Part part, Part subpart, int quant) throws RemoteException {
        int pos = this.partList.indexOf(part);
        this.partList.get(pos).addSubComponent(subpart.getName(), subpart.getDescription(), quant);
    }

    @Override
    public Part getPart(UUID id) {
        return null;
    }
}
