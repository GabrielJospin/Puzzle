package br.usp.gabrieljospin;

import br.usp.gabrieljospin.command.*;
import br.usp.gabrieljospin.connection.ServerObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class ClientMain {

    private static Map<String, ServerObject> mapServers = new HashMap<>();

    public static void interaction(String input, Scanner scanner) throws Exception {
        if(input.equals(":q"))
            return;

        Command command;
        String action = List.of(input.split(" ")).get(0);
        switch (action){
            case "addp":
                command = new Addp(input, mapServers);
                command.execute();
                break;
            case "clear":
                command = new Clear(input, mapServers);
                command.execute();
                break;
            case "help":
                help();
                break;
            case "ls":
                command = new Ls(input, mapServers);
                command.execute();
                break;
            case "mount":
                command = new Mount(input, mapServers);
                command.execute();
                break;
            case "status":
                command = new Status(input, mapServers);
                command.execute();
                break;
            case "unmount":
                command = new Unmount(input, mapServers);
                command.execute();
                break;
            default:
                System.err.println("ERROR: Invalid command");
                System.err.println("\tuse help to know available commands");
        }
        System.out.println("");
        System.out.print("client@puzzle$ ");
        input = scanner.nextLine();
        interaction(input, scanner);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("client@puzzle$ ");
        String input = scanner.nextLine();
        try {
            interaction(input, scanner);
        }catch (Exception e){
            System.err.println("Error");
            System.err.println(e.getMessage());
            main(args);
        }

        try {
            input = "clear";
            Clear out = new Clear(input, mapServers);
            out.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        scanner.close();
    }

    private static void help() {
        System.out.println("Available commands");
        System.out.println(":q\t|quit the system\t");
        System.out.println("help\t|list all commands");
        System.out.println("ls\t|list every part in server\t");
        System.out.println("mount\t|connect a new server\t");
        System.out.println("status\t|show all servers\t");
        System.out.println("unmount\t|disconnect a server\t");
        System.out.println("write <command> --help to understands args");
    }
}
