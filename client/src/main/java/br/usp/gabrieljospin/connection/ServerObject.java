package br.usp.gabrieljospin.connection;

public class ServerObject {

    private final String name;
    private final Integer port;
    private final String connName;
    private Status status;
    public enum Status {NEW, NOT_FOUND, CONNECT, OFF}

    public ServerObject(String name, Integer port) {
        this.name = name;
        this.port = port;
        this.connName = "//127.0.0.1:" + port + "/" + name;
        this.status = Status.NEW;
    }

    public String getName() {
        return name;
    }

    public Integer getPort() {
        return port;
    }

    public Status getStatus() {
        return status;
    }

    public String getConnName() {
        return connName;
    }

    public void setStatus(boolean connected){
        if(this.status.equals(Status.NEW) && connected)
            this.status = Status.CONNECT;
        if(this.status.equals(Status.NEW))
            this.status = Status.NOT_FOUND;
        if(this.status.equals(Status.CONNECT) && !connected)
            this.status = Status.OFF;
        if(this.status.equals(Status.NOT_FOUND) && connected)
            this.status = Status.CONNECT;
        if(this.status.equals(Status.OFF) && connected)
            this.status = Status.CONNECT;
    }
}
