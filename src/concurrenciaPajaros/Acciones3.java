package concurrenciaPajaros;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/*
 * Los pájaros son más educados todavía y cantan por turnos, no cantando nunca a la vez, independientemente de la raza.
 * */
public class Acciones3 {
	Semaphore semaforo = new Semaphore(1);
	
	public void cantar(int num,String tipo) throws InterruptedException {
        	
			semaforo.acquire();
        	System.out.println("El pajaro "+ num + " que es de tipo " + tipo + " y voy a cantar");				
		
	}
	
	public void dejarCantar(int num,String tipo) {
		System.out.println("El pajaro "+ num + " que es de tipo " + tipo +" y dejo de cantar");
		semaforo.release();
	}

}