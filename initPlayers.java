

/**
 *
 * @author User
 */
public class initPlayers {
    
    private int numberOfPlayers;
    
    
    public initPlayers(int numOfPlayers){
        numberOfPlayers = numOfPlayers;
    }
    
    // initiate the players by giving initial score, ans the position
    public Player[] createPlayers(Player[] pl){
        
        pl[0] = new Player("P1", 0, 0, 0);
        pl[1] = new Player("P2", 0, 44, 0);
        pl[2] = new Player("P3", 0, 0, 44);
        pl[3] = new Player("P4", 0, 44, 44);
        
        return pl;
    }
    
}
