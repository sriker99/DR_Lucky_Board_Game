package game.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * This class implements World interface and creates a world from the users input and.
 * performs all the actions like moving the target, displaying neighbours, getting items.
 */
public class ConcreteWorld implements World {

  private final int numOfRows;
  private final int numOfCol;
  private final String nameOfWorld;
  private final List<String> sequence;
  private final Person target;
  private final int numOfRooms;
  private final List<Space> spaceList;
  private final Pet pet;
  private List<String> neighboursList;
  private Map<String, Thing> itemList;
  private List<Player> playersList;
  private int playerCount;
  private int currentPlayerIndex;
  private String winner;
  private RandomGenerator randNum;
  private List<Integer>[] adj;
  private int numOfTurns;
  private List<Integer> traversal;
  private int source;
  private int index;


  /**
   * This constructor reads the input from the given format and assigns all the values to the
   * objects and initializes the World object parameters.
   *
   * @param randNum is a random number generator object.
   * @param file    this is an input file should be in text format
   * @throws IllegalArgumentException if the arguments doesn't meet the standards.
   */
  public ConcreteWorld(Readable file, RandomGenerator randNum, int numOfTurns)
      throws IllegalArgumentException {
    if (file == null || randNum == null || numOfTurns <= 0) {
      throw new IllegalArgumentException("File and random number shouldn't be empty. "
          + "Number of turns can't be negative or zero.");
    }
    sequence = new ArrayList<String>();
    neighboursList = new ArrayList<String>();
    spaceList = new ArrayList<Space>();
    itemList = new HashMap<String, Thing>();
    playersList = new ArrayList<Player>();
    Scanner br = new Scanner(file);
    this.numOfCol = br.nextInt();
    this.numOfRows = br.nextInt();
    this.nameOfWorld = br.nextLine();
    int health = br.nextInt();
    String targetName = br.nextLine();
    target = new Target(targetName, health, 0);
    this.pet = new PetImpl(br.nextLine(), 0);
    numOfRooms = br.nextInt();
    for (int i = 0; i < numOfRooms; i++) {
      int upperLeftCol = br.nextInt();
      int upperLeftRow = br.nextInt();
      int lowerRightCol = br.nextInt();
      int lowerRightRow = br.nextInt();
      if (upperLeftCol >= this.numOfCol || lowerRightCol >= this.numOfCol
          || upperLeftRow >= this.numOfRows || lowerRightRow >= this.numOfRows) {
        throw new IllegalArgumentException(
            "Coordinates can't be more than or equal to maximum size of the world");
      }
      String nameOfSpace = br.nextLine();
      nameOfSpace.trim();
      SpaceImpl s =
          new SpaceImpl(upperLeftRow, upperLeftCol, lowerRightRow, lowerRightCol, nameOfSpace);
      spaceList.add(s);
      sequence.add(nameOfSpace);
    }

    int numOfItems = br.nextInt();
    int spaceIndex = 0;
    int itemDamage = 0;
    for (int i = 0; i < numOfItems; i++) {
      int[] arr2 = new int[2];
      for (int j = 0; j < 2; j++) {
        arr2[j] = br.nextInt();
        spaceIndex = arr2[0];
        itemDamage = arr2[1];
      }
      String itemName = br.nextLine();
      Thing tempItem = new Item(itemName.trim(), itemDamage, spaceIndex);
      itemList.put(itemName.trim(), tempItem);
      Space s = spaceList.get(spaceIndex);
      s.setItems(itemName.trim());
    }
    this.playerCount = 0;
    currentPlayerIndex = 0;
    this.randNum = randNum;
    this.numOfTurns = numOfTurns;
    this.winner = null;
    source = 0;
    index = 0;
    traversal = new ArrayList<>();
    movePetTraversal();
  }

