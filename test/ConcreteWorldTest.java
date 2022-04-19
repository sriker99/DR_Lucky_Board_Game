import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import game.model.ConcreteWorld;
import game.model.RandomGen;
import game.model.RandomGenerator;
import game.model.Space;
import game.model.SpaceImpl;
import game.model.World;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * This class test ConcreteWorld with all necessary actions.
 */
public class ConcreteWorldTest {

  private World c1;
  private World c2;
  private RandomGenerator random;
  private Readable file;

  @Before
  public void setup() throws IOException {
    file = new FileReader("res/mansiontest.txt");
    c1 = new ConcreteWorld(file, new RandomGen(), 5);
  }

  @Test
  public void lookAround() {
    Space space = new SpaceImpl(3, 16, 10, 21,
        "Kitchen");
    c1.addPlayer("ravi", "Kitchen");
    StringBuffer output = new StringBuffer();
    output.append("Target location is:  Armory\n");
    output.append(
        "Space is  Kitchen and its items are Crepe Pan with damage 3, "
            + "Sharp Knife with damage 3, \n");
    output.append(" and its players are [ravi].\n");
    output.append("Its neighbours are \n");
    output.append(" Dining Hall with items [] with players []\n");
    output.append(" Parlor with items [] with players []\n");
    output.append(" Wine Cellar with items [Rat Poison, Piece of Rope] with players []\n");
    assertEquals(output.toString(), c1.lookAround());
  }

  @Test
  public void displayPlayerInfo() {
    c1.addPlayer("ravi", "Kitchen");
    StringBuffer output = new StringBuffer();
    output.append("Currently player ravi is located at  Kitchen and not carrying any items.\n");
    assertEquals(output.toString(), c1.displayPlayerInfo(0));
  }

  @Test
  public void displaySpaceInfoWithPet() {
    c1.addPlayer("ravi", "Armory");
    StringBuffer output = new StringBuffer();
    output.append("Space is Armory and its items are [Revolver]\n");
    output.append(" and its players are [ravi].\n");
    output.append("Pet is present in the current room.\n");
    output.append("Its neighbours are [ Billiard Room,  Dining Hall,  Drawing Room]\n");
    assertEquals(output.toString(), c1.displaySpaceInfo("Armory"));
  }

  @Test
  public void displaySpaceInfo() {
    c1.addPlayer("ravi", "Kitchen");
    StringBuffer output = new StringBuffer();
    output.append("Space is Kitchen and its items are [Crepe Pan, Sharp Knife]\n");
    output.append(" and its players are [ravi].\n");
    output.append("Its neighbours are [ Dining Hall,  Parlor,  Wine Cellar]\n");
    assertEquals(output.toString(), c1.displaySpaceInfo("Kitchen"));
  }

  @Test
  public void addPlayer() {
    c1.addPlayer("ravi", "Kitchen");
    StringBuffer output = new StringBuffer();
    output.append("Currently player ravi is located at  Kitchen and not carrying any items.\n");
    assertEquals(output.toString(), c1.displayPlayerInfo(0));
    output = new StringBuffer();
    output.append("Space is Kitchen and its items are [Crepe Pan, Sharp Knife]\n");
    output.append(" and its players are [ravi].\n");
    output.append("Its neighbours are [ Dining Hall,  Parlor,  Wine Cellar]\n");
    assertEquals(output.toString(), c1.displaySpaceInfo("Kitchen"));
  }

  @Test
  public void addComputerPlayer() {
    c1.addComputerPlayer();
    assertEquals(
        "computer_0", c1.getTurn());
  }

  @Test(expected = IllegalArgumentException.class)
  public void tryingToPickInvalidItem() {
    c1.addPlayer("ravi", "Kitchen");
    c1.pickItem("Revolver");
  }

  @Test(expected = IllegalStateException.class)
  public void tryingToPickItemFromEmptyRoom() {
    c1.addPlayer("ravi", "Dining Hall");
    c1.pickItem("Revolver");
  }

