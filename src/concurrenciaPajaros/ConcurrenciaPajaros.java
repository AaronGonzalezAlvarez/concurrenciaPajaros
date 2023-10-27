package concurrenciaPajaros;



public class ConcurrenciaPajaros {

	public static void main(String[] args) {
		Acciones b = new Acciones();
		for (int i = 0; i < 10; i++) {			
			Pajaro pajaro = (new Pajaro(i, b,"periquito"));			
			Pajaro pajaro2 = (new Pajaro(i, b,"loro"));			
			Pajaro pajaro3 = (new Pajaro(i, b,"gorrion"));
			pajaro.start();
			pajaro2.start();
			pajaro3.start();
		}
		
		/*for (int i = 0; i < 10; i++) {
			pajaro.start();
		}
		
		for (int i = 0; i < 10; i++) {
			pajaro.start();
		}*/
	}

}
