package concurrenciaPajaros;


public class Pajaro extends Thread {

	int i;
	Acciones4 accion;
	String tipo;

	public Pajaro(int i, Acciones4 accion, String tipo) {
		this.i = i;
		this.accion = accion;
		this.tipo = tipo;
	}

	
	/*public void run() {
		try {
			Thread.sleep(5*1000);					
			accion.cantar(i,tipo);
			Thread.sleep(2*1000);
			accion.dejarCantar(i, tipo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}*/
	
	//apartado 4
	public void run() {
		try {
			Thread.sleep(8*1000);
			accion.alaEspera(i, tipo);
			Thread.sleep(5*1000);					
			accion.cantar(i,tipo);
			Thread.sleep(2*1000);
			accion.dejarCantar(i, tipo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
