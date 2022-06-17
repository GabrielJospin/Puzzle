package br.usp.gabrieljospin;

import br.usp.gabrieljospin.command.*;

import java.util.Scanner;
import java.util.List;

public class ClientMain {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            while(!input.startsWith("quit")){

                Command command;
                String action = List.of(input.split(" ")).get(0);
                switch (action){
                    case "help":
                        help();
                    case "ls":
                        command = new Ls(input);
                        break;
                    default:
                        System.err.println("ERROR: Invalid command");
                        System.err.println("\tuse help to know available commands");
                }

                input = scanner.nextLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void help() {

    }
}
