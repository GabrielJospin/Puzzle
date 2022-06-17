package br.usp.gabrieljospin;

import br.usp.gabrieljospin.command.*;

import java.util.Scanner;
import java.util.List;

public class ClientMain {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("client@puzzle>");
            String input = scanner.nextLine();
            while(!input.startsWith(":q")){

                Command command;
                String action = List.of(input.split(" ")).get(0);
                switch (action){
                    case "help":
                        help();
                        break;
                    case "ls":
                        command = new Ls(input);
                        break;
                    default:
                        System.err.println("ERROR: Invalid command");
                        System.err.println("\tuse help to know available commands");
                }
                System.out.println("");
                System.out.print("client@puzzle>");
                input = scanner.nextLine();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private static void help() {
        System.out.println("Available commands");
        System.out.println("ls\t|list every part in server\t");
        System.out.println("help\t|list all commands");
        System.out.println("write <command> --help to understands args");
    }
}
