package com.gmail.necnionch.myplugin.hatyou.bukkit.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HatYouEvent extends Event implements Cancellable {
    public static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final Player target;
    private final @Nullable Event sourceEvent;
    private boolean cancelled;

    public HatYouEvent(Player player, Player target, @Nullable Event sourceEvent) {
        this.player = player;
        this.target = target;
        this.sourceEvent = sourceEvent;

    }

    public Player getPlayer() {
        return player;
    }

    public Player getTarget() {
        return target;
    }

    @Nullable
    public Event getSourceEvent() {
        return sourceEvent;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
