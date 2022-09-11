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
package presentacion;

import gm.clock.ClockPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import logica.CajaLogica;
import logica.EmpresaLogica;
import logica.MovimientoCajaLogica;
import presentacion.Dialog.DialogAbout;
import presentacion.Dialog.DialogBackup;
import presentacion.InternalFrames.InternalFrameConsultaArticulo;
import presentacion.InternalFrames.InternalFrameConsultaFacturacion;
import presentacion.InternalFrames.InternalFrameFacturacion;
import presentacion.InternalFrames.InternalFrameGestorArticulo;
import presentacion.InternalFrames.InternalFrameGestorCliente;
import presentacion.InternalFrames.InternalFrameGestorCondVenta;
import presentacion.InternalFrames.InternalFrameGestorCondicionIva;
import presentacion.InternalFrames.InternalFrameGestorListasPrecio;
import presentacion.InternalFrames.InternalFrameGestorLocalidad;
import presentacion.InternalFrames.InternalFrameGestorProveedores;
import presentacion.InternalFrames.InternalFrameGestorProvincia;
import presentacion.InternalFrames.InternalFrameGestorRubro;
import presentacion.InternalFrames.InternalFrameGestorSubRubro;
import presentacion.InternalFrames.InternalFrameGestorVendedor;
import presentacion.InternalFrames.InternalFrameMovimientoCaja;
import presentacion.InternalFrames.InternalFrameMovimientoCtaCte;
import presentacion.InternalFrames.InternalFrameNotaCredito;
import presentacion.InternalFrames.InternalFrameUsuariosPerfiles;
import presentacion.Reports.InternalFrameAnalisisVenta;
import presentacion.Reports.InternalFrameListadoPrecio;
import presentacion.Reports.InternalFrameReporteVentas;

import complementos.MyDesktopPanel;

import dominio.Caja;
import dominio.Empresa;
import dominio.Permiso;
import dominio.Usuario;

public class WinPrincipal extends JFrame {

	/*implementacion de patron singleton*/
	private static WinPrincipal winPrincipal = null;

	public static WinPrincipal getInstance(){
		if(winPrincipal == null){
			winPrincipal = new WinPrincipal();
		}
		return winPrincipal;
	}

	private static final long serialVersionUID = 1L;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenuSistema = null;
	private JMenu jMenuProveedor = null;
	private JMenu jMenuCaja = null;
	private JMenu jMenuSeguridadMantenimiento = null;
	private JMenu jMenuEstadisticas = null;
	private JMenu jMenuAyuda = null;
	private JMenuItem jMenuItemSistemaSalir = null;
	private JMenuItem jMenuItemClientesGestion = null;
	private JMenuItem jMenuItemGestionProveedor = null;
	private JMenuItem jMenuItemCajaCierre = null;
	private JMenuItem jMenuItemCajaInicio = null;
	private JMenuItem jMenuItemEstadisticasVentas = null;
	private JMenu jMenuInventario = null;
	private JMenuItem jMenuItemGestionUsuarios = null;
	private JMenuItem jMenuItemAyudaAcercaDe = null;
	private JMenuItem jMenuItemArticulos = null;
	private JMenu jMenuClienteCtaCte = null;
	private JMenuItem jMenuOtrosProvincias = null;
	private JMenuItem jMenuOtrosLocalidades = null;
	private JMenuItem jMenuOtrosVendedores = null;
	private JMenuItem jMenuOtrosIva = null;
	private JMenuItem jMenuItemInventarioRubros = null;
	private JMenuItem jMenuItemInventarioSubRubros = null;
	private JMenuItem jMenuOtrosTipoDocumento = null;
	private MyDesktopPanel jDesktopPane = null;
	private JMenuItem jMenuOtrosCondicionVenta = null;
	private JMenuItem jMenuOtrosListasPrecios = null;
	private JMenu jMenuVentas = null;
	private JMenuItem jMenuItemFacturacion = null;
	private JMenuItem jMenuItemConsultaArticulo = null;
	private JMenuItem jMenuItemAyudaTemasAyuda = null;
	private JMenu jMenuItemSistemaPreferencia = null;
	private JLabel jLabelRazonSocial = null;
	private JLabel jLabelNomFantasia = null;
	private JLabel jLabelNroPtoVta = null;
	private JLabel jLabelFechaInicioActiv = null;
	private JLabel jLabelDireccion = null;
	private JLabel jLabelLocalidad = null;
	private JLabel jLabelProvincia = null;
	private JLabel jLabelCodPos = null;
	private JLabel jLabelTipoDoc = null;
	private JLabel jLabelDocNro = null;
	private JLabel jLabelCondicionIva = null;
	private JLabel jLabelTelefono1 = null;
	private JLabel jLabelTelefono2 = null;
	private JLabel jLabelEmail = null;


	private EmpresaLogica empresaLogica = EmpresaLogica.getInstance();  //  @jve:decl-index=0:
	private JLabel jLabelWeb = null;
	private JMenuItem jMenuItemClienteCtaCteConsulta = null;
	private JMenuItem jMenuItemBackupDataBase = null;
	private JMenuItem jMenuItemCajaMovimientoGestion = null;
	private JMenu jMenuListadosReportes = null;
	private JMenuItem jMenuItemListadoPrecios = null;
	private JMenuItem jMenuItemReporteVentas = null;
	private JButton jButtonClientes = null;
	private JButton jButtonArticulos = null;
	private JButton jButtonFacturacion = null;
	private JButton jButtonCobranzas = null;
	private JButton jButtonCaja = null;

	private Usuario usuarioActivo = null;  //  @jve:decl-index=0:
	private Iterator<Permiso> permisos = null;  //  @jve:decl-index=0:
	private LoginUsuario dialogLoginUsuario = null;
	private JMenuItem jMenuItemDesconectar = null;
	private JMenuItem jMenuItemConectar = null;
	private static Caja cajaDiaria = null;
	private CajaLogica cajaLogica = CajaLogica.getInstance();  //  @jve:decl-index=0:
	private MovimientoCajaLogica movimientoCajaLogica = MovimientoCajaLogica.getInstance();
	private Process pcalc = null;

	private JLabel jLabelFecha = null;
	private JMenuItem jMenuItemNotaCredito = null;
	private JMenuItem jMenuItemFacturacionConsulta = null;
	private JToolBar jJToolBarBar = null;
	private JButton jButtonNotaCredito = null;
	private JButton jButtonConsultaFactura = null;
	private JButton jButtonExit = null;
	private JInternalFrame jInternalFrameClock = null;
	private JButton jButtonCalculadora = null;
	/**
	 * This is the default constructor
	 */