  @Test
  public void moveToValidRoom() {
    c1.addPlayer("ravi", "Dining Hall");
    c1.movePlayer("Kitchen");
    StringBuffer output = new StringBuffer();
    output.append("Currently player ravi is located at  Kitchen and not carrying any items.\n");
    assertEquals(output.toString(), c1.displayPlayerInfo(0));
    output = new StringBuffer();
    output.append("Space is Kitchen and its items are [Crepe Pan, Sharp Knife]\n");
    output.append(" and its players are [ravi].\n");
    output.append("Its neighbours are [ Dining Hall,  Parlor,  Wine Cellar]\n");
    assertEquals(output.toString(), c1.displaySpaceInfo("Kitchen"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void tryingToMoveToNonNeighbour() {
    c1.addPlayer("ravi", "Kitchen");
    c1.movePlayer("Armory");
  }

  @Test(expected = IllegalArgumentException.class)
  public void tryingToMoveToNull() {
    c1.addPlayer("ravi", "Kitchen");
    c1.movePlayer(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void tryingToPickNull() {
    c1.addPlayer("ravi", "Kitchen");
    c1.pickItem(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveToRoom() {
    c1.addPlayer("ravi", "Kitchen");
    c1.movePlayer("abc");
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptySpaceParameterToMove() {
    c1.addPlayer("ravi", "Kitchen");
    c1.movePlayer("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void tryingToMoveToSameRoom() {
    c1.addPlayer("ravi", "Kitchen");
    c1.movePlayer("Kitchen");
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyItemParameterToPick() {
    c1.addPlayer("ravi", "Kitchen");
    c1.pickItem("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidItemParameterToPick() {
    c1.addPlayer("ravi", "Kitchen");
    c1.pickItem("abc");
  }


  @Test
  public void tryingToPickValidItem() {
    c1.addPlayer("ravi", "Kitchen");
    c1.pickItem("Sharp Knife");
    StringBuffer output = new StringBuffer();
    output.append(
        "Currently player ravi is located at  Kitchen and carrying items Sharp Knife,.\n");
    assertEquals(output.toString(), c1.displayPlayerInfo(0));
    output = new StringBuffer();
    output.append("Space is Kitchen and its items are [Crepe Pan]\n");
    output.append(" and its players are [ravi].\n");
    output.append("Its neighbours are [ Dining Hall,  Parlor,  Wine Cellar]\n");
    assertEquals(output.toString(), c1.displaySpaceInfo("Kitchen"));
  }

  @Test
  public void removingItemWhenPicked() {
    c1.addPlayer("ravi", "Kitchen");
    c1.pickItem("Sharp Knife");
    StringBuffer output = new StringBuffer();
    output.append("Space is Kitchen and its items are [Crepe Pan]\n");
    output.append(" and its players are [ravi].\n");
    output.append("Its neighbours are [ Dining Hall,  Parlor,  Wine Cellar]\n");
    assertEquals(output.toString(), c1.displaySpaceInfo("Kitchen"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidLookAround() {
    c1.lookAround();
  }

  @Test
  public void pickingItemsMoreThanOneItem() {
    c1.addPlayer("ravi", "Kitchen");
    c1.pickItem("Sharp Knife");
    c1.pickItem("Crepe Pan");
    c1.movePlayer("Dining Hall");
    c1.movePlayer("Armory");
    c1.pickItem("Revolver");
    assertEquals("Currently player ravi is located at  Armory and carrying items "
        + "Sharp Knife,Crepe Pan,Revolver,.\n", c1.displayPlayerInfo(0));
  }

  @Test(expected = IllegalStateException.class)
  public void pickingItemsMoreThanItemLimit() {
    c1.addPlayer("ravi", "Kitchen");
    c1.pickItem("Sharp Knife");
    c1.pickItem("Crepe Pan");
    c1.movePlayer("Dining Hall");
    c1.movePlayer("Armory");
    c1.pickItem("Revolver");
    c1.movePlayer("Billiard Room");
    c1.pickItem("Billiard Cue");
  }

  @Test
  public void testingMultiplePlayers() {
    c1.addPlayer("ravi", "Kitchen");
    c1.addPlayer("teja", "Armory");
    c1.movePlayer("Dining Hall");
    StringBuffer output = new StringBuffer();
    output.append("Currently player ravi is located at  Dining Hall and not carrying any items.\n");
    assertEquals(output.toString(), c1.displayPlayerInfo(0));
    c1.pickItem("Revolver");
    output = new StringBuffer();
    output.append("Currently player teja is located at  Armory and carrying items Revolver,.\n");
    assertEquals(output.toString(), c1.displayPlayerInfo(1));
    output = new StringBuffer();
    output.append("Target location is:  Carriage House\n");
    output.append("Pet is present in the current room\n");
    output.append("Space is  Dining Hall with no items \n");
    output.append(" and its players are [ravi].\n");
    output.append("Its neighbours are \n");
    output.append(" Armory with items [] with players [teja]\n");
    output.append(" Billiard Room with items [Billiard Cue] with players []\n");
    output.append(" Drawing Room with items [Letter Opener] with players []\n");
    output.append(" Kitchen with items [Crepe Pan, Sharp Knife] with players []\n");
    output.append(" Parlor with items [] with players []\n");
    output.append(" Tennessee Room with items [] with players []\n");
    output.append(" Trophy Room with items [Duck Decoy, Monkey Hand] with players []\n");
    output.append(" Wine Cellar with items [Rat Poison, Piece of Rope] with players []\n");
    assertEquals(output.toString(), c1.lookAround());
    c1.movePlayer("Dining Hall");
    output = new StringBuffer();
    output.append(
        "Currently player teja is located at  Dining Hall and carrying items Revolver,.\n");
    assertEquals(output.toString(), c1.displayPlayerInfo(1));
  }

  @Test
  public void testDisplaySpaceInfoWithMultiplePlayers() {
    c1.addPlayer("ravi", "Kitchen");
    c1.addPlayer("teja", "Kitchen");
    c1.addPlayer("dandi", "Kitchen");
    StringBuffer output = new StringBuffer();
    output.append("Space is Kitchen and its items are [Crepe Pan, Sharp Knife]\n");
    output.append(" and its players are [ravi, teja, dandi].\n");
    output.append("Its neighbours are [ Dining Hall,  Parlor,  Wine Cellar]\n");
    assertEquals(output.toString(), c1.displaySpaceInfo("Kitchen"));
  }

  @Test
  public void testDisplaySpaceInfoWithNoPlayers() {
    StringBuffer output = new StringBuffer();
    output.append("Space is Kitchen and its items are [Crepe Pan, Sharp Knife]\n");
    output.append(" with no players.\n");
    output.append("Its neighbours are [ Dining Hall,  Parlor,  Wine Cellar]\n");
    assertEquals(output.toString(), c1.displaySpaceInfo("Kitchen"));
  }

  @Test
  public void testDisplaySpaceInfoWithOnePlayers() {
    c1.addPlayer("ravi", "Armory");
    StringBuffer output = new StringBuffer();
    output.append("Space is Armory and its items are [Revolver]\n");
    output.append(" and its players are [ravi].\n");
    output.append("Pet is present in the current room.\n");
    output.append("Its neighbours are [ Billiard Room,  Dining Hall,  Drawing Room]\n");
    assertEquals(output.toString(), c1.displaySpaceInfo("Armory"));
  }

  @Test
  public void testingMoveTarget() {
    c1.addPlayer("ravi", "Kitchen");
    c1.lookAround();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLookAroundWithoutPlayers() {
    c1.lookAround();
  }

  @Test
  public void testLookAroundWithPet() {
    c1.addPlayer("ravi", "Armory");
    StringBuffer output = new StringBuffer();
    output.append("Target location is:  Armory\n");
    output.append("Pet is present in the current room\n");
    output.append("Space is  Armory and its items are Revolver with damage 3, \n");
    output.append(" and its players are [ravi].\n");
    output.append("Its neighbours are \n");
    output.append(" Billiard Room with items [Billiard Cue] with players []\n");
    output.append(" Dining Hall with items [] with players []\n");
    output.append(" Drawing Room with items [Letter Opener] with players []\n");
    assertEquals(output.toString(), c1.lookAround());
  }

  @Test
  public void testLookAroundWithoutPet() {
    c1.addPlayer("ravi", "Kitchen");
    StringBuffer output = new StringBuffer();
    output.append("Target location is:  Armory\n");
    output.append(
        "Space is  Kitchen and its items are Crepe Pan with damage 3, Sharp Knife "
            + "with damage 3, \n");
    output.append(" and its players are [ravi].\n");
    output.append("Its neighbours are \n");
    output.append(" Dining Hall with items [] with players []\n");
    output.append(" Parlor with items [] with players []\n");
    output.append(" Wine Cellar with items [Rat Poison, Piece of Rope] with players []\n");
    assertEquals(output.toString(), c1.lookAround());
  }

  @Test
  public void testLookAroundWithPetInNeighbours() {
    c1.addPlayer("teja", "Drawing Room");
    c1.addPlayer("ravi", "Armory");
    StringBuffer output = new StringBuffer();
    output.append("Target location is:  Armory\n");
    output.append("Space is  Drawing Room and its items are Letter Opener with damage 2, \n");
    output.append(" and its players are [teja].\n");
    output.append("Its neighbours are \n");
    output.append(" Dining Hall with items [] with players []\n");
    output.append(" Foy with items [] with players []\n");
    output.append(" Wine Cellar with items [Rat Poison, Piece of Rope] with players []\n");
    assertEquals(output.toString(), c1.lookAround());
  }

  @Test(expected = IllegalStateException.class)
  public void testAttackTargetNotInSame() {
    c1.addPlayer("ravi", "Kitchen");
    c1.pickItem("Sharp Knife");
    c1.attack("Sharp Knife");
  }

  @Test(expected = IllegalStateException.class)
  public void testAttackWithMultiplePlayersInCurrentSpace() {
    c1.addPlayer("teja", "Armory");
    c1.addPlayer("ravi", "Armory");
    c1.attack("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAttackIllegalItem() {
    c1.addPlayer("teja", "Billiard Room");
    c1.pickItem("Billiard Cue");
    c1.attack("Sharp Knife");
  }

  @Test(expected = IllegalStateException.class)
  public void testAttackWithNeighbourPlayers() {
    c1.addPlayer("teja", "Carriage House");
    c1.addPlayer("ravi", "Winter Garden");
    StringBuffer output = new StringBuffer();
    output.append("Target name is  Doctor Lucky, health is 5, current location is 0\n");
    output.append("Player name is teja, and its location is 2,  carrying items []\n");
    assertEquals(output.toString(), c1.displayClues());
    c1.pickItem("Chain Saw");
    output = new StringBuffer();
    output.append("Target name is  Doctor Lucky, health is 5, current location is 1\n");
    output.append("Player name is ravi, and its location is 20,  carrying items []\n");
    assertEquals(output.toString(), c1.displayClues());
    output = new StringBuffer();
    output.append("Target location is:  Billiard Room\n");
    output.append("Space is  Winter Garden with no items \n");
    output.append(" and its players are [ravi].\n");
    output.append("Its neighbours are \n");
    output.append(" Carriage House with items [Big Red Hammer] with players [teja]\n");
    output.append(" Piazza with items [Civil War Cannon] with players []\n");
    assertEquals(output.toString(), c1.lookAround());
    output = new StringBuffer();
    output.append("Target name is  Doctor Lucky, health is 5, current location is 2\n");
    output.append("Player name is teja, and its location is 2,  carrying items [Chain Saw]\n");
    output.append(" with damages 4,  respectively.\n");
    assertEquals(output.toString(), c1.displayClues());
    c1.attack("Chain Saw");
    output = new StringBuffer();
    output.append("Target name is  Doctor Lucky, health is 5, current location is 2\n");
    output.append("Player name is teja, and its location is 2,  carrying items [Chain Saw]\n");
    output.append(" with damages 4,  respectively.\n");
    assertEquals(output.toString(), c1.displayClues());
  }

  @Test
  public void testAttackOnePlayerWithItem() {
    c1.addPlayer("teja", "Carriage House");
    c1.pickItem("Chain Saw");
    c1.lookAround();
    StringBuffer output = new StringBuffer();
    output.append("Target name is  Doctor Lucky, health is 5, current location is 2\n");
    output.append("Player name is teja, and its location is 2,  carrying items [Chain Saw]\n");
    output.append(" with damages 4,  respectively.\n");
    assertEquals(output.toString(), c1.displayClues());
    c1.attack("Chain Saw");
    output = new StringBuffer();
    output.append("Target name is  Doctor Lucky, health is 1, current location is 3\n");
    output.append("Player name is teja, and its location is 2,  carrying items []\n");
    assertEquals(output.toString(), c1.displayClues());
    output = new StringBuffer();
    output.append(
        "Currently player teja is located at  Carriage House and not carrying any items.\n");
    assertEquals(output.toString(), c1.displayPlayerInfo(0));
  }

  @Test
  public void testAttackWithPetInRoom() {
    c1.addPlayer("teja", "Armory");
    StringBuffer output = new StringBuffer();
    output.append("Target name is  Doctor Lucky, health is 5, current location is 0\n");
    output.append("Player name is teja, and its location is 0,  carrying items []\n");
    output.append("Pet is present in the current room as player.\n");
    assertEquals(output.toString(), c1.displayClues());
    c1.attack("");
    output = new StringBuffer();
    output.append("Target name is  Doctor Lucky, health is 4, current location is 1\n");
    output.append("Player name is teja, and its location is 0,  carrying items []\n");
    assertEquals(output.toString(), c1.displayClues());
  }

  @Test
  public void testAttackWithPetInRoomWithNeighbouringPlayers() {
    c1.addPlayer("teja", "Armory");
    c1.addPlayer("ravi", "Drawing Room");
    c1.attack("");
    assertEquals("Space is Armory and its items are [Revolver]\n"
        + " and its players are [teja].\n"
        + "Its neighbours are [ Dining Hall,  Drawing Room]\n", c1.displaySpaceInfo("Armory"));
    assertEquals("Space is Drawing Room and its items are [Letter Opener]\n"
            + " and its players are [ravi].\n"
            + "Its neighbours are [ Armory,  Dining Hall,  Foy,  Wine Cellar]\n",
        c1.displaySpaceInfo("Drawing Room"));
    assertEquals("Target name is  Doctor Lucky, health is 4, current location is 1\n"
        + "Player name is ravi, and its location is 4,  carrying items []\n", c1.displayClues());
  }

  @Test(expected = IllegalStateException.class)
  public void testAttackWithNeighbouringPlayersWithPet() {
    c1.addPlayer("ravi", "Drawing Room");
    c1.addPlayer("teja", "Dining Hall");
    StringBuffer st = new StringBuffer();
    st.append("Target location is:  Armory\n");
    st.append("Space is  Drawing Room and its items are Letter Opener with damage 2, \n");
    st.append(" and its players are [ravi].\n");
    st.append("Its neighbours are \n");
    st.append(" Dining Hall with items [] with players [teja]\n");
    st.append(" Foy with items [] with players []\n");
    st.append(" Wine Cellar with items [Rat Poison, Piece of Rope] with players []\n");
    assertEquals(st.toString(), c1.lookAround());
    st = new StringBuffer();
    st.append("Target location is:  Billiard Room\n");
    st.append("Space is  Dining Hall with no items \n");
    st.append(" and its players are [teja].\n");
    st.append("Its neighbours are \n");
    st.append(" Armory with items [Revolver] with players []\n");
    st.append(" Drawing Room with items [Letter Opener] with players [ravi]\n");
    st.append(" Kitchen with items [Crepe Pan, Sharp Knife] with players []\n");
    st.append(" Parlor with items [] with players []\n");
    st.append(" Tennessee Room with items [] with players []\n");
    st.append(" Trophy Room with items [Duck Decoy, Monkey Hand] with players []\n");
    st.append(" Wine Cellar with items [Rat Poison, Piece of Rope] with players []\n");
    assertEquals(st.toString(), c1.lookAround());
    c1.pickItem("Letter Opener");
    c1.lookAround();
    c1.attack("Letter Opener");
  }

  @Test
  public void movePet() {
    c1.addPlayer("ravi", "Trophy Room");
    c1.addPlayer("teja", "Piazza");
    c1.movePet("Drawing Room");
    assertEquals("Space is Drawing Room and its items are [Letter Opener]\n"
            + " with no players.\n"
            + "Pet is present in the current room.\n"
            + "Its neighbours are [ Armory,  Dining Hall,  Foy,  Wine Cellar]\n",
        c1.displaySpaceInfo("Drawing Room"));
    c1.movePet("Wine Cellar");
    assertEquals("Space is Wine Cellar and its items are [Rat Poison, Piece of Rope]\n"
            + " with no players.\n"
            + "Pet is present in the current room.\n"
            + "Its neighbours are [ Dining Hall,  Drawing Room,  Kitchen]\n",
        c1.displaySpaceInfo("Wine Cellar"));
  }

  @Test
  public void testPetDfs() {
    c1.addPlayer("ravi", "Billiard Room");
    assertEquals("Target name is  Doctor Lucky, health is 5, current location is 0\n"
        + "Player name is ravi, and its location is 1,  carrying items []\n", c1.displayClues());
    assertEquals("Space is Armory and its items are [Revolver]\n"
            + " with no players.\n"
            + "Pet is present in the current room.\n"
            + "Its neighbours are [ Billiard Room,  Dining Hall,  Drawing Room]\n",
        c1.displaySpaceInfo("Armory"));
    c1.lookAround();
    assertEquals("Target name is  Doctor Lucky, health is 5, current location is 1\n"
        + "Player name is ravi, and its location is 1,  carrying items []\n"
        + "Pet is present in the current room as player.\n", c1.displayClues());
    assertEquals("Space is Billiard Room and its items are [Billiard Cue]\n"
            + " and its players are [ravi].\n"
            + "Pet is present in the current room.\n"
            + "Its neighbours are [ Armory,  Dining Hall,  Trophy Room]\n",
        c1.displaySpaceInfo("Billiard Room"));
    c1.movePlayer("Trophy Room");
    assertEquals("Target name is  Doctor Lucky, health is 5, current location is 2\n"
        + "Player name is ravi, and its location is 18,  carrying items []\n", c1.displayClues());
    assertEquals("Space is Dining Hall with no items \n"
        + " with no players.\n"
        + "Pet is present in the current room.\n"
        + "Its neighbours are [ Armory,  Billiard Room,  Drawing Room,  Kitchen,  Parlor,  "
        + "Tennessee Room,  Trophy Room,  Wine Cellar]\n", c1.displaySpaceInfo("Dining Hall"));
    c1.pickItem("Monkey Hand");
    assertEquals("Target name is  Doctor Lucky, health is 5, current location is 3\n"
        + "Player name is ravi, and its location is 18,  carrying items [Monkey Hand]\n"
        + " with damages 2,  respectively.\n", c1.displayClues());
    assertEquals("Space is Drawing Room and its items are [Letter Opener]\n"
            + " with no players.\n"
            + "Pet is present in the current room.\n"
            + "Its neighbours are [ Armory,  Dining Hall,  Foy,  Wine Cellar]\n",
        c1.displaySpaceInfo("Drawing Room"));
    c1.movePlayer("Dining Hall");
    c1.movePlayer("Kitchen");
    c1.pickItem("Sharp Knife");
    c1.pickItem("Crepe Pan");
    c1.lookAround();
    assertEquals("Target name is  Doctor Lucky, health is 5, current location is 8\n"
        + "Player name is ravi, and its location is 8,  carrying items [Monkey Hand, Sharp Knife,"
        + " Crepe Pan]\n"
        + " with damages 2, 3, 3,  respectively.\n", c1.displayClues());
    c1.attack("Monkey Hand");
    assertEquals("Target name is  Doctor Lucky, health is 3, current location is 9\n"
        + "Player name is ravi, and its location is 8,  carrying items [Sharp Knife, Crepe Pan]\n"
        + " with damages 3, 3,  respectively.\n", c1.displayClues());
    assertEquals("Space is Piazza and its items are [Civil War Cannon]\n"
            + " with no players.\n"
            + "Pet is present in the current room.\n"
            + "Its neighbours are [ Foy,  Hedge Maze,  Winter Garden]\n",
        c1.displaySpaceInfo("Piazza"));
    c1.movePet("Drawing Room");
    assertEquals("Space is Drawing Room and its items are [Letter Opener]\n"
            + " with no players.\n"
            + "Pet is present in the current room.\n"
            + "Its neighbours are [ Armory,  Dining Hall,  Foy,  Wine Cellar]\n",
        c1.displaySpaceInfo("Drawing Room"));
    c1.lookAround();
    assertEquals("Space is Armory and its items are [Revolver]\n"
            + " with no players.\n"
            + "Pet is present in the current room.\n"
            + "Its neighbours are [ Billiard Room,  Dining Hall,  Drawing Room]\n",
        c1.displaySpaceInfo("Armory"));
  }

  @Test
  public void testWinnerPlayer() {
    c1.addPlayer("ravi", "Armory");
    c1.attack("");
    c1.pickItem("Revolver");
    c1.movePlayer("Dining Hall");
    c1.attack("Revolver");
    c1.movePlayer("Kitchen");
    c1.pickItem("Sharp Knife");
    c1.lookAround();
    c1.lookAround();
    c1.attack("Sharp Knife");
    assertEquals("ravi", c1.getWinner());
  }

  @Test
  public void testWinnerTarget() {
    c1.addPlayer("ravi", "Armory");
    c1.attack("");
    c1.pickItem("Revolver");
    c1.movePlayer("Dining Hall");
    c1.attack("Revolver");
    c1.movePlayer("Kitchen");
    assertEquals(true, c1.isGameOver());
    assertEquals(null, c1.getWinner());
  }

  @Test
  public void testGameNotOver() {
    c1.addPlayer("ravi", "Armory");
    c1.attack("");
    c1.pickItem("Revolver");
    c1.movePlayer("Dining Hall");
    c1.attack("Revolver");
    assertEquals(false, c1.isGameOver());
    assertEquals(null, c1.getWinner());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpaceForMovePet() {
    c1.addPlayer("ravi", "Kitchen");
    c1.movePet("abcd");
  }

  @Test(expected = IllegalStateException.class)
  public void illegalAttackWithPoke() {
    c1.addPlayer("ravi", "Kitchen");
    c1.attack("");
  }

  @Test
  public void removeItemAfterAttack() {
    c1.addPlayer("ravi", "Kitchen");
    c1.pickItem("Sharp Knife");
    assertEquals("Currently player ravi is located at  Kitchen and carrying items Sharp Knife,.\n",
        c1.displayPlayerInfo(0));
    Exception e = assertThrows(IllegalStateException.class, () -> {
      c1.attack("Sharp Knife");
    });
    assertEquals("Currently player ravi is located at  Kitchen and not carrying any items.\n",
        c1.displayPlayerInfo(0));
    assertEquals("Space is Kitchen and its items are [Crepe Pan]\n"
        + " and its players are [ravi].\n"
        + "Its neighbours are [ Parlor,  Wine Cellar]\n", c1.displaySpaceInfo("Kitchen"));
  }

  @Test
  public void testMovePetForComputerPlayer() throws FileNotFoundException {
    StringBuilder log = new StringBuilder();
    random = new RandomMock(log, 2345, 3, 2, 5, 10);
    Readable file1 = new FileReader("res/mansiontest.txt");
    c1 = new ConcreteWorld(file1, random, 5);
    c1.addComputerPlayer();
    String actual = c1.checkComputerPlayer();
    String expected1 = "Computer player computer_0 moved the pet\n";
    assertEquals(expected1, actual);
    StringBuilder sb = new StringBuilder();
    sb.append("Space is Library with no items \n");
    sb.append(" with no players.\n");
    sb.append("Its neighbours are [ Master Suite,  Nursery,  Trophy Room]\n");
    assertEquals(sb.toString(), c1.displaySpaceInfo("Library"));
  }

  @Test
  public void testAttackForComputerPlayer() throws FileNotFoundException {
    StringBuilder log = new StringBuilder();
    random = new RandomMock(log, 2345, 0, -1, 5, 10);
    Readable file1 = new FileReader("res/mansiontest.txt");
    c1 = new ConcreteWorld(file1, random, 5);
    c1.addComputerPlayer();
    String actual = c1.checkComputerPlayer();
    String expected1 = "Computer player computer_0 has attacked\n";
    assertEquals(expected1, actual);
    StringBuilder sb = new StringBuilder();
    sb.append("Currently player computer_0 is located at  Armory and not carrying any items.\n");
    assertEquals(sb.toString(), c1.displayPlayerInfo(0));
  }
}