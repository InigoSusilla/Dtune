import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Reproductor{
	static Player playMP3;
	public static void ReproducirCancion(String ruta) {
		try{
		    FileInputStream fis = new FileInputStream(ruta);
		    playMP3 = new Player(fis);
		    playMP3.play();
		}
		catch(Exception exc){
		    exc.printStackTrace();
		    System.out.println("No se ha podido reproducir la canción");
		}
		
	}
	public static void PararCancion() {
		playMP3.close();
	}
	
                          
	}


