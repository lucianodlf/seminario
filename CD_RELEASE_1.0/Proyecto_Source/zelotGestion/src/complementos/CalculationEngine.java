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

import java.util.Iterator;
import java.util.Vector;

import dominio.Articulo;
import dominio.Cliente;
import dominio.Factura;
import dominio.ItemFactura;
import dominio.ListaPrecio;

public class CalculationEngine {


	/*
	 * CALCULA EL PRECIO DE COSTO CON IMPUESTO INTERNO - DEL ARTICULO
	 */
	public static Float calcCostoConImpInt (Articulo articulo){
		Float costoConImpInt = null;
		if(articulo.getImpInterno() == null) articulo.setImpInterno(Float.valueOf(0));
		if(articulo.getImpInterno() != null && articulo.getCostoSinImp() != null){
			costoConImpInt = articulo.getCostoSinImp() + articulo.getImpInterno();
			return costoConImpInt;
		}else{
			return new Float(0);
		}

	}

	/*
	 * CALCULA EL PRECIO DE COSTO C/ IMP. INT. MAS %UTILIAD - DEL ARTICULO
	 */
	public static Float calcCostoConUtilidad (Articulo articulo){
		Float costoConUtilidad = null;
		Float costoConImpInt = calcCostoConImpInt(articulo);
		if(articulo.getMarckupDefaul() == null)articulo.setMarckupDefaul(Float.valueOf(0));
		if(articulo.getMarckupDefaul() != null && costoConImpInt != null){
			costoConUtilidad = costoConImpInt + (costoConImpInt * articulo.getMarckupDefaul());
			return costoConUtilidad;
		}else{
			return costoConImpInt;
		}
	}

	/*
	 * CALCULA EL PRECIO DE COSTO C/ IMP. INT. + %UTILIDAD + DESCUENTOS DEFAULT - DEL ARTICULO
	 */
	public static Float calcCostoConDesc (Articulo articulo){
		Float costoConDesc = null;
		Float costoConUtilidad = calcCostoConUtilidad(articulo);
		if(articulo.getDescuentoDefault() == null)articulo.setDescuentoDefault(Float.valueOf(0));
		if(articulo.getDescuentoDefault() != null && costoConUtilidad != null){
			costoConDesc = costoConUtilidad - (costoConUtilidad * articulo.getDescuentoDefault());
			return costoConDesc;
		}else{
			return costoConUtilidad;
		}
	}

	/*
	 * ******************PRECIO DE LISTA BASE***************************************
	 * CALCULA PRECIO DE LISTA CON LA UTILIDAD DE LISTA DE PRECIO.
	 * TOMA EL VALOR DEL COSTO C/IMP. INT. + %UTILIAD DEL ARTICULO + DESC. DEFAULT DEL ARTICULO
	 * Y LE AGREGA LA UTILIDAD DE LA LISTA DE PRECIOS.
	 * *****************************************************************************
	 */
	public static Float calcPreLista (Articulo articulo, ListaPrecio listaPrecio){
		Float preListaConUtilidad = null;
		Float preArticuloFinal = calcCostoConDesc(articulo); //precio de articulo C/ Imp. Int + %Utilidad + Recar. - Desc. del articulo
		if(listaPrecio.getUtilidad() == null)listaPrecio.setUtilidad(Float.valueOf(0));
		if(preArticuloFinal != null && listaPrecio.getUtilidad() != null){
			preListaConUtilidad = preArticuloFinal + (preArticuloFinal * listaPrecio.getUtilidad());
			return preListaConUtilidad;
		}else{
			return preArticuloFinal;
		}
	}

	/*
	 * CALCULA LOS DESCUENTOS DE LISTA SOBRE EL PRECIO DE LISTA BASE
	 */
	public static Float calcPreListaConDesc(Articulo articulo, ListaPrecio listaPrecio){
		Float preListaConDesc = null;
		Float preListaConUtilidad = calcPreLista(articulo, listaPrecio);
		if(listaPrecio.getDescuentoAdicional() == null)listaPrecio.setDescuentoAdicional(Float.valueOf(0));
		if(preListaConUtilidad != null && listaPrecio.getDescuentoAdicional() != null){
			preListaConDesc = preListaConUtilidad - (preListaConUtilidad * listaPrecio.getDescuentoAdicional());
			return preListaConDesc;
		}else{
			return preListaConUtilidad;
		}
	}

