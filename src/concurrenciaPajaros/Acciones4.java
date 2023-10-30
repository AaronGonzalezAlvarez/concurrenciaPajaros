package concurrenciaPajaros;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 *     4) Un adiestrador muy bueno ha conseguido que los pájaros:
        a. Nunca cantan a la vez (independientemente de la raza del pájaro)
        b. Los pájaros se preparan para cantar, pero no empiezan hasta que su adiestrador les da el visto bueno 
        (el adiestrador elige uno cualquiera para empezar). Como idea, el adiestrador puede esperar hasta que todos
         los pájaros estén preparados para cantar (ejemplo: espera 8 segundos antes de empezar).
        c. Cuando un pájaro de una especie termina de cantar da el turno a un pájaro cualquiera de otra especie
         (se elige aleatoriamente) siempre que haya uno de ellos esperando, si no hay ninguno de otra especie le
          da el turno a otro pájaro de su propia especie. Bonus si haces el código de forma que funcione con 3, 5 o 1000 razas de pajaros.
 * */

public class Acciones4 {
	private int canto = 0;
	private ReentrantLock lock = new ReentrantLock();
    private Condition esperarCanto = lock.newCondition();
    private Condition adiestradorDarOrdenCanto = lock.newCondition();
    
    public void alaEspera(int num,String tipo) throws InterruptedException {
    	lock.lock();
        try {      		
        	System.out.println("Pajaro "+ num + " que es de tipo " + tipo +" a la espera de la orden");  
        	adiestradorDarOrdenCanto.await();        			
        } finally {
            lock.unlock();
        }
    }
    
    public void AdiestradorPuedeCantarPajaro(int num,String tipo) throws InterruptedException {   	
    	System.out.println("Pajaro "+ num + " que es de tipo " + tipo +" y el adiestrador dice que puedes cantar");
    	adiestradorDarOrdenCanto.signal();
	    
    }
	
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