  @Override
  public void constructWorld() {
    try {
      BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_BGR);
      Graphics2D g = (Graphics2D) image.getGraphics();
      g.setColor(Color.WHITE);
      for (Space space : spaceList) {
        g.drawRect(space.getUpperLeftRow() * 20, space.getUpperLeftCol() * 20,
            space.getLowerRightRow() * 20 - space.getUpperLeftRow() * 20 + 18,
            space.getLowerRightCol() * 20 - space.getUpperLeftCol() * 20 + 18);
        g.setFont(new Font("TimesRoman", Font.ITALIC, 14));
        g.drawString(space.getNameOfSpace(), space.getUpperLeftRow() * 20 + 15,
            space.getUpperLeftCol() * 20 + 20);
      }
      File f = new File("output.png");
      ImageIO.write(image, "png", f);
    } catch (IOException ie) {
      throw new IllegalArgumentException("Unable to write the image into file.");
    }
  }

  /**
   * This method finds the neighbours of the given space.
   *
   * @param space this is a space object.
   * @return list of spaces names
   */
  private List<String> getNeighbours(Space space) {
    if (space == null) {
      throw new IllegalArgumentException("Space shouldn't be empty.");
    }
    int x = space.getUpperLeftRow();
    int y = space.getUpperLeftCol();
    int v = space.getLowerRightRow();
    int w = space.getLowerRightCol();
    List<String> tempList = new ArrayList<String>();
    for (Space spaceObj : spaceList) {
      int p = spaceObj.getUpperLeftRow();
      int q = spaceObj.getUpperLeftCol();
      int r = spaceObj.getLowerRightRow();
      int s = spaceObj.getLowerRightCol();
      if ((v + 1 == p) && !((q >= w) || (y >= s))) {
        tempList.add(spaceObj.getNameOfSpace());
      }
      if ((r + 1 == x) && !((q >= w) || (y >= s))) {
        tempList.add(spaceObj.getNameOfSpace());
      }
      if ((q == w + 1) && !((p >= v) || (x >= r))) {
        tempList.add(spaceObj.getNameOfSpace());
      }
      if ((s == y - 1) && !((p >= v) || (x >= r))) {
        tempList.add(spaceObj.getNameOfSpace());
      }
      this.neighboursList = tempList;
    }
    return this.neighboursList;
  }

  /**
   * The target is moved to the next space based on the index.
   */
  private void moveTarget() {
    if (target.getCurrentSpaceIndex() == numOfRooms - 1) {
      target.setCurrentSpaceIndex(0);
    } else {
      int temp = target.getCurrentSpaceIndex() + 1;
      target.setCurrentSpaceIndex(temp);
    }
  }

  @Override
  public String displayClues() {
    StringBuffer info = new StringBuffer();
    info.append(target.toString());
    info.append(playersList.get(currentPlayerIndex % playerCount).toString());
    boolean flag = playersList.get(currentPlayerIndex % playerCount).getItems().size() > 0;
    if (flag) {
      info.append(" with damages ");
    }
    for (String s : playersList.get(currentPlayerIndex % playerCount).getItems()) {
      info.append(itemList.get(s).getDamage() + ", ");
    }
    if (flag) {
      info.append(" respectively.\n");
    }
    if (pet.getPetLocation()
        == playersList.get(currentPlayerIndex % playerCount).getCurrentSpaceIndex()) {
      info.append("Pet is present in the current room as player.\n");
    }
    return info.toString();
  }

  @Override
  public void addComputerPlayer() {
    String playerName = "computer_" + playerCount;
    int spaceIndex = randNum.getRandomNumber() % (spaceList.size());
    Player p = new PlayerImpl(playerName, spaceIndex, playerCount, User.COMPUTER);
    Space spaceObj = spaceList.get(spaceIndex);
    spaceObj.addPlayersInSpace(p.getPlayerName());
    playersList.add(p);
    playerCount++;
  }

  @Override
  public String checkComputerPlayer() {
    StringBuffer info = new StringBuffer();
    Player p = playersList.get(this.currentPlayerIndex % playerCount);
    if (p.getPlayerType().equals("COMPUTER")) {
      int choice = randNum.getRandomNumber() % 4;
      int randSpace = randNum.getRandomNumber() % numOfRooms;
      List<String> neighbours = getNeighbours(spaceList.get(p.getCurrentSpaceIndex()));
      int i = randNum.getRandomNumber() % (neighbours.size());
      Space nextSpaceObj = null;
      for (Space space : spaceList) {
        if (space.getNameOfSpace().equals(neighbours.get(i))) {
          nextSpaceObj = space;
          break;
        }
      }
      String maxItem = "";
      int d = 0;
      for (String s : p.getItems()) {
        if (itemList.get(s).getDamage() > d) {
          maxItem = s;
          itemList.get(s).getDamage();
        }
      }
      if (target.getCurrentSpaceIndex() == p.getCurrentSpaceIndex() && !isPlayerVisible()) {
        attack(maxItem);
        info.append(String.format("Computer player %s has attacked\n", p.getPlayerName()));
      } else if (choice == 1) {
        lookAround();
        info.append(String.format("Computer player %s has looked around\n", p.getPlayerName()));
      } else if (choice == 2 || p.getItemLimit() == 0) {
        movePet(spaceList.get(randSpace).getNameOfSpace());
        info.append(String.format("Computer player %s moved the pet\n", p.getPlayerName()));
      } else if (spaceList.get(p.getCurrentSpaceIndex()).getItems().isEmpty() || choice == 0) {
        movePlayer(nextSpaceObj.getNameOfSpace());
        info.append(String.format("Computer player %s has been moved.\n", p.getPlayerName()));
      } else if (choice == 3) {
        info.append(String.format("Computer player %s has picked item %s.\n", p.getPlayerName(),
            spaceList.get(p.getCurrentSpaceIndex()).getItems().get(0)));
        pickItem(spaceList.get(p.getCurrentSpaceIndex()).getItems().get(0));
      }
    }
    return info.toString();
  }

  @Override
  public void movePet(String spaceName) {
    if (spaceName == null || "".equals(spaceName.trim())) {
      throw new IllegalArgumentException("Space name should shouldn't be null or empty");
    }
    Space spaceObj = null;
    for (Space space : spaceList) {
      if (space.getNameOfSpace().trim().equals(spaceName.trim())) {
        spaceObj = space;
      }
    }
    if (spaceObj == null) {
      throw new IllegalArgumentException("Space name is invalid.");
    }
    pet.setPetLocation(spaceList.indexOf(spaceObj));
    this.source = pet.getPetLocation();
    index = 0;
    traversal = new ArrayList<>();
    movePetTraversal();
    currentPlayerIndex++;
    moveTarget();
    numOfTurns--;
  }

  @Override
  public void attack(String itemName) {
    Player currentPlayer = playersList.get(currentPlayerIndex % playerCount);
    boolean itemPresent = currentPlayer.getItems().contains(itemName.trim());
    if (!(itemName == null || "".equals(itemName.trim()) || itemPresent)) {
      throw new IllegalArgumentException("Item name is invalid.");
    }
    numOfTurns--;
    if (currentPlayer.getCurrentSpaceIndex() != target.getCurrentSpaceIndex()) {
      moveTarget();
      if (itemPresent) {
        itemList.remove(itemName);
        currentPlayer.removeItem(itemName);
      }
      currentPlayerIndex++;
      movePetDfs();
      throw new IllegalStateException("Can't attack, target is not present in your place.\n");
    }
    if (isPlayerVisible()) {
      moveTarget();
      if (itemPresent) {
        itemList.remove(itemName);
        currentPlayer.removeItem(itemName);
      }
      currentPlayerIndex++;
      movePetDfs();
      throw new IllegalStateException("Can't attack while other players are watching.\n");
    }
    if (itemName == null || "".equals(itemName.trim())) {
      moveTarget();
      target.decreaseHealthBy(1);
      currentPlayerIndex++;
    } else if (itemPresent) {
      target.decreaseHealthBy(itemList.get(itemName.trim()).getDamage());
      moveTarget();
      itemList.remove(itemName);
      currentPlayer.removeItem(itemName.trim());
      currentPlayerIndex++;
    }
    movePetDfs();
    if (target.getHealth() <= 0) {
      winner = currentPlayer.getPlayerName();
    }
  }

  private boolean isPlayerVisible() {
    Space spaceObj =
        spaceList.get(playersList.get(currentPlayerIndex % playerCount).getCurrentSpaceIndex());
    if (spaceObj.getPlayersInSpace().size() > 1) {
      return true;
    }
    if (spaceList.indexOf(spaceObj) == pet.getPetLocation()) {
      return false;
    }
    Space newSpaceObj = null;
    List<String> neighbours = getNeighbours(spaceObj);
    for (String s : neighbours) {
      for (Space space : spaceList) {
        if (space.getNameOfSpace().trim().equals(s.trim())) {
          newSpaceObj = space;
          if (!newSpaceObj.getPlayersInSpace().isEmpty()) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public void addPlayer(String name, String spaceName) {
    if (spaceName == null || "".equals(spaceName.trim()) || name == null
        || "".equals(name.trim())) {
      throw new IllegalArgumentException("Player name,space name and usertype shouldn't be empty.");
    }
    Space spaceObj = null;
    for (Space space : spaceList) {
      if (space.getNameOfSpace().trim().equals(spaceName.trim())) {
        spaceObj = space;
      }
    }
    if (spaceObj == null) {
      throw new IllegalArgumentException("Space name is invalid.");
    }
    PlayerImpl p = new PlayerImpl(name, spaceList.indexOf(spaceObj), playerCount, User.HUMAN);
    spaceObj.addPlayersInSpace(p.getPlayerName());
    playersList.add(p);
    playerCount++;
  }

  @Override
  public void pickItem(String item) {
    if (item == null || "".equals(item.trim())) {
      throw new IllegalArgumentException("Item name is invalid.");
    }
    Player p = playersList.get(this.currentPlayerIndex % playerCount);
    if (spaceList.get(p.getCurrentSpaceIndex()).getItems().isEmpty()) {
      throw new IllegalStateException("Space has no items\n");
    }
    Space tempSpace = spaceList.get(p.getCurrentSpaceIndex());
    List<String> items = new ArrayList<String>();
    items = tempSpace.getItems();
    boolean pickFlag = items.contains(item.trim());
    if (!pickFlag) {
      throw new IllegalArgumentException("Item is not present in the current space.");
    }
    moveTarget();
    numOfTurns--;
    movePetDfs();
    if (p.getItemLimit() >= 1 && pickFlag) {
      tempSpace.removeItem(item.trim());
      p.addItem(item.trim());
      currentPlayerIndex++;
      currentPlayerIndex = currentPlayerIndex % playerCount;
    } else if (p.getItemLimit() == 0) {
      currentPlayerIndex++;
      currentPlayerIndex = currentPlayerIndex % playerCount;
      throw new IllegalStateException("Player doesn't have item limit.");
    }
  }


  @Override
  public void movePlayer(String nextSpaceName) {
    if (nextSpaceName == null || "".equals(nextSpaceName.trim())) {
      throw new IllegalArgumentException("Space name shouldn't be empty.\n");
    } else {
      Space spaceObj = null;
      for (Space space : spaceList) {
        if (space.getNameOfSpace().trim().equals(nextSpaceName.trim())) {
          spaceObj = space;
        }
      }
      if (spaceObj == null) {
        throw new IllegalArgumentException("Given space is invalid.\n");
      }
      Player p = playersList.get(this.currentPlayerIndex % playerCount);
      Space oldSpaceObj = spaceList.get(p.getCurrentSpaceIndex());
      List<String> neighbours = getNeighbours(spaceObj);
      if ((!neighbours.contains(oldSpaceObj.getNameOfSpace()))) {
        throw new IllegalArgumentException("Given space is not in neighbours.\n");
      } else {
        oldSpaceObj.removePlayerFromSpace(p.getPlayerName());
        currentPlayerIndex++;
        p.setCurrentSpaceIndex(spaceList.indexOf(spaceObj));
        currentPlayerIndex = currentPlayerIndex % playerCount;
        spaceObj.addPlayersInSpace(p.getPlayerName());
        moveTarget();
        movePetDfs();
        numOfTurns--;
      }
    }
  }

  @Override
  public String displaySpaceInfo(String s) {
    if (s == null || "".equals(s.trim())) {
      throw new IllegalArgumentException("Space name shouldn't be empty.");
    }
    Space spaceObj = null;
    for (Space space : spaceList) {
      if (space.getNameOfSpace().trim().equals(s.trim())) {
        spaceObj = space;
      }
    }
    List<String> players = spaceObj.getPlayersInSpace();
    StringBuffer info = new StringBuffer();
    info.append("Space is " + s);
    if (!spaceObj.getItems().isEmpty()) {
      info.append(" and its items are " + spaceObj.getItems().toString());
      info.append("\n");
    } else {
      info.append(" with no items \n");
    }
    if (players.isEmpty()) {
      info.append(" with no players." + "\n");
    } else {
      info.append(" and its players are " + players.toString() + ".\n");
    }
    if (pet.getPetLocation() == spaceList.indexOf(spaceObj)) {
      info.append("Pet is present in the current room.\n");
    }
    List<String> neighbours = getNeighbours(spaceObj);
    neighbours.remove(spaceList.get(pet.getPetLocation()).getNameOfSpace());
    info.append("Its neighbours are " + neighbours + "\n");
    return info.toString();
  }

  @Override
  public String lookAround() {
    if (this.playerCount <= 0) {
      throw new IllegalArgumentException("There are no players");
    }
    List<String> items = new ArrayList<String>();
    List<String> neighbours = new ArrayList<String>();
    List<String> players = new ArrayList<String>();
    String s =
        spaceList.get(playersList.get(currentPlayerIndex % playerCount).getCurrentSpaceIndex())
            .getNameOfSpace();
    Space spaceObj = null;
    for (Space space : spaceList) {
      if ((space.getNameOfSpace()).trim().equals(s.trim())) {
        spaceObj = space;
        items = space.getItems();
        neighbours = this.getNeighbours(space);
      }
    }
    StringBuffer info = new StringBuffer();
    info.append(
        "Target location is: " + spaceList.get(target.getCurrentSpaceIndex()).getNameOfSpace()
            + "\n");
    String petLocation = spaceList.get(pet.getPetLocation()).getNameOfSpace();
    if (petLocation.equals(s)) {
      info.append("Pet is present in the current room\n");
    }
    neighbours.remove(petLocation);
    for (Player player : playersList) {
      if (spaceList.get(player.getCurrentSpaceIndex()).getNameOfSpace().trim().equals(s.trim())) {
        players.add(player.getPlayerName());
      }
    }
    info.append("Space is " + s);
    if (!spaceObj.getItems().isEmpty()) {
      info.append(" and its items are ");
      for (String item : items) {
        info.append(item + " with damage " + itemList.get(item).getDamage() + ", ");
      }
      info.append("\n");
    } else {
      info.append(" with no items \n");
    }
    if (players.isEmpty()) {
      info.append(" with no players." + "\n");
    } else {
      info.append(" and its players are " + players.toString() + ".\n");
    }
    Space newSpaceObj;
    info.append("Its neighbours are \n");
    for (String neighbour : neighbours) {
      for (Space space : spaceList) {
        if ((space.getNameOfSpace()).trim().equals(neighbour.trim())) {
          newSpaceObj = space;
          info.append(newSpaceObj.getNameOfSpace() + " with items " + newSpaceObj.getItems()
              + " with players " + newSpaceObj.getPlayersInSpace() + "\n");
        }
      }
    }
    moveTarget();
    this.currentPlayerIndex++;
    movePetDfs();
    numOfTurns--;
    return info.toString();
  }


  @Override
  public String displayPlayerInfo(int i) {
    if (i < 0 || i >= playerCount) {
      throw new IllegalArgumentException("Player index is invalid.");
    }
    StringBuffer info = new StringBuffer();
    Player p = playersList.get(i);
    Space name = spaceList.get(p.getCurrentSpaceIndex());
    info.append(
        "Currently player " + p.getPlayerName() + " is located at " + name.getNameOfSpace());
    if (p.getItems().isEmpty()) {
      info.append(" and not carrying any items.\n");
    } else {
      info.append(" and carrying items ");
      for (String item : p.getItems()) {
        info.append(item + ",");
      }
      info.append(".\n");
    }
    return info.toString();
  }

  @Override
  public String getTurn() {
    return playersList.get(currentPlayerIndex % playerCount).getPlayerName();
  }

  private void movePetDfs() {
    index++;
    index = index % traversal.size();
    pet.setPetLocation(traversal.get(index));
  }

  /**
   * Moving pet in DFS order.
   */
  private void movePetTraversal() {
    adj = new LinkedList[numOfRooms];
    for (int i = 0; i < numOfRooms; ++i) {
      adj[i] = new LinkedList();
    }
    boolean[] visited = new boolean[numOfRooms];
    List<String> neighbours;
    for (int i = 0; i < numOfRooms; i++) {
      neighbours = this.getNeighbours(spaceList.get(i));
      for (String s : neighbours) {
        for (Space space : spaceList) {
          if ((space.getNameOfSpace()).trim().equals(s.trim())) {
            adj[i].add(spaceList.indexOf(space));
          }
        }
      }
    }
    dfsUtil(source, visited);
  }

  /**
   * Checks whether a space is visited or not.
   *
   * @param v       space index.
   * @param visited boolean array whether is visited or not.
   */
  private void dfsUtil(int v, boolean[] visited) {
    visited[v] = true;
    traversal.add(v);
    Iterator<Integer> i = adj[v].listIterator();
    while (i.hasNext()) {
      int n = i.next();
      if (!visited[n]) {
        dfsUtil(n, visited);
        traversal.add(v);
      }
    }
  }

  @Override
  public boolean isGameOver() {
    return getWinner() != null || numOfTurns == 0;
  }

  @Override
  public String getWinner() {
    return winner;
  }
}