	/*
	 * CALCULA LOS RECARGOS DE LISTA SOBRE EL (PRECIO DE LISTA BASE - DESCUENTOS)
	 */
	public static Float calcPreListaConRec(Articulo articulo, ListaPrecio listaPrecio){
		Float preListaConRec = null;
		Float preListaConDesc = calcPreListaConDesc(articulo, listaPrecio);
		if(listaPrecio.getRecargoAdicional() == null)listaPrecio.setRecargoAdicional(Float.valueOf(0));
		if(preListaConDesc != null && listaPrecio.getRecargoAdicional() != null){
			preListaConRec = preListaConDesc + (preListaConDesc * listaPrecio.getRecargoAdicional());
			return preListaConRec;
		}else{
			return preListaConDesc;
		}
	}

	/*
	 * CALCULA EL PRECIO DEL ARTICULO CON LA ALICUOTA DE IVA ASOCIADA EN FUNCION DE LA LISTA DE PRECIOS
	 * SI LA LISTA DE PRECIOS TIENE O NO LA MARCA PARA CALCULAR IVA.
	 */
	public static Float calcPreConIvaSegunListaPre(Articulo articulo, ListaPrecio listaPrecio){
		Float precioListaFinal = calcPreListaConRec(articulo, listaPrecio); //precio del articulo final + %utilidad + Rec. - Desc. de la lista de precios
		Float preConIva = precioListaFinal;
		if(listaPrecio.isAplicaAlicuta()){
			if(precioListaFinal != null && articulo.getImpuesto() != null){
				Float alicuota = articulo.getImpuesto().getPorcentaje();
				if(alicuota != null){
					preConIva = precioListaFinal + (precioListaFinal * alicuota);
				}
			}
		}
		return preConIva;
	}

	/*
	 * CALCULA EL PRECIO DEL ARTICULO CON LA ALICUOTO DE IVA ASOCIADA
	 */
	public static Float calcPreConIva(Articulo articulo, Cliente cliente){
		Float precioListaFinal = calcPreListaConRec(articulo, cliente.getListaPrecios()); //precio del articulo final + %utilidad + Rec. - Desc. de la lista de precios
		Float preConIva = precioListaFinal;
		if(precioListaFinal != null && articulo.getImpuesto() != null){
			Float alicuota = articulo.getImpuesto().getPorcentaje();
			if(alicuota != null){
				preConIva = precioListaFinal + (precioListaFinal * alicuota);
			}
		}
		return preConIva;
	}



	/****** FACTURACION ************/

	/*
	 * CALCULA EL IMPORTE DEL IVA DISCRIMINADO
	 */
	public static  Float calcIvaDiscrm(Articulo articulo, Cliente cliente){
		Float preConIva = calcPreConIva(articulo, cliente);
		Float ivaDisc = preConIva * articulo.getImpuesto().getPorcentaje();
		return ivaDisc;
	}

	/*
	 * CALCULA EL PRECIO DE VENTA DEL ARTICULO DEPENDIENDO LA CONDICION DE IVA DEL CLIENTE.
	 * CALCULA EL IVA O NO SEGUN LA CONDICION DE IVA DEL CLIENTE
	 */
	public static Float calcPreVentaPorArtFact(Articulo articulo, Cliente cliente){
		Float precioListaFinal = calcPreListaConRec(articulo, cliente.getListaPrecios()); //precio del articulo final + %utilidad + Rec. - Desc. de la lista de precios
		Float resultado = new Float(0);
		if(cliente.getCondicionIVA() != null){
			if(cliente.getCondicionIVA().getCodigo() != 1 || cliente.getCondicionIVA().getCodigo() != 5){
				if(precioListaFinal != null && articulo.getImpuesto() != null){
					Float alicuota = articulo.getImpuesto().getPorcentaje();
					if(alicuota != null){
						resultado = precioListaFinal + (precioListaFinal * alicuota);
					}
				}else{
				resultado = precioListaFinal;
				}
			}
		}
		return resultado;
	}

