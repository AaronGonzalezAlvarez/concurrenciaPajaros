package concurrenciaPajaros;

public class Acciones {
	
	/*
	 * Pájaros no educados, cantan cuando pueden, es decir, pueden cantar más de uno a la vez.
	 * */
	
	public void cantar(int num,String tipo) throws InterruptedException {
		System.out.println("esta cantando el pajaro "+ num + " que es de tipo " + tipo);				
	}
	
	public void dejarCantar(int num,String tipo) {
		System.out.println("pajaro "+ num + " deja de cantar que es de tipo " + tipo);
	}

}
