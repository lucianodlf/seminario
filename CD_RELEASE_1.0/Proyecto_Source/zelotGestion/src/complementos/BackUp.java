/*<Copyright© 2011 Luciano Delfino>
*****************************************************************************************
This file is part of Zelot Gestion ERP.

    Zelot Gestion ERP is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Zelot Gestion ERP is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Zelot Gestion ERP.  If not, see <http://www.gnu.org/licenses/>.

*****************************************************************************************
Este archivo forma parte de Zelot Gestión ERP.

     Zelot Gestion ERP es un software libre: usted puede redistribuirlo y / o modificar
     bajo los términos de la GNU Lesser General Public License publicada por
     la Free Software Foundation, bien de la versión 3 de la Licencia, o
     (a su elección) cualquier versión posterior.

     Zelot Gestión ERP se distribuye con la esperanza de que sea útil,
     pero SIN NINGUNA GARANTÍA, incluso sin la garantía implícita de
     COMERCIALIZACIÓN o IDONEIDAD PARA UN PROPÓSITO PARTICULAR. Consulte la
     GNU Lesser General Public License para más detalles.

     Debería haber recibido una copia de la GNU Lesser General Public License
     junto con Zelot Gestión ERP. Si no, vea <http://www.gnu.org/licenses/>.
******************************************************************************************/
package complementos;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class BackUp {

	    private int BUFFER = 10485760;
	    //para guardar en memmoria
	    private StringBuffer temp = null;
	    //para guardar el archivo SQL
	    private FileWriter  fichero = null;
	    private PrintWriter pw = null;


	 public boolean CrearBackup(String host, String port, String user, String password, String db, String file_backup){
	    boolean ok=false;
	    try{

	        //sentencia para crear el BackUp
	         Process run = Runtime.getRuntime().exec(
	        "mysqldump --host=" + host + " --port=" + port +
	        " --user=" + user + " --password=" + password +
	        " --compact --complete-insert --extended-insert --skip-quote-names" +
	        " --skip-comments --skip-triggers " + db);
	        //se guarda en memoria el backup
	        InputStream in = run.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        temp = new StringBuffer();
	        int count;
	        char[] cbuf = new char[BUFFER];
	        	while ((count = br.read(cbuf, 0, BUFFER)) != -1){
	        		System.out.println("count: "+count);
		        	  temp.append(cbuf, 0, count);
		        }
	        br.close();
	        in.close();
	        /* se crea y escribe el archivo SQL */
	        fichero = new FileWriter(file_backup);
	        pw = new PrintWriter(fichero);
	        pw.println(temp.toString());
	        ok=true;
	   }
	    catch (Exception ex){
	            ex.printStackTrace();
	    } finally {
	       try {
	         if (null != fichero)
	              fichero.close();
	       } catch (Exception e2) {
	           e2.printStackTrace();
	       }
	    }
	    return ok;
	 }

}

