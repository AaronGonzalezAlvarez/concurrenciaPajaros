package concurrenciaPajaros;

public class Adiestrador extends Thread {
	Acciones4 accion;
	
	public Adiestrador(Acciones4 accion) {
		this.accion = accion;
	}
	
	public void run() {
		try {
			Thread.sleep(10*1000);
			accion.AdiestradorPuedeCantarPajaro();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
