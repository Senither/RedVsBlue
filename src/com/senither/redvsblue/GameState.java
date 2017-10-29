package com.senither.redvsblue;

import com.senither.redvsblue.contracts.states.GameStateHandler;
import com.senither.redvsblue.states.ChangingMapHandler;
import com.senither.redvsblue.states.PlayingHandler;
import com.senither.redvsblue.states.WaitingForPlayersHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum GameState {

    WAITING_FOR_PLAYERS(1, 30, true, WaitingForPlayersHandler.class),
    PLAYING(3, PlayingHandler.class),
    CHANGING_MAP(4, ChangingMapHandler.class);

    private static final Map<GameState, GameStateHandler> handlers = new HashMap<>();

    static {
        for (GameState state : values()) {
            try {
                Object instance = state.getInstance().getDeclaredConstructor().newInstance();

                if (instance instanceof GameStateHandler) {
                    handlers.put(state, (GameStateHandler) instance);
                }
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                Logger.getLogger("Minecraft.RedVsBlue").log(Level.SEVERE, "Failed to create game state handler.", ex);
            }
        }
    }

    private final int id;
    private final int fixedTime;
    private final boolean hasFixedTime;
    private final Class handler;

    GameState(int id, int fixedTime, boolean hasFixedTime, Class handler) {
        this.id = id;
        this.fixedTime = fixedTime;
        this.hasFixedTime = hasFixedTime;
        this.handler = handler;
    }

    GameState(int id, boolean hasFixedTime, Class handler) {
        this(id, 0, hasFixedTime, handler);
    }

    GameState(int id, Class handler) {
        this(id, false, handler);
    }

    public int getId() {
        return id;
    }

    public int getFixedTime() {
        return fixedTime;
    }

    public boolean isFixedTime() {
        return hasFixedTime;
    }

    public GameStateHandler getHandler() {
        return handlers.get(this);
    }

    public <T> Class<T> getInstance() {
        return handler;
    }
}
