
import java.util.HashMap;


public class Player {
   
    String name;
    int score,x,y;
    String coordinateToStr;
    int i=0;
    
    
    public HashMap<String,String> foodPositions;
    
    Player( String name,int score,int x,int y){
        this.score = score;
        this.name = name;
        this.x = x;
        this.y = y;
        coordinateToStr = x+","+y;
    }
    
    // move the player according to pressed arrow key  
    void walk(int key ){
         
        switch (key) {
            case 37:
                x = x - 1;
                break;
            case 38:
                y = y - 1 ;
                break;
            case 39:
                x = x + 1;
                break;
            case 40:
                y = y + 1;
                break;
            default:
                break;
        }
        
       // player movement logic at the edges   
        if (x<0) x = 44;
        else if (x>44) x = 0;
        
        if (y<0) y = 44;
        else if (y>44) y = 0;
        
        
        coordinateToStr = x + "," + y;    
    }

    // give marks for players when it eat foods
    void updateMarks(String coordinates){
        
        if(foodPositions.containsKey(coordinates)){
            switch (foodPositions.get(coordinates)) {
                case "R":
                    score=score+1;
                    break;
                case "G":
                    score=score+2;
                    break;
                case "B":
                    score=score+4;
                    break;
                default:
                    break;
            }
        }
    }
    
    
    // re initiate players position when collition is happened
    public void goHome(int playerId){
        
        switch (playerId){
            case 0:
                this.x = 0;
                this.y = 0;
                this.score-=3;   
                break;
                
            case 1:
                this.x = 44;
                this.y = 0;
                this.score-=3;   
                break;
                
            case 2:
                this.x = 0;
                this.y = 44;
                this.score-=3;   
                break;
                
            case 3:
                this.x = 44;
                this.y = 44;
                this.score-=3;   
                break;
        }
        
    }
    
    @Override
    public String toString(){
        
        return "[\""+name+"\","+score+","+x+","+y+"]";
    }
    
}
