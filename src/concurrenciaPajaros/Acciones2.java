package concurrenciaPajaros;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Acciones2 {
	private ReentrantLock lock = new ReentrantLock();
    private Condition esperarCanto = lock.newCondition();
	Semaphore cantaLoro = new Semaphore(1);
	Semaphore cantaPeriquito = new Semaphore(1);
	Semaphore cantaGorrion = new Semaphore(1);
	
	public void cantar(int num,String tipo) throws InterruptedException {
		
        	if(tipo.equals("gorrion")) {
        		cantaGorrion.acquire();
        		System.out.println("esta cantando el pajaro "+ num + " que es de tipo " + tipo);
        	}else if(tipo.equals("loro")) {
        		cantaLoro.acquire();
        		System.out.println("esta cantando el pajaro "+ num + " que es de tipo " + tipo);
        	}else if(tipo.equals("periquito")) {
        		cantaPeriquito.acquire();
        		System.out.println("esta cantando el pajaro "+ num + " que es de tipo " + tipo);
        	}  		
	}
	
	public void dejarCantar(int num,String tipo) {
		
		if(tipo.equals("gorrion")) {
    		cantaGorrion.release();
    		System.out.println("El pajaro "+ num + " que es de tipo " + tipo +"deja de cantar");
    	}else if(tipo.equals("loro")) {
    		cantaLoro.release();
    		System.out.println("El pajaro "+ num + " que es de tipo " + tipo +"deja de cantar");
    	}else if(tipo.equals("periquito")) {
    		cantaPeriquito.release();
    		System.out.println("El pajaro "+ num + " que es de tipo " + tipo +"deja de cantar");
    	}
	}
}
