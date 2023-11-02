package concurrenciaPajaros;

import java.util.HashMap;
import java.util.Map;
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
    private Map<String, Condition> raza = new HashMap<String, Condition>();
	
	public void cantar(int num,String tipo) throws InterruptedException {	
		lock.lock();
		
		if(!raza.containsKey(tipo)) {
    		raza.put(tipo,  lock.newCondition());
    	}
		
		for (Map.Entry<String, Condition> entry : raza.entrySet()) {
            String key = entry.getKey();
            Condition value = entry.getValue();
            if(key.equals(tipo)) {
            	while(cantar) {
    				try {
						value.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			}
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
		/*if(tipo.equals("gorrion")) {	
			int numeroAleatorio = random.nextInt(2);
			if(numeroAleatorio == 0 && hayHilosEsperandoEnCondicion(esperarLoro)) {
				esperarLoro.signal();
			}else if(numeroAleatorio == 1 && hayHilosEsperandoEnCondicion(esperarPeriquito)) {
				esperarPeriquito.signal();
			}else if(hayHilosEsperandoEnCondicion(esperarGorrion)){
				esperarGorrion.signal();
			}			
    	}*/
		
		int numRazas = raza.size();
		Random random = new Random();
		String proximaRaza="";
		boolean salirDelBucle = false;
		int currentIndex = 0;
		int totalEntries = raza.size();
		for (Map.Entry<String, Condition> entry : raza.entrySet()) {
            String key = entry.getKey();
            Condition value = entry.getValue();

            if (!salirDelBucle && !key.equals(tipo) && hayHilosEsperandoEnCondicion(value)) {
                value.signal();
                salirDelBucle = true;
            }           
            currentIndex++;

            if (currentIndex == totalEntries && !salirDelBucle) {
            	raza.get(tipo).signal();
            }
		}
		lock.unlock();		
	}
	
	private boolean hayHilosEsperandoEnCondicion(Condition condition) {
        return lock.hasWaiters(condition);
    }
}
