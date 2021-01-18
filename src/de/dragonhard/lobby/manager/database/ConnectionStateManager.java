package de.dragonhard.lobby.manager.database;

import de.dragonhard.lobby.manager.Managers;

public class ConnectionStateManager extends Managers {

    private static Enum currentState = ConnectionState.OFFLINE;

    public void setState(Enum state) {
        currentState = state;
    }

    public Enum getState() {
        return currentState;
    }

    public boolean isOffline() {
        return currentState.equals(ConnectionState.OFFLINE);
    }

    public boolean isConnected() {
        return currentState.equals(ConnectionState.CONNECTION_ESTABLISHED);
    }

    public boolean isConnecting() {
        return currentState.equals(ConnectionState.CONNECTING);
    }

    public boolean isConnectionError() {
        return currentState.equals(ConnectionState.CONNECTION_ERROR);
    }

    public boolean isConnectionRefused() {
        return currentState.equals(ConnectionState.CONNECTION_REFUSED);
    }

}

