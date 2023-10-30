package concurrenciaPajaros;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Acciones5 {
	private boolean cantar = false;
	private ReentrantLock lock = new ReentrantLock();
    private Condition esperarLoro = lock.newCondition();
    private Condition esperarGorrion = lock.newCondition();
    private Condition esperarPeriquito = lock.newCondition();
	
	public void cantar(int num,String tipo) throws InterruptedException {	
		lock.lock();
		if(tipo.equals("gorrion")) {
			while(cantar) {
				esperarGorrion.await();
			}
    	}else if(tipo.equals("loro")) {
    		while(cantar) {
    			esperarLoro.await();
			}
    	}else if(tipo.equals("periquito")) {
    		while(cantar) {
    			esperarPeriquito.await();
			}
    	}
		cantar=true;
		System.out.println("esta cantando el pajaro "+ num + " que es de tipo " + tipo);
		lock.unlock();
	}
	
	public void dejarCantar(int num,String tipo) {
		lock.lock();
		cantar=false;
		System.out.println("El pajaro "+ num + " que es de tipo " + tipo +" deja de cantar");
		if(tipo.equals("gorrion")) {	
			if(hayHilosEsperandoEnCondicion(esperarLoro)) {
				esperarLoro.signal();
			}else if(hayHilosEsperandoEnCondicion(esperarPeriquito)) {
				esperarPeriquito.signal();
			}else if(hayHilosEsperandoEnCondicion(esperarGorrion)){
				esperarGorrion.signal();
			}			
    	}else if(tipo.equals("loro")) {		
    		if(hayHilosEsperandoEnCondicion(esperarGorrion)) {
    			esperarGorrion.signal();
			}else if(hayHilosEsperandoEnCondicion(esperarPeriquito)) {
				esperarPeriquito.signal();
			}else if(hayHilosEsperandoEnCondicion(esperarLoro)){
				esperarLoro.signal();
			}
    	}else if(tipo.equals("periquito")) {
    		if(hayHilosEsperandoEnCondicion(esperarLoro)) {
				esperarLoro.signal();
			}else if(hayHilosEsperandoEnCondicion(esperarGorrion)) {
				esperarGorrion.signal();
			}else if(hayHilosEsperandoEnCondicion(esperarPeriquito)){
				esperarPeriquito.signal();
			}
    	}
		lock.unlock();		
	}
	
	private boolean hayHilosEsperandoEnCondicion(Condition condition) {
        return lock.hasWaiters(condition);
    }
}
