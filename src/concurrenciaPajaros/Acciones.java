package concurrenciaPajaros;

public class Acciones {
	
	public void cantar(int num,String tipo) throws InterruptedException {
		System.out.println("esta cantando el pajaro "+ num + " que es de tipo " + tipo);				
	}
	
	public void dejarCantar(int num,String tipo) {
		System.out.println("pajaro "+ num + " deja de cantar que es de tipo " + tipo);
	}

}