	private WinPrincipal() {
		//super();
		initialize();
		System.out.println("OPEN "+this.getClass().getName());
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		Locale.setDefault(new Locale("AR"));
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setPreferredSize(new Dimension(1024, 768));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon(getClass().getResource("/ZelotGestionLogo2.png")).getImage());
		//this.setBackground(SystemColor.control);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJDesktopPane());
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setTitle("Zelot Gestion - Raul Torres Hogar");
		this.setLayout(null);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.out.println("windowClosing()"); // TODO Auto-generated Event stub windowClosing()
				finalizeAplication();
			}
		});
		usuarioActivo = new Usuario();

		disableAllOptions(false);
		setDatosEmpresa();
		/*dialogLoginUsuario = new DialogLoginUsuario(new Frame());
		dialogLoginUsuario.setModal(true);
		dialogLoginUsuario.setVisible(true);
		loginSistema(dialogLoginUsuario.getUsuarioActivo());*/
		inicializarCaja();

	}

	/**
	 * This method initializes jDesktopPane
	 *
	 * @return javax.swing.JDesktopPane
	 */
	public MyDesktopPanel getJDesktopPane() {
		if (jDesktopPane == null) {
			jLabelFecha = new JLabel();
			jLabelFecha.setBounds(new Rectangle(812, 70, 200, 30));
			jLabelFecha.setText(getTextFormat("Fecha: "+(new SimpleDateFormat("dd/MM/yyyy").format(new Date())), "4", "black", true, true));
			jLabelWeb = new JLabel();
			jLabelWeb.setBounds(new Rectangle(44, 604, 500, 30));
			jLabelWeb.setText("Web");
			jLabelEmail = new JLabel();
			jLabelEmail.setBounds(new Rectangle(44, 564, 500, 30));
			jLabelEmail.setText("Email");
			jLabelTelefono2 = new JLabel();
			jLabelTelefono2.setBounds(new Rectangle(44, 519, 500, 30));
			jLabelTelefono2.setText("Telefono 2");
			jLabelTelefono1 = new JLabel();
			jLabelTelefono1.setBounds(new Rectangle(44, 474, 500, 30));
			//jLabelTelefono1.setText("Telefono 1");
			jLabelCondicionIva = new JLabel();
			jLabelCondicionIva.setBounds(new Rectangle(44, 165, 250, 30));
			//jLabelCondicionIva.setText("Condicion IVa");
			jLabelDocNro = new JLabel();
			jLabelDocNro.setBounds(new Rectangle(368, 166, 127, 30));
			jLabelDocNro.setText("");
			jLabelTipoDoc = new JLabel();
			jLabelTipoDoc.setBounds(new Rectangle(298, 166, 100, 30));
			jLabelTipoDoc.setText("Tipo Documento:");
			jLabelCodPos = new JLabel();
			jLabelCodPos.setBounds(new Rectangle(44, 379, 500, 30));
			jLabelCodPos.setText("Codigo Postal");
			jLabelProvincia = new JLabel();
			jLabelProvincia.setBounds(new Rectangle(44, 289, 500, 30));
			jLabelProvincia.setText("Provincia");
			jLabelLocalidad = new JLabel();
			jLabelLocalidad.setBounds(new Rectangle(44, 334, 500, 30));
			jLabelLocalidad.setText("Localidad");
			jLabelDireccion = new JLabel();
			jLabelDireccion.setBounds(new Rectangle(44, 429, 500, 30));
			jLabelDireccion.setText("Direccion");
			jLabelFechaInicioActiv = new JLabel();
			jLabelFechaInicioActiv.setBounds(new Rectangle(44, 250, 500, 30));
			jLabelFechaInicioActiv.setText("Fecha Inicio Actividades");
			jLabelNroPtoVta = new JLabel();
			jLabelNroPtoVta.setBounds(new Rectangle(44, 210, 500, 30));
			jLabelNroPtoVta.setText("Nro Punto de Venta");
			jLabelNomFantasia = new JLabel();
			jLabelNomFantasia.setBounds(new Rectangle(45, 70, 431, 48));
			//jLabelNomFantasia.setText("Nombre Fantasía");
			jLabelRazonSocial = new JLabel();
			jLabelRazonSocial.setBounds(new Rectangle(44, 125, 367, 30));
			//jLabelRazonSocial.setText("Razón Social");

			jDesktopPane = new MyDesktopPanel(new ImageIcon(getClass().getResource("/Fondo_Principal_1.4.png")).getImage());
			jDesktopPane.setLayout(null);
			jDesktopPane.setBounds(new Rectangle(this.getWidth(), this.getHeight()));
			jDesktopPane.add(getJJToolBarBar(), null);
			jDesktopPane.add(jLabelRazonSocial, null);
			jDesktopPane.add(jLabelNomFantasia, null);
			jDesktopPane.add(jLabelNroPtoVta, null);
			jDesktopPane.add(jLabelFechaInicioActiv, null);
			jDesktopPane.add(jLabelDireccion, null);
			jDesktopPane.add(jLabelLocalidad, null);
			jDesktopPane.add(jLabelProvincia, null);
			jDesktopPane.add(jLabelCodPos, null);
			jDesktopPane.add(jLabelTipoDoc, null);
			jDesktopPane.add(jLabelDocNro, null);
			jDesktopPane.add(jLabelCondicionIva, null);
			jDesktopPane.add(jLabelTelefono1, null);
			jDesktopPane.add(jLabelTelefono2, null);
			jDesktopPane.add(jLabelEmail, null);
			jDesktopPane.add(jLabelWeb, null);
			jDesktopPane.add(jLabelFecha, null);
			jDesktopPane.add(getJInternalFrameClock(), null);
		}
		return jDesktopPane;
	}

	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.setPreferredSize(new Dimension(0, 30));
			jJMenuBar.add(getJMenuSistema());
			jJMenuBar.add(getJMenuVentas());
			jJMenuBar.add(getJMenuProveedor());
			jJMenuBar.add(getJMenuInventario());
			jJMenuBar.add(getJMenuCaja());
			jJMenuBar.add(getJMenuListadosReportes());
			jJMenuBar.add(getJMenuEstadisticas());
			jJMenuBar.add(getJMenuAyuda());
		}
		return jJMenuBar;
	}


	private JMenu getJMenuSistema() {
		if (jMenuSistema == null) {
			jMenuSistema = new JMenu();
			jMenuSistema.setText("Sistema");
			jMenuSistema.setPreferredSize(new Dimension(60, 30));
			jMenuSistema.setToolTipText("Opciones de Sistema");
			jMenuSistema.add(getJMenuItemSistemaPreferencia());
			jMenuSistema.add(getJMenuItemConectar());
			jMenuSistema.add(getJMenuItemDesconectar());
			jMenuSistema.add(getJMenuItemSistemaSalir());

		}
		return jMenuSistema;
	}


	private JMenu getJMenuProveedor() {
		if (jMenuProveedor == null) {
			jMenuProveedor = new JMenu();
			jMenuProveedor.setVisible(true);
			jMenuProveedor.setText("Proveedores");
			jMenuProveedor.setName("3000");
			jMenuProveedor.setPreferredSize(new Dimension(80, 30));
			jMenuProveedor.setToolTipText("Gestión de Proveedores");
			jMenuProveedor.add(getJMenuItemGestionProveedor());
		}
		return jMenuProveedor;
	}


	private JMenu getJMenuCaja() {
		if (jMenuCaja == null) {
			jMenuCaja = new JMenu();
			jMenuCaja.setText("Caja");
			jMenuCaja.setName("5000");
			jMenuCaja.setToolTipText("Opciones de Caja");
			jMenuCaja.setPreferredSize(new Dimension(35, 30));
			jMenuCaja.add(getJMenuItemCajaMovimientoGestion());
			jMenuCaja.add(getJMenuItemCajaInicio());
			jMenuCaja.add(getJMenuItemCajaCierre());
		}
		return jMenuCaja;
	}

	private JMenu getJMenuSeguridadMantenimiento() {
		if (jMenuSeguridadMantenimiento == null) {
			jMenuSeguridadMantenimiento = new JMenu();
			jMenuSeguridadMantenimiento.setIcon(new ImageIcon(getClass().getResource("/preferences-desktop_16x16.png")));
			jMenuSeguridadMantenimiento.setText("Usuarios y Mantenimiento");
			jMenuSeguridadMantenimiento.setName("1007");
			jMenuSeguridadMantenimiento.setToolTipText("Opciones de Usuarios");
			jMenuSeguridadMantenimiento.add(getJMenuItemGestionUsuarios());
			jMenuSeguridadMantenimiento.add(getJMenuItemBackupDataBase());
		}
		return jMenuSeguridadMantenimiento;
	}


	private JMenu getJMenuEstadisticas() {
		if (jMenuEstadisticas == null) {
			jMenuEstadisticas = new JMenu();
			jMenuEstadisticas.setText("Estadísticas");
			jMenuEstadisticas.setToolTipText("Emisión de Estadísticas varias");
			jMenuEstadisticas.setPreferredSize(new Dimension(80, 30));
			jMenuEstadisticas.add(getJMenuItemEstadisticasVentas());
		}
		return jMenuEstadisticas;
	}


	private JMenu getJMenuAyuda() {
		if (jMenuAyuda == null) {
			jMenuAyuda = new JMenu();
			jMenuAyuda.setText("Ayuda");
			jMenuAyuda.setToolTipText("Ayuda");
			jMenuAyuda.setPreferredSize(new Dimension(45, 30));
			jMenuAyuda.add(getJMenuItemAyudaAcercaDe());
			jMenuAyuda.add(getJMenuItemAyudaTemasAyuda());
		}
		return jMenuAyuda;
	}


	private JMenuItem getJMenuItemSistemaSalir() {
		if (jMenuItemSistemaSalir == null) {
			jMenuItemSistemaSalir = new JMenuItem();
			jMenuItemSistemaSalir.setText("Salir");
			jMenuItemSistemaSalir.setIcon(new ImageIcon(getClass().getResource("/exit_16x16.png")));
			jMenuItemSistemaSalir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed(SALIR)"); //
					finalizeAplication();
				}
			});
		}
		return jMenuItemSistemaSalir;
	}


	public JMenuItem getJMenuItemClientesGestion() {
		if (jMenuItemClientesGestion == null) {
			jMenuItemClientesGestion = new JMenuItem();
			jMenuItemClientesGestion.setText("Gestión de Clientes");
			jMenuItemClientesGestion.setName("2001");
			jMenuItemClientesGestion.setIcon(new ImageIcon(getClass().getResource("/customers_16x16.png")));
			jMenuItemClientesGestion.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Clientes()"); //
					InternalFrameGestorCliente internalFrameGestorClientes = new InternalFrameGestorCliente();
					jDesktopPane.add(internalFrameGestorClientes);
					setCentrarJInternalFrame(internalFrameGestorClientes);
					internalFrameGestorClientes.setVisible(true);
				}
			});
		}
		return jMenuItemClientesGestion;
	}


	private JMenuItem getJMenuItemGestionProveedor() {
		if (jMenuItemGestionProveedor == null) {
			jMenuItemGestionProveedor = new JMenuItem();
			jMenuItemGestionProveedor.setText("Gestión Proveedores");
			jMenuItemGestionProveedor.setIcon(new ImageIcon(getClass().getResource("/man-user-icon_16x16.png")));
			jMenuItemGestionProveedor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					InternalFrameGestorProveedores internalFrameGestorProveedores = new InternalFrameGestorProveedores();
					jDesktopPane.add(internalFrameGestorProveedores);
					setCentrarJInternalFrame(internalFrameGestorProveedores);
					internalFrameGestorProveedores.setVisible(true);
				}

			});
		}
		return jMenuItemGestionProveedor;
	}


	private JMenuItem getJMenuItemCajaCierre() {
		if (jMenuItemCajaCierre == null) {
			jMenuItemCajaCierre = new JMenuItem();
			jMenuItemCajaCierre.setIcon(new ImageIcon(getClass().getResource("/stop_16x16.png")));
			jMenuItemCajaCierre.setText("Cerrar Caja Diaria");
			jMenuItemCajaCierre.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					if(cajaDiaria != null){
						cajaDiaria.setMovimientosCasa(movimientoCajaLogica.getListMovimientoCajas(cajaDiaria));
						if(cajaLogica.cieereCaja(cajaDiaria)){
							JOptionPane.showMessageDialog(null, "Cierre de Caja Finalizado con Éxito","Cierre de Caja",JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(null, "Ocurrió un Error al Realizar el Cierre de Caja","Cierre de Caja",JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Debe Iniciar la Caja del Día","Iniciar Caja",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		}
		return jMenuItemCajaCierre;
	}


	private JMenuItem getJMenuItemCajaInicio() {
		if (jMenuItemCajaInicio == null) {
			jMenuItemCajaInicio = new JMenuItem();
			jMenuItemCajaInicio.setText("Iniciar Caja Diaria");
			jMenuItemCajaInicio.setIcon(new ImageIcon(getClass().getResource("/start_16x16.png")));
			jMenuItemCajaInicio.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					inicializarCaja();
				}
			});
		}
		return jMenuItemCajaInicio;
	}


	private JMenuItem getJMenuItemEstadisticasVentas() {
		if (jMenuItemEstadisticasVentas == null) {
			jMenuItemEstadisticasVentas = new JMenuItem();
			jMenuItemEstadisticasVentas.setText("Generar Estadistica de Ventas");
			jMenuItemEstadisticasVentas.setIcon(new ImageIcon(getClass().getResource("/view-statistics-icon_16x16.png")));
			jMenuItemEstadisticasVentas
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()"); //
							InternalFrameAnalisisVenta internalFrameAnalisisVenta = new InternalFrameAnalisisVenta();
							jDesktopPane.add(internalFrameAnalisisVenta);
							setCentrarJInternalFrame(internalFrameAnalisisVenta);
							internalFrameAnalisisVenta.setVisible(true);
						}
					});
		}
		return jMenuItemEstadisticasVentas;
	}


	private JMenu getJMenuInventario() {
		if (jMenuInventario == null) {
			jMenuInventario = new JMenu();
			jMenuInventario.setText("Inventario");
			jMenuInventario.setName("4000");
			jMenuInventario.setToolTipText("Opciones sobre el Inventario");
			jMenuInventario.setPreferredSize(new Dimension(65, 30));
			jMenuInventario.add(getJMenuItemArticulos());
			jMenuInventario.add(getJMenuItemConsultaArticulo());
			jMenuInventario.add(getJMenuItemInventarioRubros());
			jMenuInventario.add(getJMenuItemInventarioSubRubros());
		}
		return jMenuInventario;
	}


	private JMenuItem getJMenuItemGestionUsuarios() {
		if (jMenuItemGestionUsuarios == null) {
			jMenuItemGestionUsuarios = new JMenuItem();
			jMenuItemGestionUsuarios.setText("Usuarios y Perfiles");
			jMenuItemGestionUsuarios.setIcon(new ImageIcon(getClass().getResource("/users_16x16.png")));
			jMenuItemGestionUsuarios.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					InternalFrameUsuariosPerfiles internalFrameUsuariosPerfiles = new InternalFrameUsuariosPerfiles();
					jDesktopPane.add(internalFrameUsuariosPerfiles);
					setCentrarJInternalFrame(internalFrameUsuariosPerfiles);
					internalFrameUsuariosPerfiles.setVisible(true);
				}
			});
		}
		return jMenuItemGestionUsuarios;
	}


	private JMenuItem getJMenuItemAyudaAcercaDe() {
		if (jMenuItemAyudaAcercaDe == null) {
			jMenuItemAyudaAcercaDe = new JMenuItem();
			jMenuItemAyudaAcercaDe.setIcon(new ImageIcon(getClass().getResource("/Information_16x16.png")));
			jMenuItemAyudaAcercaDe.setText("Acerca De...");
			jMenuItemAyudaAcercaDe.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					DialogAbout dialogAbout = new DialogAbout(new Frame());
					dialogAbout.setModal(true);
					dialogAbout.setVisible(true);
				}
			});
		}
		return jMenuItemAyudaAcercaDe;
	}


	private JMenuItem getJMenuItemArticulos() {
		if (jMenuItemArticulos == null) {
			jMenuItemArticulos = new JMenuItem();
			jMenuItemArticulos.setIcon(new ImageIcon(getClass().getResource("/inventory_categories_16x16.png")));
			jMenuItemArticulos.setText("Gestión de Inventario");
			jMenuItemArticulos.setName("4001");
			jMenuItemArticulos.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					InternalFrameGestorArticulo internalFrameGestorArticulos = new InternalFrameGestorArticulo();
					jDesktopPane.add(internalFrameGestorArticulos);
					setCentrarJInternalFrame(internalFrameGestorArticulos);
					internalFrameGestorArticulos.setVisible(true);
				}
			});
		}
		return jMenuItemArticulos;
	}


	private JMenu getJMenuClienteCtaCte() {
		if (jMenuClienteCtaCte == null) {
			jMenuClienteCtaCte = new JMenu();
			jMenuClienteCtaCte.setText("Cuentas Corrientes");
			jMenuClienteCtaCte.setIcon(new ImageIcon(getClass().getResource("/account_balances_16x16.png")));
			jMenuClienteCtaCte.setName("2003");
			jMenuClienteCtaCte.add(getJMenuItemClienteCtaCteConsulta());
		}
		return jMenuClienteCtaCte;
	}


	private JMenuItem getJMenuOtrosProvincias() {
		if (jMenuOtrosProvincias == null) {
			jMenuOtrosProvincias = new JMenuItem();
			jMenuOtrosProvincias.setText("Gestión Provincias");
			jMenuOtrosProvincias.setName("1003");
			jMenuOtrosProvincias.setIcon(new ImageIcon(getClass().getResource("/window-standar-icon_16x16.png")));
			jMenuOtrosProvincias.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					InternalFrameGestorProvincia internalFrameGestorProvincia = new InternalFrameGestorProvincia();
					jDesktopPane.add(internalFrameGestorProvincia);
					setCentrarJInternalFrame(internalFrameGestorProvincia);
					internalFrameGestorProvincia.setVisible(true);

				}

			});
		}
		return jMenuOtrosProvincias;
	}


	private JMenuItem getJMenuOtrosLocalidades() {
		if (jMenuOtrosLocalidades == null) {
			jMenuOtrosLocalidades = new JMenuItem();
			jMenuOtrosLocalidades.setText("Gestión de Localicades");
			jMenuOtrosLocalidades.setName("1004");
			jMenuOtrosLocalidades.setIcon(new ImageIcon(getClass().getResource("/window-standar-icon_16x16.png")));
			jMenuOtrosLocalidades.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					InternalFrameGestorLocalidad internalFrameGestorLocalidad = new InternalFrameGestorLocalidad();
					jDesktopPane.add(internalFrameGestorLocalidad);
					setCentrarJInternalFrame(internalFrameGestorLocalidad);
					internalFrameGestorLocalidad.setVisible(true);
				}

			});
		}
		return jMenuOtrosLocalidades;
	}


	private JMenuItem getJMenuOtrosVendedores() {
		if (jMenuOtrosVendedores == null) {
			jMenuOtrosVendedores = new JMenuItem();
			jMenuOtrosVendedores.setText("Gestión de Vendedores");
			jMenuOtrosVendedores.setIcon(new ImageIcon(getClass().getResource("/evolution-contacts-icon_16x16.png")));
			jMenuOtrosVendedores.setName("1005");
			jMenuOtrosVendedores.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Vendedores()"); //
					InternalFrameGestorVendedor internalFrameGestorVendedor = new InternalFrameGestorVendedor();
					jDesktopPane.add(internalFrameGestorVendedor);
					setCentrarJInternalFrame(internalFrameGestorVendedor);
					internalFrameGestorVendedor.setVisible(true);
				}

			});
		}
		return jMenuOtrosVendedores;
	}


	private JMenuItem getJMenuOtrosIva() {
		if (jMenuOtrosIva == null) {
			jMenuOtrosIva = new JMenuItem();
			jMenuOtrosIva.setText("Gestión Condiciones de IVA");
			jMenuOtrosIva.setIcon(new ImageIcon(getClass().getResource("/window-standar-icon_16x16.png")));
			jMenuOtrosIva.setName("1006");
			jMenuOtrosIva.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					InternalFrameGestorCondicionIva internalFrameGestorCondicionIva = new InternalFrameGestorCondicionIva();
					jDesktopPane.add(internalFrameGestorCondicionIva);
					setCentrarJInternalFrame(internalFrameGestorCondicionIva);
					internalFrameGestorCondicionIva.setVisible(true);
				}
			});
		}
		return jMenuOtrosIva;
	}


	private JMenuItem getJMenuItemInventarioRubros() {
		if (jMenuItemInventarioRubros == null) {
			jMenuItemInventarioRubros = new JMenuItem();
			jMenuItemInventarioRubros.setIcon(new ImageIcon(getClass().getResource("/window-standar-icon_16x16.png")));
			jMenuItemInventarioRubros.setName("4003");
			jMenuItemInventarioRubros.setText("Rubros");
			jMenuItemInventarioRubros.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					InternalFrameGestorRubro internalFrameGestorRubro = new InternalFrameGestorRubro();
					jDesktopPane.add(internalFrameGestorRubro);
					setCentrarJInternalFrame(internalFrameGestorRubro);
					internalFrameGestorRubro.setVisible(true);
				}
			});
		}
		return jMenuItemInventarioRubros;
	}


	private JMenuItem getJMenuItemInventarioSubRubros() {
		if (jMenuItemInventarioSubRubros == null) {
			jMenuItemInventarioSubRubros = new JMenuItem();
			jMenuItemInventarioSubRubros.setText("SubRubros");
			jMenuItemInventarioSubRubros.setIcon(new ImageIcon(getClass().getResource("/window-standar-icon_16x16.png")));
			jMenuItemInventarioSubRubros.setName("4004");
			jMenuItemInventarioSubRubros.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("SubRubros"); //
					InternalFrameGestorSubRubro internalFrameGestorSubRubro = new InternalFrameGestorSubRubro();
					jDesktopPane.add(internalFrameGestorSubRubro);
					setCentrarJInternalFrame(internalFrameGestorSubRubro);
					internalFrameGestorSubRubro.setVisible(true);
				}

			});
		}
		return jMenuItemInventarioSubRubros;
	}


	private JMenuItem getJMenuOtrosTipoDocumento() {
		if (jMenuOtrosTipoDocumento == null) {
			jMenuOtrosTipoDocumento = new JMenuItem();
			jMenuOtrosTipoDocumento.setText("Gestion Tipos de Documentos");
			jMenuOtrosTipoDocumento.setVisible(false);
			jMenuOtrosTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

				}
			});
		}
		return jMenuOtrosTipoDocumento;
	}


	private JMenuItem getJMenuOtrosCondicionVenta() {
		if (jMenuOtrosCondicionVenta == null) {
			jMenuOtrosCondicionVenta = new JMenuItem();
			jMenuOtrosCondicionVenta.setText("Condiciones de Venta");
			jMenuOtrosCondicionVenta.setName("1002");
			jMenuOtrosCondicionVenta.setIcon(new ImageIcon(getClass().getResource("/window-standar-icon_16x16.png")));
			jMenuOtrosCondicionVenta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					InternalFrameGestorCondVenta internalFrameGestorCondVenta = new InternalFrameGestorCondVenta();
					jDesktopPane.add(internalFrameGestorCondVenta);
					setCentrarJInternalFrame(internalFrameGestorCondVenta);
					internalFrameGestorCondVenta.setVisible(true);
				}
			});
		}
		return jMenuOtrosCondicionVenta;
	}


	private JMenuItem getJMenuOtrosListasPrecios() {
		if (jMenuOtrosListasPrecios == null) {
			jMenuOtrosListasPrecios = new JMenuItem();
			jMenuOtrosListasPrecios.setIcon(new ImageIcon(getClass().getResource("/book_open_16x16.png")));
			jMenuOtrosListasPrecios.setText("Listas de Precios");
			jMenuOtrosListasPrecios.setName("1001");
			jMenuOtrosListasPrecios.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Listas Precios()"); //
					InternalFrameGestorListasPrecio internalFrameGestorListasPrecio = new InternalFrameGestorListasPrecio();
					jDesktopPane.add(internalFrameGestorListasPrecio);
					setCentrarJInternalFrame(internalFrameGestorListasPrecio);
					internalFrameGestorListasPrecio.setVisible(true);
				}
			});
		}
		return jMenuOtrosListasPrecios;
	}


	private JMenu getJMenuVentas() {
		if (jMenuVentas == null) {
			jMenuVentas = new JMenu();
			jMenuVentas.setText("Ventas");
			jMenuVentas.setName("2000");
			jMenuVentas.setToolTipText("Opciones de Ventas");
			jMenuVentas.setPreferredSize(new Dimension(50, 30));
			jMenuVentas.add(getJMenuItemClientesGestion());
			jMenuVentas.add(getJMenuItemFacturacion());
			jMenuVentas.add(getJMenuItemFacturacionConsulta());
			jMenuVentas.add(getJMenuItemNotaCredito());
			jMenuVentas.add(getJMenuClienteCtaCte());
		}
		return jMenuVentas;
	}


	private JMenuItem getJMenuItemFacturacion() {
		if (jMenuItemFacturacion == null) {
			jMenuItemFacturacion = new JMenuItem();
			jMenuItemFacturacion.setText("Facturación");
			jMenuItemFacturacion.setIcon(new ImageIcon(getClass().getResource("/facturacion_16x16.png")));
			jMenuItemFacturacion.setName("2002");
			jMenuItemFacturacion.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Facturacion()"); //
					InternalFrameFacturacion internalFrameFacturacion= new InternalFrameFacturacion();
					jDesktopPane.add(internalFrameFacturacion);
					setCentrarJInternalFrame(internalFrameFacturacion);
					internalFrameFacturacion.setVisible(true);
					internalFrameFacturacion.getJTextFieldCodigoCli().requestFocus();
				}
			});
		}
		return jMenuItemFacturacion;
	}

	private JMenuItem getJMenuItemConsultaArticulo() {
		if (jMenuItemConsultaArticulo == null) {
			jMenuItemConsultaArticulo = new JMenuItem();
			jMenuItemConsultaArticulo.setIcon(new ImageIcon(getClass().getResource("/search_16x16.png")));
			jMenuItemConsultaArticulo.setName("4002");
			jMenuItemConsultaArticulo.setText("Consulta de Productos");
			jMenuItemConsultaArticulo
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()"); //
							InternalFrameConsultaArticulo internalFrameConsultaArticulo = new InternalFrameConsultaArticulo();
							jDesktopPane.add(internalFrameConsultaArticulo);
							setCentrarJInternalFrame(internalFrameConsultaArticulo);
							internalFrameConsultaArticulo.setVisible(true);
						}
					});
		}
		return jMenuItemConsultaArticulo;
	}


	private JMenuItem getJMenuItemAyudaTemasAyuda() {
		if (jMenuItemAyudaTemasAyuda == null) {
			jMenuItemAyudaTemasAyuda = new JMenuItem();
			jMenuItemAyudaTemasAyuda.setIcon(new ImageIcon(getClass().getResource("/system-help_16x16.png")));
			jMenuItemAyudaTemasAyuda.setText("Temas de Ayuda");
			jMenuItemAyudaTemasAyuda.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					     Desktop desktop;
					     File file = new File("help/Manual_Usuario_Final_V3.0.htm");//declaro un Objeto File que apunte a mi archivo html
					        if (Desktop.isDesktopSupported()){// si éste Host soporta esta API
					           desktop = Desktop.getDesktop();//objtengo una instancia del Desktop(Escritorio)de mi host
					            try {
					                desktop.open(file);//abro el archivo con el programa predeterminado
					                }
					            catch (IOException ex) {
					                Logger.getLogger(WinPrincipal.class.getName()).log(Level.SEVERE, null, ex);
					                }
					        }else{ JOptionPane.showMessageDialog(null,"Error en Apertura de Archivo; su equipo no soporta la API Desktop");
					       }
				}
			});
		}
		return jMenuItemAyudaTemasAyuda;
	}


	private JMenu getJMenuItemSistemaPreferencia() {
		if (jMenuItemSistemaPreferencia == null) {
			jMenuItemSistemaPreferencia = new JMenu();
			jMenuItemSistemaPreferencia.setText("Preferencias y Ajustes del Sistema");
			jMenuItemSistemaPreferencia.setIcon(new ImageIcon(getClass().getResource("/Config-Tools_16x16.png")));
			jMenuItemSistemaPreferencia.add(getJMenuOtrosListasPrecios());
			jMenuItemSistemaPreferencia.add(getJMenuOtrosCondicionVenta());
			jMenuItemSistemaPreferencia.add(getJMenuOtrosProvincias());
			jMenuItemSistemaPreferencia.add(getJMenuOtrosLocalidades());
			jMenuItemSistemaPreferencia.add(getJMenuOtrosVendedores());
			jMenuItemSistemaPreferencia.add(getJMenuOtrosIva());
			jMenuItemSistemaPreferencia.add(getJMenuOtrosTipoDocumento());
			jMenuItemSistemaPreferencia.add(getJMenuSeguridadMantenimiento());
		}
		return jMenuItemSistemaPreferencia;
	}





	private JMenuItem getJMenuItemClienteCtaCteConsulta() {
		if (jMenuItemClienteCtaCteConsulta == null) {
			jMenuItemClienteCtaCteConsulta = new JMenuItem();
			jMenuItemClienteCtaCteConsulta.setIcon(new ImageIcon(getClass().getResource("/cabinet_16x16.png")));
			jMenuItemClienteCtaCteConsulta.setText("Consultar Cuentas Corrientes");
			jMenuItemClienteCtaCteConsulta
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()"); //
							InternalFrameMovimientoCtaCte internalFrameMovimientoCtaCte = new InternalFrameMovimientoCtaCte(null);
							jDesktopPane.add(internalFrameMovimientoCtaCte);
							setCentrarJInternalFrame(internalFrameMovimientoCtaCte);
							internalFrameMovimientoCtaCte.setVisible(true);
						}
					});
		}
		return jMenuItemClienteCtaCteConsulta;
	}


	private JMenuItem getJMenuItemBackupDataBase() {
		if (jMenuItemBackupDataBase == null) {
			jMenuItemBackupDataBase = new JMenuItem();
			jMenuItemBackupDataBase.setIcon(new ImageIcon(getClass().getResource("/Save_16x16-32.png")));
			jMenuItemBackupDataBase.setText("Respaldar Base de Datos");
			jMenuItemBackupDataBase.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					DialogBackup prueba = new DialogBackup(new Frame());
					prueba.setVisible(true);
				}
			});
		}
		return jMenuItemBackupDataBase;
	}


	private JMenuItem getJMenuItemCajaMovimientoGestion() {
		if (jMenuItemCajaMovimientoGestion == null) {
			jMenuItemCajaMovimientoGestion = new JMenuItem();
			jMenuItemCajaMovimientoGestion.setIcon(new ImageIcon(getClass().getResource("/cashbox_16x16.png")));
			jMenuItemCajaMovimientoGestion.setText("Movimientos de Caja");
			jMenuItemCajaMovimientoGestion
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()"); //
							if(cajaDiaria != null){
								InternalFrameMovimientoCaja internalFrameMovimientoCaja = new InternalFrameMovimientoCaja(cajaDiaria);
								jDesktopPane.add(internalFrameMovimientoCaja);
								setCentrarJInternalFrame(internalFrameMovimientoCaja);
								internalFrameMovimientoCaja.setVisible(true);
							}else{
								JOptionPane.showMessageDialog(null, "Debe Iniciar la Caja del Día","Iniciar Caja",JOptionPane.INFORMATION_MESSAGE);
							}

						}
					});
		}
		return jMenuItemCajaMovimientoGestion;
	}


	private JMenu getJMenuListadosReportes() {
		if (jMenuListadosReportes == null) {
			jMenuListadosReportes = new JMenu();
			jMenuListadosReportes.setName("6000");
			jMenuListadosReportes.setToolTipText("Emisión de Listados y Reportes");
			jMenuListadosReportes.setText("Listados y Reportes");
			jMenuListadosReportes.setPreferredSize(new Dimension(120, 30));
			jMenuListadosReportes.add(getJMenuItemListadoPrecios());
			jMenuListadosReportes.add(getJMenuItemReporteVentas());
		}
		return jMenuListadosReportes;
	}


	private JMenuItem getJMenuItemListadoPrecios() {
		if (jMenuItemListadoPrecios == null) {
			jMenuItemListadoPrecios = new JMenuItem();
			jMenuItemListadoPrecios.setText("Listado de Precios");
			jMenuItemListadoPrecios.setIcon(new ImageIcon(getClass().getResource("/list-icon_16x16.png")));
			jMenuItemListadoPrecios.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					InternalFrameListadoPrecio internaFrameListadoPrecio = new InternalFrameListadoPrecio();
					jDesktopPane.add(internaFrameListadoPrecio);
					setCentrarJInternalFrame(internaFrameListadoPrecio);
					internaFrameListadoPrecio.setVisible(true);
				}
			});
		}
		return jMenuItemListadoPrecios;
	}


	private JMenuItem getJMenuItemReporteVentas() {
		if (jMenuItemReporteVentas == null) {
			jMenuItemReporteVentas = new JMenuItem();
			jMenuItemReporteVentas.setText("Reporte de Ventas");
			jMenuItemReporteVentas.setIcon(new ImageIcon(getClass().getResource("/sales-report_16x16.png")));
			jMenuItemReporteVentas.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					InternalFrameReporteVentas internalFrameReporteVentas = new InternalFrameReporteVentas();
					jDesktopPane.add(internalFrameReporteVentas);
					setCentrarJInternalFrame(internalFrameReporteVentas);
					internalFrameReporteVentas.setVisible(true);
				}
			});
		}
		return jMenuItemReporteVentas;
	}

	private void setDatosEmpresa(){
		Empresa empresa = empresaLogica.getEmpresa(1);
		if(empresa != null){
			jLabelRazonSocial.setText(getTextFormat(empresa.getRazonSocial(), "4", "white", true, true));
			jLabelNomFantasia.setText(getTextFormat(empresa.getNombreFantasia(), "10", "black", true, true));
			jLabelNroPtoVta.setText(getTextFormat("<b>Punto de Venta Nro: </b>"+empresa.getNroPtoVta(), "4", "white", false, true));
			jLabelFechaInicioActiv.setText(getTextFormat("<b>Inicio de Actividades: </b>"+empresa.getFechaInicioActividades(), "4", "white", false, true));
			jLabelDireccion.setText(getTextFormat(empresa.getDireccion(), "4", "white", false, true));
			jLabelLocalidad.setText(getTextFormat("<b>Localidad: </b>"+empresa.getLocalidad().getNombre(), "4", "white", false, true));
			jLabelProvincia.setText(getTextFormat("<b>Provincia: </b>"+empresa.getProvincia().getNombre(), "4", "white", false, true));
			jLabelCodPos.setText(getTextFormat("<b>Código Postal: </b>"+empresa.getCodPostal(), "4", "white", false, true));
			jLabelTipoDoc.setText(getTextFormat(empresa.getDocumentoTipoPersona().getDescripcion(), "4", "white", false, true));
			jLabelDocNro.setText(getTextFormat(empresa.getDocumentoNro(), "4", "white", false, true));
			jLabelCondicionIva.setText(getTextFormat("<b>IVA: </b>"+empresa.getCondicionIva().getDescripcion(), "4", "white", false, true));
			jLabelTelefono1.setText(getTextFormat("<b>Teléfono 1: </b>"+empresa.getTelefono1(), "4", "white", false, true));
			//jLabelTelefono2.setText(getTextFormat("<b>Telefono 2: </b>"+empresa.getTelefono2(), "4", "white", false, true));
			jLabelTelefono2.setText(getTextFormat("Teléfono 2:", "4", "white", true, true));
			jLabelEmail.setText(getTextFormat(empresa.getMail(), "4", "white", false, true));
			jLabelWeb.setText(getTextFormat(empresa.getWeb(), "4", "white", false, true));

		}
	}

	private String getTextFormat(String text, String size, String color, boolean bold, boolean font){
		String resultado = "<html> ";
		if(bold)resultado += "<b> ";
		if(font){
			resultado += "<font ";
			if(size != null)resultado += "size='"+size+"' ";
			if(color != null)resultado += "color='"+color+"' ";
			resultado += "> ";
		}
		resultado += text;
		if(font)resultado += " </font> ";
		if(bold)resultado += "</b> ";
		resultado += "</html>";
		return resultado;
	}

	/**
	 * This method initializes jButtonClientes
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonClientes() {
		if (jButtonClientes == null) {
			jButtonClientes = new JButton();
			jButtonClientes.setIcon(new ImageIcon(getClass().getResource("/customers_64x64.png")));
			//jButtonClientes.setText("Clientes");
			jButtonClientes.setToolTipText("Gestión de Clientes");
			jButtonClientes.setBorderPainted(false);
			jButtonClientes.setPreferredSize(new Dimension(64, 64));
			jButtonClientes.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					InternalFrameGestorCliente internalFrameGestorClientes = new InternalFrameGestorCliente();
					jDesktopPane.add(internalFrameGestorClientes);
					setCentrarJInternalFrame(internalFrameGestorClientes);
					internalFrameGestorClientes.setVisible(true);
				}
			});

		}
		return jButtonClientes;
	}

	/**
	 * This method initializes jButtonArticulos
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonArticulos() {
		if (jButtonArticulos == null) {
			jButtonArticulos = new JButton();
			jButtonArticulos.setPreferredSize(new Dimension(64, 64));
			jButtonArticulos.setToolTipText("Gestión de Inventario");
			//jButtonArticulos.setText("Articulos");
			jButtonArticulos.setBorderPainted(false);
			jButtonArticulos.setIcon(new ImageIcon(getClass().getResource("/inventory_categories_64X64.png")));
			jButtonArticulos.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					InternalFrameGestorArticulo internalFrameGestorArticulos = new InternalFrameGestorArticulo();
					jDesktopPane.add(internalFrameGestorArticulos);
					setCentrarJInternalFrame(internalFrameGestorArticulos);
					internalFrameGestorArticulos.setVisible(true);
				}
			});
		}
		return jButtonArticulos;
	}


	private JButton getJButtonFacturacion() {
		if (jButtonFacturacion == null) {
			jButtonFacturacion = new JButton();
			jButtonFacturacion.setPreferredSize(new Dimension(64, 64));
			jButtonFacturacion.setToolTipText("Emisión de Facturas");
			//jButtonFacturacion.setText("Facturacion");
			jButtonFacturacion.setIcon(new ImageIcon(getClass().getResource("/invoice_64x64.png")));
			jButtonFacturacion.setBorderPainted(false);
			jButtonFacturacion.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					InternalFrameFacturacion internalFrameFacturacion= new InternalFrameFacturacion();
					jDesktopPane.add(internalFrameFacturacion);
					setCentrarJInternalFrame(internalFrameFacturacion);
					internalFrameFacturacion.setVisible(true);
					internalFrameFacturacion.getJTextFieldCodigoCli().requestFocus();
				}
			});
		}
		return jButtonFacturacion;
	}

	/**
	 * This method initializes jButtonCobranzas
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonCobranzas() {
		if (jButtonCobranzas == null) {
			jButtonCobranzas = new JButton();
			jButtonCobranzas.setPreferredSize(new Dimension(64, 64));
			jButtonCobranzas.setToolTipText("Gestión de Cobranzas");
			jButtonCobranzas.setIcon(new ImageIcon(getClass().getResource("/paymen_64x64.png")));
			jButtonCobranzas.setBorderPainted(false);
			jButtonCobranzas.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					InternalFrameMovimientoCtaCte internalFrameMovimientoCtaCte = new InternalFrameMovimientoCtaCte(null);
					jDesktopPane.add(internalFrameMovimientoCtaCte);
					setCentrarJInternalFrame(internalFrameMovimientoCtaCte);
					internalFrameMovimientoCtaCte.setVisible(true);
				}
			});
		}
		return jButtonCobranzas;
	}

	/**
	 * This method initializes jButtonCaja
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonCaja() {
		if (jButtonCaja == null) {
			jButtonCaja = new JButton();
			jButtonCaja.setPreferredSize(new Dimension(64, 64));
			jButtonCaja.setToolTipText("Gestión de Caja");
			jButtonCaja.setIcon(new ImageIcon(getClass().getResource("/cashbox_64x64.png")));
			//jButtonCaja.setText("Caja");
			//jButtonCaja.setBounds(new Rectangle(891, 111, 115, 28));
			jButtonCaja.setBorderPainted(true);
			jButtonCaja.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					if(cajaDiaria != null){
						InternalFrameMovimientoCaja internalFrameMovimientoCaja = new InternalFrameMovimientoCaja(cajaDiaria);
						jDesktopPane.add(internalFrameMovimientoCaja);
						setCentrarJInternalFrame(internalFrameMovimientoCaja);
						internalFrameMovimientoCaja.setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "Debe Inicializar la Caja del Dia","Inicializar Caja",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		}
		return jButtonCaja;
	}

	public void loginSistema(Usuario us){
		if(us != null){
			usuarioActivo = us;
			//permisos = us.getPerfil().getPermisos().iterator();
			enableOptionByPermisoUser(us);
			this.setTitle("Zelot Gestión - Raul Torres Hogar / Operador: "+ us.getApodo());
		}
	}

	private void enableOptionByPermisoUser(Usuario us){
		jMenuItemSistemaPreferencia.setEnabled(validaPermiso(permisos, "1000"));
		jMenuOtrosListasPrecios.setEnabled(validaPermiso(permisos, "1001"));
		jMenuOtrosCondicionVenta.setEnabled(validaPermiso(permisos, "1002"));
		jMenuOtrosProvincias.setEnabled(validaPermiso(permisos, "1003"));
		jMenuOtrosLocalidades.setEnabled(validaPermiso(permisos, "1004"));
		jMenuOtrosVendedores.setEnabled(validaPermiso(permisos, "1005"));
		jMenuOtrosIva.setEnabled(validaPermiso(permisos, "1006"));
		jMenuSeguridadMantenimiento.setEnabled(validaPermiso(permisos, "1007"));
		jMenuVentas.setEnabled(validaPermiso(permisos, "2000"));
		jMenuItemClientesGestion.setEnabled(validaPermiso(permisos, "2001"));
		jButtonClientes.setEnabled(validaPermiso(permisos, "2001"));
		jMenuItemFacturacion.setEnabled(validaPermiso(permisos, "2002"));
		jButtonNotaCredito.setEnabled(validaPermiso(permisos, "2002"));
		jMenuItemNotaCredito.setEnabled(validaPermiso(permisos, "2002"));
		jButtonFacturacion.setEnabled(validaPermiso(permisos, "2002"));
		jMenuItemFacturacionConsulta.setEnabled(validaPermiso(permisos, "2002"));
		jButtonConsultaFactura.setEnabled(validaPermiso(permisos, "2002"));

		if(validaPermiso(permisos, "2003")){
			jMenuClienteCtaCte.setEnabled(true);
			jMenuItemClienteCtaCteConsulta.setEnabled(true);
			jButtonCobranzas.setEnabled(true);
		}
		jButtonCobranzas.setEnabled(validaPermiso(permisos, "2003"));
		jMenuProveedor.setEnabled(validaPermiso(permisos, "3000"));
		if(validaPermiso(permisos, "3000")){
			jMenuItemGestionProveedor.setEnabled(true);
		}
		jMenuInventario.setEnabled(validaPermiso(permisos, "4000"));
		if(validaPermiso(permisos, "4000")){
			jMenuItemArticulos.setEnabled(true);
			jMenuItemConsultaArticulo.setEnabled(true);
			jMenuItemInventarioRubros.setEnabled(true);
			jMenuItemInventarioSubRubros.setEnabled(true);
			jButtonArticulos.setEnabled(true);
		}
		jMenuCaja.setEnabled(validaPermiso(permisos, "5000"));
		jButtonCaja.setEnabled(validaPermiso(permisos, "5000"));
		if(validaPermiso(permisos, "5000")){
			jMenuItemCajaCierre.setEnabled(true);
			jMenuItemCajaInicio.setEnabled(true);
			jMenuItemCajaMovimientoGestion.setEnabled(true);
		}
		jMenuListadosReportes.setEnabled(validaPermiso(permisos, "6000"));
		//jMenuItemListadoProductos.setEnabled(validaPermiso(permisos, "6001"));
		//jMenuItemListadoClientes.setEnabled(validaPermiso(permisos, "6002"));
		jMenuItemListadoPrecios.setEnabled(validaPermiso(permisos, "6003"));
		jMenuItemReporteVentas.setEnabled(validaPermiso(permisos, "6004"));
		jMenuEstadisticas.setEnabled(validaPermiso(permisos, "7000"));
		if(validaPermiso(permisos, "7000")){
			jMenuItemEstadisticasVentas.setEnabled(true);
			//jMenuItemEstadisticasMovimientosCaja.setEnabled(true);
			//jMenuItemEstadisticasMovimientosCtaCte.setEnabled(true);
		}
	}

	private boolean validaPermiso(Iterator<Permiso> permisos, String codPermiso){
		boolean resultado = false;
		permisos = usuarioActivo.getPerfil().getPermisos().iterator();
		if(permisos != null && codPermiso != null){
			DecimalFormat df = new DecimalFormat("####");
			try {
				int codPer = df.parse(codPermiso).intValue();
				while(permisos.hasNext()){
					Permiso p = (Permiso)permisos.next();
					if(p.getCodigo() == codPer){
						if(p.isActivo())resultado = true;
						break;
					}
				}
			} catch (ParseException e) {
				//
				e.printStackTrace();
			}
		}
		return resultado;
	}


	private void disableAllOptions(boolean option){

		Component[] componentsMenuBar = getJMenuBar().getComponents();

		for(int i = 0; i < componentsMenuBar.length; i++){
			if(componentsMenuBar[i].getClass() == JMenu.class){
				JMenu m = (JMenu)componentsMenuBar[i];
				m.setEnabled(option);

				Component[] compJmenu = m.getMenuComponents();
				for(int ia = 0; ia < compJmenu.length; ia++){
					if(compJmenu[ia].getClass() == JMenu.class){
						JMenu m2 = (JMenu)compJmenu[ia];
						m2.setEnabled(option);

						Component[] compJmenu2 = m2.getMenuComponents();
						for(int ib = 0; ib < compJmenu2.length; ib++){
							if(compJmenu2[ib].getClass() == JMenu.class){
								JMenu m3 = (JMenu)compJmenu2[ib];
								m3.setEnabled(option);
							}else if(compJmenu2[ib].getClass() == JMenuItem.class){
								JMenuItem jmi3 = (JMenuItem)compJmenu2[ib];
								jmi3.setEnabled(option);
							}
						}
					}else if(compJmenu[ia].getClass() == JMenuItem.class){
						JMenuItem jmi2 = (JMenuItem)compJmenu[ia];
						jmi2.setEnabled(option);
					}

				}
			}else if(componentsMenuBar[i].getClass() == JMenuItem.class){
				JMenuItem jmi = (JMenuItem)componentsMenuBar[i];
				jmi.setEnabled(option);
			}

			Component[] componentsDesktopPane = jDesktopPane.getComponents();
			for(int ic = 0; ic < componentsDesktopPane.length; ic++){
				componentsDesktopPane[ic].setEnabled(option);
			}
			Component[] componentsToolBar = jJToolBarBar.getComponents();
			for(int ic = 0; ic < componentsToolBar.length; ic++){
				componentsToolBar[ic].setEnabled(option);
			}
			jMenuSistema.setEnabled(true);
			jMenuItemSistemaSalir.setEnabled(true);
			jMenuAyuda.setEnabled(true);
			jMenuItemAyudaAcercaDe.setEnabled(true);
			jMenuItemAyudaTemasAyuda.setEnabled(true);
			jMenuItemConectar.setEnabled(true);
			jMenuItemDesconectar.setEnabled(true);
			jButtonExit.setEnabled(true);
			jButtonCalculadora.setEnabled(true);
		}
	}


	private JMenuItem getJMenuItemDesconectar() {
		if (jMenuItemDesconectar == null) {
			jMenuItemDesconectar = new JMenuItem();
			jMenuItemDesconectar.setIcon(new ImageIcon(getClass().getResource("/door_open_out_16x16.png")));
			jMenuItemDesconectar.setText("Desconectar");
			jMenuItemDesconectar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					disableAllOptions(false);
					usuarioActivo = null;
					permisos = null;
				}
			});
		}
		return jMenuItemDesconectar;
	}


	private JMenuItem getJMenuItemConectar() {
		if (jMenuItemConectar == null) {
			jMenuItemConectar = new JMenuItem();
			jMenuItemConectar.setText("Conectar");
			jMenuItemConectar.setIcon(new ImageIcon(getClass().getResource("/door_open_in_16x16.png")));
			jMenuItemConectar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					if(usuarioActivo == null){
						dialogLoginUsuario = new LoginUsuario();
						//dialogLoginUsuario.setModal(true);
						dialogLoginUsuario.setVisible(true);
						loginSistema(dialogLoginUsuario.getUsuarioActivo());
					}else{
						JOptionPane.showMessageDialog(null, "Ya se encuentra conectado con el usuario: "+ usuarioActivo.getApodo() +"\n" +
						"Si quiere cambiar de usuario debe desconectarse primero", "Conexión Activa", JOptionPane.INFORMATION_MESSAGE);
					}

				}
			});
		}
		return jMenuItemConectar;
	}


	private void setCentrarJInternalFrame(JInternalFrame jifrm){
		jifrm.setLocation(jifrm.getParent().getWidth()/2 - jifrm.getWidth()/2 ,jifrm.getParent().getHeight()/2 - jifrm.getHeight()/2 - 20);
	}

	private boolean inicializarCaja(){
		boolean resultado = true;
		if(usuarioActivo != null){
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			cajaDiaria = cajaLogica.getCajaByFecha(df.format(new Date()));
			if(cajaDiaria == null){
				int selectOption = JOptionPane.showConfirmDialog(null, "Desea Iniciar la Caja del Día?","Iniciar Caja Diaria", JOptionPane.OK_CANCEL_OPTION);
				if(selectOption == 0){
					cajaDiaria = cajaLogica.inicializaCaja();
					if(cajaDiaria == null){
						/*if(cajaLogica.getCajaByFecha(df.format(new Date())) != null){
							JOptionPane.showMessageDialog(null, "La caja del dia ya ha sido inicializada","Inicializar Caja Diaria", JOptionPane.INFORMATION_MESSAGE);
							cajaDiaria = cajaLogica.getCajaByFecha(df.format(new Date()));
						}else{
							JOptionPane.showMessageDialog(null, "Ocurrio un Error al Inicializar la Caja del Dia Cierre el sistema e intente nuevamente\n" +
									"si el error persiste comuniquece con su administrador del sistema","Error al Inicializar la Caja", JOptionPane.ERROR_MESSAGE);
							resultado = false;
						}*/
						JOptionPane.showMessageDialog(null, "Ocurrió un Error al Inciar la Caja del Día. Cierre el Sistema e intente nuevamente,\n" +
								"si el error persiste comuniquese con su Administrador del sistema","Error al Iniciar la Caja Diaria", JOptionPane.ERROR_MESSAGE);
						resultado = false;
					}
				}else{
					resultado = false;
				}
			}
		}else{
			resultado = false;
		}
		return resultado;
	}

	public static Caja getCaja(){
		return cajaDiaria;
	}


	private JMenuItem getJMenuItemNotaCredito() {
		if (jMenuItemNotaCredito == null) {
			jMenuItemNotaCredito = new JMenuItem();
			jMenuItemNotaCredito.setName("2004");
			jMenuItemNotaCredito.setIcon(new ImageIcon(getClass().getResource("/document-delete_16x16.png")));
			jMenuItemNotaCredito.setText("Notas de Crédito");
			jMenuItemNotaCredito.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); //
					InternalFrameNotaCredito internalFrameNotaCredito = new InternalFrameNotaCredito();
					jDesktopPane.add(internalFrameNotaCredito);
					setCentrarJInternalFrame(internalFrameNotaCredito);
					internalFrameNotaCredito.setVisible(true);
					internalFrameNotaCredito.getJTextFieldCodigoCli().requestFocus();
				}
			});
		}
		return jMenuItemNotaCredito;
	}


	private JMenuItem getJMenuItemFacturacionConsulta() {
		if (jMenuItemFacturacionConsulta == null) {
			jMenuItemFacturacionConsulta = new JMenuItem();
			jMenuItemFacturacionConsulta.setName("2002");
			jMenuItemFacturacionConsulta.setIcon(new ImageIcon(getClass().getResource("/view-icon_16x16.png")));
			jMenuItemFacturacionConsulta.setText("Visor de Facturación");
			jMenuItemFacturacionConsulta
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()"); //
							InternalFrameConsultaFacturacion internalFrameConsultaFacturacion= new InternalFrameConsultaFacturacion();
							jDesktopPane.add(internalFrameConsultaFacturacion);
							setCentrarJInternalFrame(internalFrameConsultaFacturacion);
							internalFrameConsultaFacturacion.setVisible(true);
							internalFrameConsultaFacturacion.setFocusStart();
						}
					});
		}
		return jMenuItemFacturacionConsulta;
	}

	/**
	 * This method initializes jJToolBarBar
	 *
	 * @return javax.swing.JToolBar
	 */
	public JToolBar getJJToolBarBar() {
		if (jJToolBarBar == null) {
			jJToolBarBar = new JToolBar();
			jJToolBarBar.setVisible(true);
			jJToolBarBar.setAutoscrolls(false);
			jJToolBarBar.setFloatable(false);
			//jJToolBarBar.setBackground(Color.BLACK);
			jJToolBarBar.setBounds(new Rectangle(0, 0, 1017, 64));
			//jJToolBarBar.setPreferredSize(new Dimension(this.getSize().width, 64));
			jJToolBarBar.addSeparator(new Dimension(20, 48));
			jJToolBarBar.add(getJButtonClientes());
			jJToolBarBar.addSeparator(new Dimension(20, 48));
			jJToolBarBar.add(getJButtonArticulos());
			jJToolBarBar.addSeparator(new Dimension(20, 48));
			jJToolBarBar.add(getJButtonFacturacion());
			jJToolBarBar.addSeparator(new Dimension(20, 48));
			jJToolBarBar.add(getJButtonNotaCredito());
			jJToolBarBar.addSeparator(new Dimension(20, 48));
			jJToolBarBar.add(getJButtonConsultaFactura());
			jJToolBarBar.addSeparator(new Dimension(20, 48));
			jJToolBarBar.add(getJButtonCaja());
			jJToolBarBar.addSeparator(new Dimension(20, 48));
			jJToolBarBar.add(getJButtonCobranzas());
			jJToolBarBar.addSeparator(new Dimension(20, 48));
			jJToolBarBar.add(getJButtonCalculadora());
			jJToolBarBar.addSeparator(new Dimension(20, 48));
			jJToolBarBar.add(getJButtonExit());
			jJToolBarBar.addSeparator(new Dimension(20, 48));
		}
		return jJToolBarBar;
	}

	public Dimension getDimensionWinPrincipal(){
		return this.getSize();
	}
	public void setDimensionJtoolbar(Dimension dim){
		jJToolBarBar.setSize(dim);
	}
	public void setPositionFecha(Point p){
		jLabelFecha.setLocation(p);
	}

	/**
	 * This method initializes jButtonNotaCredito
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonNotaCredito() {
		if (jButtonNotaCredito == null) {
			jButtonNotaCredito = new JButton();
			jButtonNotaCredito.setPreferredSize(new Dimension(64, 64));
			jButtonNotaCredito.setIcon(new ImageIcon(getClass().getResource("/notaCredito_64x64.png")));
			jButtonNotaCredito.setBorderPainted(false);
			jButtonNotaCredito.setToolTipText("Emisión de Notas de Crédito");
			jButtonNotaCredito.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					InternalFrameNotaCredito internalFrameNotaCredito = new InternalFrameNotaCredito();
					jDesktopPane.add(internalFrameNotaCredito);
					setCentrarJInternalFrame(internalFrameNotaCredito);
					internalFrameNotaCredito.setVisible(true);
					internalFrameNotaCredito.getJTextFieldCodigoCli().requestFocus();
				}
			});
		}
		return jButtonNotaCredito;
	}

	/**
	 * This method initializes jButtonConsultaFactura
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonConsultaFactura() {
		if (jButtonConsultaFactura == null) {
			jButtonConsultaFactura = new JButton();
			jButtonConsultaFactura.setPreferredSize(new Dimension(64, 64));
			jButtonConsultaFactura.setToolTipText("Visor de Comprobantes");
			jButtonConsultaFactura.setIcon(new ImageIcon(getClass().getResource("/Preview Docment_64x64.png")));
			jButtonConsultaFactura.setBorderPainted(false);
			jButtonConsultaFactura.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					InternalFrameConsultaFacturacion internalFrameConsultaFacturacion= new InternalFrameConsultaFacturacion();
					jDesktopPane.add(internalFrameConsultaFacturacion);
					setCentrarJInternalFrame(internalFrameConsultaFacturacion);
					internalFrameConsultaFacturacion.setVisible(true);
					internalFrameConsultaFacturacion.setFocusStart();
				}
			});
		}
		return jButtonConsultaFactura;
	}

	private void finalizeAplication(){
		int selectOption = JOptionPane.showConfirmDialog(null, "Está seguro que desea cerrar Zelot Gestion ERP?", "Cerrar Aplicación",JOptionPane.OK_CANCEL_OPTION);

		if(selectOption == 0){
			if(cajaDiaria != null){
				cajaDiaria.setMovimientosCasa(movimientoCajaLogica.getListMovimientoCajas(cajaDiaria));
				if(cajaLogica.cieereCaja(cajaDiaria)){
					//JOptionPane.showMessageDialog(null, "Cieere de Caja Finalizado con Exito","Cierre de Caja",JOptionPane.INFORMATION_MESSAGE);
				}else{
					//JOptionPane.showMessageDialog(null, "Ocurrio un Error al Relalizar  el Cierre de Caja","Cierre de Caja",JOptionPane.ERROR_MESSAGE);
				}
				if(pcalc!=null)pcalc.destroy();
				dispose();
				System.exit(0);
			}
		}
		System.out.println(selectOption);
	}

	/**
	 * This method initializes jButtonExit
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonExit() {
		if (jButtonExit == null) {
			jButtonExit = new JButton();
			jButtonExit.setPreferredSize(new Dimension(64, 64));
			jButtonExit.setToolTipText("Cerrar Zelot Gestión ERP");
			jButtonExit.setIcon(new ImageIcon(getClass().getResource("/exit_64x64.png")));
			jButtonExit.setBorderPainted(false);
			jButtonExit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					finalizeAplication();
				}
			});
		}
		return jButtonExit;
	}

	/**
	 * This method initializes jInternalFrameClock
	 *
	 * @return javax.swing.JInternalFrame
	 */
	public JInternalFrame getJInternalFrameClock() {
		if (jInternalFrameClock == null) {
			jInternalFrameClock = new JInternalFrame();
			//setUndecorated(true);
		    //jInternalFrameClock.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
			//jInternalFrameClock.setBounds(new Rectangle(702, 128, 237, 180));
			ClockPane pane = new ClockPane();
			pane.setBackground(Color.WHITE);
			jInternalFrameClock.setContentPane(pane);
			jInternalFrameClock.setSize(155, 175);

			jInternalFrameClock.setFrameIcon(null);
			//jInternalFrameClock.setLocationByPlatform(true);
			jInternalFrameClock.setLocation(new Point(836, 105));
			jInternalFrameClock.setResizable(false);
			jInternalFrameClock.setClosable(true);
			jInternalFrameClock.setTitle("Reloj");
			jInternalFrameClock.setVisible(true);
			jInternalFrameClock.setJMenuBar(null);
			jInternalFrameClock.setBorder(null);
			jInternalFrameClock.setOpaque(false);
		}
		return jInternalFrameClock;
	}

	public void getCalculadora(){
		try {
			 pcalc = Runtime.getRuntime().exec("calc.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	/**
	 * This method initializes jButtonCalculadora
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonCalculadora() {
		if (jButtonCalculadora == null) {
			jButtonCalculadora = new JButton();
			jButtonCalculadora.setPreferredSize(new Dimension(64, 64));
			jButtonCalculadora.setToolTipText("Calculadora");
			jButtonCalculadora.setIcon(new ImageIcon(getClass().getResource("/calculator_64x64.png")));
			jButtonCalculadora.setBorderPainted(false);
			jButtonCalculadora.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					getCalculadora();
				}
			});
		}
		return jButtonCalculadora;
	}


}  //  @jve:decl-index=0:visual-constraint="23,28"