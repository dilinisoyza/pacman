
/**** Servlet class for pacman game   ****/
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/game", "/UpdateGame"})
public class UpdateGame extends HttpServlet {
   
    
    
    
    UpdatePlayer playerData;
    UpdateCanves canves = new UpdateCanves();
    
    String canvesData = canves.toString();
    public HashMap<String,String> updatedFoodPositions = canves.foodPositions;

    public String gameData;
    
    
    private static int numOfPlayers = 4;
    static int count;             // number of players
    Player[] players = new Player[numOfPlayers];
    
    
    @Override
    public void init() throws ServletException {
       initPlayers pl = new initPlayers(numOfPlayers);        
       players = pl.createPlayers(players);
        count = 0;
    }

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream;charset=UTF-8");

        try (final PrintWriter out = response.getWriter()) {
            Object attribute = request.getSession().getAttribute("player");        // get 4 sessions and assign attribute to new sessions
            if (attribute == null) {
                if (count < numOfPlayers) {
                    request.getSession().setAttribute("player", players[count]);
                    ++count;
                }
            }
            

            while (!Thread.interrupted()) {
                synchronized (this) {
                     
                    playerData = new UpdatePlayer(players[0],players[1],players[2],players[3]);   // get jason format of player data
                    gameData = canvesData + playerData;         // combine player data and canves data
                    
                    out.println("data: " + gameData);       // send json formated game data to script.js file
                    out.println();
                    out.flush();
                 
                    wait();
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            synchronized (this) {
                
                if(count==4){   // game is begin only when 4 players are connected
                    String key = request.getParameter("keypress");
                     //String key = request.getReader().readLine();  

                    Player player = (Player) request.getSession().getAttribute("player");           // identify 4 players separately

                    player.foodPositions = updatedFoodPositions;        // get the updated data of food positions
                    int k = Integer.valueOf(key);

                    player.walk(k); // update the position of the player according to pressed key
                    player.updateMarks(player.coordinateToStr); // update player marks according to pressed key
                    canves.eatFood(player.coordinateToStr);  // update canves according to pressed key

                    canvesData = canves.toString();
                    updatedFoodPositions  = canves.foodPositions;       // again get updated food data after pressing key
                    canves.colideHandle(players);  // reinitiate player positions if collition is happened
                    notifyAll();
                }
            }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
