package de.dragonhard.lobby.manager.database;

public enum ConnectionState {
    OFFLINE,
    CONNECTING,
    CONNECTION_ESTABLISHED,
    CONNECTION_REFUSED,
    CONNECTION_ERROR;
}
