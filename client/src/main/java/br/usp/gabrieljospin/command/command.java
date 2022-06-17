package br.usp.gabrieljospin.command;

import java.rmi.RemoteException;
import java.util.List;

public interface command {

    List<String> getArgs();
    String getCommand();
    void execute() throws RemoteException;
}
