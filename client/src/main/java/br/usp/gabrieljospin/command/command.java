package br.usp.gabrieljospin.command;

import java.util.List;

public interface command {

    List<String> getArgs();
    String getCommand();
    void execute();
}
