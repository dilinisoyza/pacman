
/**
 *
 * @author User
 */
public class UpdatePlayer {
    
    private String data;
    
    
    // get details of all players and create a json object structure 
    UpdatePlayer(Player p1,Player p2, Player p3, Player p4){
        data =  "\"PLAYERS\": [" + p1.toString() + ", " + p2.toString() + ", " + p3.toString() + ", " + p4.toString() + "] }";
    }
    
    public String toString(){
        return data;
    }
    
}
