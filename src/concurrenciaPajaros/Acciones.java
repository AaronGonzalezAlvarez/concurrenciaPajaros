package concurrenciaPajaros;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Acciones {
	private int canto = 0;
	private ReentrantLock lock = new ReentrantLock();
    private Condition esperarCanto = lock.newCondition();
	
	public void cantar(int num,String tipo) throws InterruptedException {
		
		lock.lock();
        try {
        	while ((canto == 1)) {
        		
        		esperarCanto.await();
        	}
        	canto++;
        	System.out.println("esta cantando el pajaro "+ num + " que es de tipo " + tipo);
        	
        } finally {
            lock.unlock();
        }				
		
	}
	
	public void dejarCantar(int num,String tipo) {
		lock.lock();
        try {
		System.out.println("pajaro "+ num + " deja de cantar que es de tipo " + tipo);
		canto--;
		esperarCanto.signal();
		} finally {
	        lock.unlock();
	    }
	}

}
