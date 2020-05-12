package ru.alexeylesin.kingdonate;

import org.bukkit.event.*;
import org.bukkit.entity.*;

public class TitleSendEvent extends Event
{
    private static final HandlerList handlers;
    private final Player player;
    private String title;
    private String subtitle;
    private boolean cancelled;
    
    public TitleSendEvent(final Player player, final String title, final String subtitle) {
        this.cancelled = false;
        this.player = player;
        this.title = title;
        this.subtitle = subtitle;
    }
    
    public HandlerList getHandlers() {
        return TitleSendEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return TitleSendEvent.handlers;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public String getSubtitle() {
        return this.subtitle;
    }
    
    public void setSubtitle(final String subtitle) {
        this.subtitle = subtitle;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    static {
        handlers = new HandlerList();
    }
}
