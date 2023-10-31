package concurrenciaPajaros;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Acciones5 {
	private boolean cantar = false;
	private ReentrantLock lock = new ReentrantLock();
    private Condition esperarLoro = lock.newCondition();
    private Condition esperarGorrion = lock.newCondition();
    private Condition esperarPeriquito = lock.newCondition();
    private Random random = new Random();
	
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
		//System.out.println("El pajaro "+ num + " que es de tipo " + tipo +" deja de cantar");
		if(tipo.equals("gorrion")) {	
			int numeroAleatorio = random.nextInt(2);
			if(numeroAleatorio == 0 && hayHilosEsperandoEnCondicion(esperarLoro)) {
				esperarLoro.signal();
			}else if(numeroAleatorio == 1 && hayHilosEsperandoEnCondicion(esperarPeriquito)) {
				esperarPeriquito.signal();
			}else if(hayHilosEsperandoEnCondicion(esperarGorrion)){
				esperarGorrion.signal();
			}			
    	}else if(tipo.equals("loro")) {		
    		int numeroAleatorio = random.nextInt(2);
    		if(numeroAleatorio == 0 && hayHilosEsperandoEnCondicion(esperarGorrion)) {
    			esperarGorrion.signal();
			}else if(numeroAleatorio == 1 && hayHilosEsperandoEnCondicion(esperarPeriquito)) {
				esperarPeriquito.signal();
			}else if(hayHilosEsperandoEnCondicion(esperarLoro)){
				esperarLoro.signal();
			}
    	}else if(tipo.equals("periquito")) {
    		int numeroAleatorio = random.nextInt(2);
    		if(numeroAleatorio == 0 && hayHilosEsperandoEnCondicion(esperarLoro)) {
				esperarLoro.signal();
			}else if(numeroAleatorio == 1 && hayHilosEsperandoEnCondicion(esperarGorrion)) {
				esperarGorrion.signal();
			}else if(hayHilosEsperandoEnCondicion(esperarPeriquito)){
				esperarPeriquito.signal();
			}
    	}
		System.out.println("esperarPeriquito: " + hayHilosEsperandoEnCondicion(esperarPeriquito));
		System.out.println("esperarGorrion: " + hayHilosEsperandoEnCondicion(esperarGorrion));
		System.out.println("esperarLoro: " + hayHilosEsperandoEnCondicion(esperarLoro));
		lock.unlock();		
	}
	
	private boolean hayHilosEsperandoEnCondicion(Condition condition) {
        return lock.hasWaiters(condition);
    }
}
