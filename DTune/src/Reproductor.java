import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Reproductor{
	
	public static void ReproducirCancion(String ruta) {
		try{
		    FileInputStream fis = new FileInputStream(ruta);
		    Player playMP3 = new Player(fis);
		    playMP3.play();
		}
		catch(Exception exc){
		    exc.printStackTrace();
		    System.out.println("No se ha podido reproducir la canción");
		}
		
	}
	
                          
	}


