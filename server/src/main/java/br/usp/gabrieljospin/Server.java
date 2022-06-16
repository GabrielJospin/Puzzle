package br.usp.gabrieljospin;

import br.usp.gabrieljospin.entinties.PieceRepository;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import br.usp.gabrieljospin.stubs.PartRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    private static Registry registry;

    public static void main(String[] args) {
        try {
            String jsonPath = "src/main/resources/" + args[0]+".json";
            JSONObject jsonObject =getJson(jsonPath);
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

    private static JSONObject getJson(String jsonPath) {
        File file = new File(jsonPath);
        String content = "";

        try {
            content = FileUtils.readFileToString(file,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JSONObject(content);
    }
}
