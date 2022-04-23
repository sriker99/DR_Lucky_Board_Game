package game.model;

public interface ReadOnlyWorld {
     String[] getSpaces();
     String displayClues();
     String[] getPlayerItems();
     String lookAround();
     String[] getSpaceItems();
}
