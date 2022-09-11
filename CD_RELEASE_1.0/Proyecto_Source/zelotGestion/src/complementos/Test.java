package complementos;

import java.awt.Frame;

public class Test {
	private static DialogLoad dialogLoad = null;
	private static Thread thread1 = null;
	private static Thread thread2 = null;
	private static Boolean marca = false;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			thread1 = null;
			thread2 = null;
			if(dialogLoad != null){
				dialogLoad.dispose();
				dialogLoad.setVisible(false);
				dialogLoad = null;
			}
			dialogLoad = new DialogLoad(new Frame(), "Prueba");

			thread1 = new Thread(){
				public void run(){
					System.out.println("Comienzo hilo "+thread1.getName());
					int i=0;
					while(i<100){
						i++;
						try {
							sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Procesando hilo nombre "+thread1.getName()+" "+i);
					}
					System.out.println("Interrumple hilo "+thread1.getName());
					thread1.interrupt();
					dialogLoad.setVisible(false);
					marca = true;
				}
			};

			thread2 = new Thread(){
				public void run(){
					System.out.println("Comienzo hilo "+thread2.getName());
					thread1.start();
					//if(jasperViewer != null)jLabelLoad.setVisible(false);
					if(marca)dialogLoad.setVisible(false);
					while(!marca){
						try {
							sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("ejecutando hilo "+thread2.getName());
						if(marca){
							//jLabelLoad.setVisible(false);
							dialogLoad.setVisible(false);
							dialogLoad.dispose();
							System.out.println("Interrumple hilo "+thread2.getName());
							thread2.interrupt();

						}
					}

				}
			};

			//jLabelLoad.setVisible(true);
			dialogLoad.setVisible(true);
			thread2.start();
			//System.out.println("Finalizo");

		}catch(Exception es){
			es.printStackTrace();
		}

	}

}
