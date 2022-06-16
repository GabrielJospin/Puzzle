import entinties.PieceRepository;
import org.json.JSONObject;
import stubs.PartRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    private static Registry registry;

    public static void main(String[] args) {
        try {
            String jsonPath = "../resources/" + args[0]+".json";
            JSONObject jsonObject = new JSONObject(
                                        new BufferedReader(
                                            new FileReader(jsonPath)).toString());

            int port = jsonObject.getInt("port");
            LocateRegistry.createRegistry(port);
            registry = LocateRegistry.getRegistry(port);
            PartRepository obj = new PieceRepository();
            registry.bind(jsonObject.getString("name"), obj);
            System.out.println("Server ready");
        }catch (Exception e){
            System.err.printf("{\nSystem answer:500\nMessage: %s\n}", e.getMessage());
        }
    }
}
