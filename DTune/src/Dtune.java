import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;




public class Dtune extends VentanaCrearCuenta {
	//Creacion del mapa
		
			private static HashMap<String, Usuario> usuarios = new HashMap<>();
			
			
			
			
			
			//metodo de añadir
			public static boolean addUsuario(Usuario x) {
				boolean repetido;
				
				repetido = usuarios.containsKey(x.getNombre());
				System.out.println(x.getNombre() + " " + x + " " + usuarios);
				if(repetido==false) {
					usuarios.put(x.getNombre(), x);
					System.out.println("usuario añadido" + usuarios); 
					guardarUsuariosEnFichero();
				}else {
					System.out.println("usuario no añadido");
				}
				
				return repetido;
			}
			
			
			
			
			//metodo de buscar
			public static Usuario buscarUsuario(String Nombre) {
				return usuarios.get(Nombre);
			}
			
			//metodo de mostrar
			public static void mostrarUsuarios() {
				for(Usuario x: usuarios.values())
					System.out.println(x);
			}
			
			
			
			//metodo de guardar los usuarios en el fichero para recordardos
			public static void guardarUsuariosEnFichero() {
				ObjectOutputStream oos = null;
				
				try {
					oos = new ObjectOutputStream(new FileOutputStream("USUARIOS.DAT"));
					oos.writeObject(usuarios);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(oos!=null)
						try {
							oos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
				
			}
			
			
			
			
			
			// metodo de cargar los usuarios ya guardados en el fichero
			public static void cargarUsuariosDeFichero() {
				ObjectInputStream ois = null;
				File f = new File("USUARIOS.DAT");
				if(f.exists()) {
					try {
						ois = new ObjectInputStream(new FileInputStream(f));
						usuarios = (HashMap<String, Usuario>) ois.readObject();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} finally {
						if(ois!=null)
							try {
								ois.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
					}
				}
			}
}

