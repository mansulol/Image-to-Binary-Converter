package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class fichero_a_binario {
	
	private DataInputStream f_origen;
	private DataOutputStream f_destino;
	
	public fichero_a_binario(File ficheroOrigen, File carpetaDestino) {
		try {
			
			f_origen = new DataInputStream(new FileInputStream(ficheroOrigen.getPath()));
			f_destino = new DataOutputStream( new FileOutputStream(carpetaDestino.getPath() ));
			byte[] vec = new byte[100000000];
			int cuantas = 0;
			
			do {
				
				cuantas = f_origen.read(vec);
				if(cuantas == -1) {
					break;
				}
				
				f_destino.write(vec, 0, cuantas);
				
			}while(true);
			
			f_origen.close();
			f_destino.close();
			System.out.println("Archivo "+ficheroOrigen.getName()+" convertido exitosamente !");
			
		}
		catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado, "+e);
		}
		catch(IOException e) {
			System.out.println("Error en el E/S, "+e);
		}
	}
	
}
