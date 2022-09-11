/*
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
package main;

import javax.swing.JFrame;

import org.jvnet.substance.SubstanceLookAndFeel;

import presentacion.LoginUsuario;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.BusinessBlackSteelSkin");
		SubstanceLookAndFeel.setCurrentButtonShaper("org.jvnet.substance.button.StandardButtonShaper");
		SubstanceLookAndFeel.setCurrentGradientPainter("org.jvnet.substance.painter.SpecularGradientPainter");
		SubstanceLookAndFeel.setToUseConstantThemesOnDialogs(true);
		LoginUsuario dialogLoginUsuario = new LoginUsuario();
		//dialogLoginUsuario.setModal(true);
		dialogLoginUsuario.setVisible(true);
		/*WinPrincipal winPrincipal = new WinPrincipal();
		winPrincipal.loginSistema(dialogLoginUsuario.getUsuarioActivo());
		winPrincipal.setVisible(true);*/

}
}