	public static ItemFactura calculaImportesItemFactura(ItemFactura itFac, Cliente clie){
		ItemFactura itemFactura = null;
		Float preFinalUnit = null;
		Float preFinalTotal = null;
		Float preUnitBase = null;
		Float valueIva = null;
		if(itFac != null){
			itemFactura = itFac;
			if(itFac.getCantidad() != 0 && itFac.getCantidad() != null){
				preFinalUnit = calcPreConIva(itFac.getArticulo(), clie);
				valueIva = preFinalUnit - (preFinalUnit / (1 + itFac.getIva()));
				preFinalTotal = preFinalUnit * itFac.getCantidad();
				if(clie.getCondicionIVA().isDiscriminaIVA()){
					preUnitBase = preFinalUnit - valueIva;
				}else{
					preUnitBase = preFinalUnit;
				}
				if(!(itFac.getDescManual() == 0) && !(itFac.getDescManual() == null)){
					preFinalUnit = preFinalUnit - (preFinalUnit * itFac.getIva());
					preFinalUnit = preFinalUnit - (preFinalUnit * itFac.getDescManual());
					preFinalUnit = preFinalUnit + (preFinalUnit * itFac.getIva());
					preFinalTotal = preFinalUnit * itFac.getCantidad();
				}
				if(!(itFac.getRecManual() == 0) && !(itFac.getRecManual() == null)){
					preFinalUnit = preFinalUnit - (preFinalUnit * itFac.getIva());
					preFinalUnit = preFinalUnit + (preFinalUnit * itFac.getRecManual());
					preFinalUnit = preFinalUnit + (preFinalUnit * itFac.getIva());
					preFinalTotal = preFinalUnit * itFac.getCantidad();
				}
				itemFactura.setPrecioUnitario(preUnitBase);
				itemFactura.setPrecioTotal(preFinalTotal);
				itemFactura.setValorIvaCalculado(valueIva);

			}
		}
		return itemFactura;
	}


	public static Factura calculaFactura (Factura fac){
		Factura facResul = null;

		Vector<ItemFactura> itemsFactura = null;
		ItemFactura itemFac = null;

		Vector<ItemFactura> newItemsFactura = new Vector<ItemFactura>();

		Float aSubTotal = new Float(0);
		Float aTotalBonifClient =  new Float(0);
		Float aTotalRecarClient =  new Float(0);
		Float aTotalBonifGlobal =  new Float(0);
		Float aTotalRecarGlobal =  new Float(0);
		Float aTotalIva21 =  new Float(0);
		Float aTotalIva27 =  new Float(0);
		Float aTotalIva105 =  new Float(0);
		Float aTotalFinal =  new Float(0);

		//Float tFinalConDesc = new Float(0);
		//Float tFinalConRec = new Float(0);

		if(fac != null){
			facResul = fac;
			itemsFactura = fac.getItemsFactura();
			Iterator<ItemFactura> itItemsFac = itemsFactura.iterator();

			while(itItemsFac.hasNext()){
				ItemFactura itemFacI = (ItemFactura)itItemsFac.next();
				if(itemFacI.getArticulo().getCodigo() != 000000000){
					itemFac = calculaImportesItemFactura(itemFacI, fac.getCliente());
					newItemsFactura.add(itemFac);

					/*if(fac.getCliente().getCondicionIVA().isDiscriminaIVA()){
						aSubTotal += (itemFac.getPrecioTotal() - itemFac.getValorIvaCalculado());
					}else{
						aSubTotal += itemFac.getPrecioTotal();
					}*/
					aSubTotal += (itemFac.getPrecioUnitario() * itemFac.getCantidad());

					aTotalFinal += itemFac.getPrecioTotal();

					switch(itemFac.getArticulo().getImpuesto().getCodigo()){
						case 1: aTotalIva21 += itemFac.getValorIvaCalculado(); break;
						case 2: aTotalIva105 += itemFac.getValorIvaCalculado(); break;
						case 3: aTotalIva27 += itemFac.getValorIvaCalculado(); break;
					}

				}else{
					newItemsFactura.add(itemFacI);
					aSubTotal += itemFacI.getPrecioTotal();
					aTotalFinal += itemFacI.getPrecioTotal();
					/*aTotalIva21 += Float.valueOf(0);
					aTotalIva105 += Float.valueOf(0);
					aTotalIva27 += Float.valueOf(0);*/
				}
			}

			if(fac.getDescGlobal() != null){
				aTotalBonifGlobal = aTotalFinal * fac.getDescGlobal();
			}
			if(fac.getRecGlobal() != null){
				aTotalRecarGlobal = aTotalFinal * fac.getRecGlobal();
			}

			aTotalFinal = ((aTotalFinal + aTotalRecarGlobal) - aTotalBonifGlobal);


			facResul.setSubTotal(aSubTotal);
			facResul.setTotalFinal(aTotalFinal);
			facResul.setTotalBonifClient(aTotalBonifClient);
			facResul.setTotalBonifGlobal(aTotalBonifGlobal);
			facResul.setTotalIva105(aTotalIva105);
			facResul.setTotalIva21(aTotalIva21);
			facResul.setTotalIva27(aTotalIva27);
			facResul.setTotalRecarClient(aTotalRecarClient);
			facResul.setTotalRecarGlobal(aTotalRecarGlobal);
			facResul.setItemsFactura(newItemsFactura);
		}
		return facResul;
	}

	/*public static Factura calculaFactura (Factura preFac){
		Factura facResul = null;
		if(preFac != null){

		}
	}*/






}
