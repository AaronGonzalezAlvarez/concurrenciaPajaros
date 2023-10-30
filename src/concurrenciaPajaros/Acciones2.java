package concurrenciaPajaros;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Acciones2 {
	private int cantoloro = 0;
	private int cantoperiquito = 0;
	private int cantogorrion = 0;
	private ReentrantLock lock = new ReentrantLock();
    private Condition esperarCanto = lock.newCondition();
	
	public void cantar(int num,String tipo) throws InterruptedException {
		
		lock.lock();
        try {
        	if(tipo == "loro") {
        		while ((cantoloro == 1)) {            		
            		esperarCanto.await();
            	}
        		cantoloro++;
            	System.out.println("esta cantando el pajaro "+ num + " que es de tipo " + tipo);
        	} else if(tipo == "periquito") {
        		while ((cantoperiquito == 1)) {            		
            		esperarCanto.await();
            	}
        		cantoperiquito++;
            	System.out.println("esta cantando el pajaro "+ num + " que es de tipo " + tipo);
        	} else if (tipo == "gorrion") {
        		while ((cantogorrion == 1)) {            		
            		esperarCanto.await();
            	}
        		cantogorrion++;
            	System.out.println("esta cantando el pajaro "+ num + " que es de tipo " + tipo);
        	}
        	
        } finally {
            lock.unlock();
        }				
		
	}
	
	public void dejarCantar(int num,String tipo) {
		lock.lock();
        try {
        	if(tipo == "loro") {          		
        		System.out.println("pajaro "+ num + " deja de cantar que es de tipo " + tipo);
        		cantoloro--;
        	} else if(tipo == "periquito") {
        		System.out.println("pajaro "+ num + " deja de cantar que es de tipo " + tipo);
        		cantoperiquito--;
        	} else if (tipo == "gorrion") {
        		System.out.println("pajaro "+ num + " deja de cantar que es de tipo " + tipo);
        		cantogorrion--;
        	}
        	esperarCanto.signal();
		} finally {
	        lock.unlock();
	    }
	}

}
