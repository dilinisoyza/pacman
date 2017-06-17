
import java.util.HashMap;
import java.util.Random;



public class UpdateCanves {
    
    Player[] player = new Player[4];
    
    public HashMap<String,String> foodPositions = new HashMap<>();
    public HashMap<String,String> foodData = new HashMap<>();
    
    
    
    
    private final int numberOfFoods = 12;
    private int colorCode = 0;
    
    public UpdateCanves(){
        for(int i = 0; i <numberOfFoods; i++){
            if(colorCode==3) colorCode=0;
            foodGenerate(colorCode);      // generate given number of foods according to color code
            ++colorCode;             
        }
           
    }

    
    // generate foods in random positions
    void foodGenerate(int colorCode){
              
        String[] color = new String[3];
        color[0] = "B";
        color[1] = "R";
        color[2] = "G";
        
        int x,y;
        String foodColor = color[colorCode];
        
        Random r = new Random();
        x =  r.nextInt(40);
        y = r.nextInt(40);
       
        
        foodPositions.put(x+","+y, foodColor);
        foodData.put(x+","+y, "[\""+foodColor+"\", "+x+", "+y+"]");
        
    }
    
    // remove foods from grid when player eats a food
    void eatFood(String coordinates){
        
        
        if(foodData.containsKey(coordinates)){
            foodData.remove(coordinates);
            foodPositions.remove(coordinates);    
        }
               
    }
    
   // reinitiate player position when collition is happen
    void colideHandle(Player [] player){
        this.player=player;
        
        for(int i=0;i<4;i++){          
            for(int j=0;j<4;j++){
                
                if(player[i]!=player[j]){
                    if(player[i].coordinateToStr.equals(player[j].coordinateToStr)){
                        player[i].goHome(i);
                        player[j].goHome(j);
                        break;
                    }
                }

            }
            break;
                    
        }
    
    }
    
    
    @Override
    public String toString(){      
        String foods=null;
        int count=0;
        
        if(foodData.isEmpty()){
            return "{ \"DOTS\":"+foods+",";
        }
        else{
        for(String key: this.foodData.keySet()){
            if(count==0) foods = "{ \"DOTS\": ["+foodData.get(key);
            foods = foods + ","+ foodData.get(key) ;
            count++;
        }
        foods = foods + " ],";
        return foods;
        }
    }
    
}
