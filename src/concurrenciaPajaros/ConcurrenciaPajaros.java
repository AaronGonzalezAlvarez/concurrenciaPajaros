package concurrenciaPajaros;



public class ConcurrenciaPajaros {

	public static void main(String[] args) {
		Acciones4 b = new Acciones4();
		int z =0;
		Adiestrador adiestrador = new Adiestrador(b);
		adiestrador.start();
		for (int i = 0; i < 10; i++) {			
			Pajaro pajaro = (new Pajaro(z, b,"periquito"));	
			pajaro.start();
			z++;
			Pajaro pajaro2 = (new Pajaro(z, b,"loro"));	
			pajaro2.start();
			z++;
			Pajaro pajaro3 = (new Pajaro(z, b,"gorrion"));
			pajaro3.start();
			z++;
			
		}
		
		/*for (int i = 0; i < 10; i++) {
			pajaro.start();
		}
		
		for (int i = 0; i < 10; i++) {
			pajaro.start();
		}*/
	}

}
