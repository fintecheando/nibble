/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.nibble.util;

import dbbeans.configsistema.dbCONFIGSISTEMA;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import mx.bigdata.sat.cfd.v22.schema.Comprobante;
import mx.bigdata.sat.cfd.v22.schema.ObjectFactory;
import mx.bigdata.sat.cfd.v22.schema.TUbicacion;
import mx.bigdata.sat.cfd.v22.schema.TUbicacionFiscal;
import mx.bigdata.sat.cfd.v22.schema.Comprobante.Conceptos;
import mx.bigdata.sat.cfd.v22.schema.Comprobante.Emisor;
import mx.bigdata.sat.cfd.v22.schema.Comprobante.Impuestos;
import mx.bigdata.sat.cfd.v22.schema.Comprobante.Receptor;
import mx.bigdata.sat.cfd.v22.schema.Comprobante.Conceptos.Concepto;
import mx.bigdata.sat.cfd.v22.schema.Comprobante.Impuestos.Traslados;
import mx.bigdata.sat.cfd.v22.schema.Comprobante.Impuestos.Traslados.Traslado;

import dbbeans.configuracionpva.dbCONFIGURACIONPVA;
import java.text.SimpleDateFormat;
import mx.bigdata.sat.cfd.v22.schema.Comprobante.Emisor.RegimenFiscal;
import pva.beans9.Bean91;
import pva.beans9.Bean91P;

/**
 *
 * @author 43507296
 */
public class CFDFactory {

    private dbCONFIGURACIONPVA configuracionpva;
    private dbCONFIGSISTEMA configsistema;
    private Bean91 bean91;
    private Bean91P bean91p;

    public Comprobante createComprobante() throws Exception {
    
    ObjectFactory of = new ObjectFactory();
    Comprobante comp = of.createComprobante();
    comp.setVersion("2.2");
    
    
    comp.setFecha(bean91.getDate());        
    /*java.util.Date temp = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss")
                .parse("2014-02-04@22:43:08");
        System.out.println(temp);
    comp.setFecha(temp);*/
    comp.setSerie(configuracionpva.getVchserie());
    comp.setFolio(new Integer(bean91.getFolio()).toString());        
    comp.setNoAprobacion(new BigInteger(new Integer(configuracionpva.getInumaprobacion()).toString()));    
    comp.setAnoAprobacion(new BigInteger(new Integer(configuracionpva.getIanoaprobacion()).toString()));    
    comp.setSubTotal(new BigDecimal(new Float(this.bean91.getSubtotal()).toString()));
    comp.setTotal(new BigDecimal(this.bean91.getTotalSTR()));
    comp.setDescuento(new BigDecimal(this.bean91.getMontodescuento()));
    comp.setMotivoDescuento(this.bean91.getMotivoDescuento());
    comp.setTipoDeComprobante("ingreso");
    comp.setEmisor(createEmisor(of));
    comp.setReceptor(createReceptor(of));
    comp.setConceptos(createConceptos(of));
    comp.setImpuestos(createImpuestos(of));
    //REQUERIDO 2012
    System.out.println("METODO PAGO "+this.bean91p.getVchmetodopago());
    System.out.println("CUENTA PAGO "+this.bean91p.getVchmcuentapago());    
    
    comp.setMetodoDePago(this.bean91p.getVchmetodopago());    
    comp.setNumCtaPago(this.bean91p.getVchmcuentapago());
    //comp.setFormaDePago(this.bean91p.getVchformapago());
    comp.setFormaDePago("UNA SOLA EXHIBICION");
    comp.setLugarExpedicion("MEXICO, D.F");
    //comp.setNumCtaPago("1234");
    return comp;
  }

  private Emisor createEmisor(ObjectFactory of) {
    Emisor emisor = of.createComprobanteEmisor();
    emisor.setNombre(this.configuracionpva.getVchrazonsocial());
    emisor.setRfc(this.configuracionpva.getVchrfc());
    TUbicacionFiscal uf = of.createTUbicacionFiscal();
    uf.setCalle(this.configuracionpva.getVchcalle());
    uf.setCodigoPostal(this.configuracionpva.getVchcodigopostal());
    uf.setColonia(this.configuracionpva.getVchcolonia());
    uf.setLocalidad(this.configuracionpva.getVchlocalidad());
    uf.setMunicipio(this.configuracionpva.getVchmunicipio());
    uf.setEstado(this.configuracionpva.getVchestado());
    uf.setNoExterior(this.configuracionpva.getVchnumexterior());
    //uf.setNoInterior("");
    uf.setPais(this.configuracionpva.getVchpais());
    emisor.setDomicilioFiscal(uf);
    //REQUERIDO 2012
    RegimenFiscal rf = of.createComprobanteEmisorRegimenFiscal();
    rf.setRegimen("simplificado");
    emisor.getRegimenFiscal().add(rf);
    return emisor;
  }

  private Receptor createReceptor(ObjectFactory of) {
    Receptor receptor = of.createComprobanteReceptor();
    receptor.setNombre(this.bean91p.getClienteRazonSocial());
    receptor.setRfc(this.bean91p.getClienteRFC());
    TUbicacion uf = of.createTUbicacion();
    uf.setCalle(this.bean91p.getClienteCalle());
    uf.setCodigoPostal(this.bean91p.getClienteCodigoPostal());
    uf.setColonia(this.bean91p.getClienteColonia());
    if (this.bean91p.getClienteLocalidad() != null
            && this.bean91p.getClienteLocalidad().equals("") != true){
        uf.setLocalidad(this.bean91p.getClienteLocalidad());
    }
    uf.setMunicipio(this.bean91p.getClienteMunicipio());
    uf.setEstado(this.bean91p.getClienteEstado());
    uf.setNoExterior(this.bean91p.getClienteNumExterior());
    if (this.bean91p.getClienteNumInterior() != null
            && this.bean91p.getClienteNumInterior().equals("") != true){
        uf.setNoInterior(this.bean91p.getClienteNumInterior());
    }
    uf.setPais(this.bean91p.getClientePais());
    receptor.setDomicilio(uf);
    
    return receptor;
  }

  private Conceptos createConceptos(ObjectFactory of) {
    
    Conceptos cps = of.createComprobanteConceptos();
    List<Concepto> list = cps.getConcepto();
    int decimalPlaces = 2;
    for (int i = 0; i < bean91.getVPartidasSize(); i++) {
        Concepto c1 = of.createComprobanteConceptosConcepto();
        c1.setCantidad(new BigDecimal(bean91.getVPartidasCantidad(i)));
        c1.setNoIdentificacion(bean91.getVPartidasVchnumparte(i));
        c1.setUnidad(bean91.getVPartidasUnidadDesc(i));
        c1.setImporte(new BigDecimal(bean91.getVPartidasPrecioUnitario(i) * bean91.getVPartidasCantidad(i)).setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP));
        c1.setDescripcion(bean91.getVPartidasDescripcion(i));
        c1.setValorUnitario(new BigDecimal(bean91.getVPartidasPrecioUnitario(i)).setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP));
        list.add(c1);
    }
    return cps;
  }

  private Impuestos createImpuestos(ObjectFactory of) {
      int decimalPlaces = 2;
    Impuestos imps = of.createComprobanteImpuestos();
    imps.setTotalImpuestosTrasladados(new BigDecimal(this.bean91.getMontoivaSTR()).setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP));
    Traslados trs = of.createComprobanteImpuestosTraslados();
    List<Traslado> list = trs.getTraslado();
    Traslado t1 = of.createComprobanteImpuestosTrasladosTraslado();
    t1.setImporte(new BigDecimal(this.bean91.getMontoivaSTR()).setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP));
    t1.setImpuesto("IVA");
    t1.setTasa(new BigDecimal(this.configsistema.getDecivaaplicable()).setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP));
    list.add(t1);
    imps.setTraslados(trs);
    return imps;
  }

    /**
     * @return the configuracionpva
     */
    public dbCONFIGURACIONPVA getConfiguracionpva() {
        return configuracionpva;
    }

    /**
     * @param configuracionpva the configuracionpva to set
     */
    public void setConfiguracionpva(dbCONFIGURACIONPVA configuracionpva) {
        this.configuracionpva = configuracionpva;
    }

    /**
     * @return the bean91
     */
    public Bean91 getBean91() {
        return bean91;
    }

    /**
     * @param bean91 the bean91 to set
     */
    public void setBean91(Bean91 bean91) {
        this.bean91 = bean91;
    }

    /**
     * @return the bean91p
     */
    public Bean91P getBean91p() {
        return bean91p;
    }

    /**
     * @param bean91p the bean91p to set
     */
    public void setBean91p(Bean91P bean91p) {
        this.bean91p = bean91p;
    }

    /**
     * @return the configsistema
     */
    public dbCONFIGSISTEMA getConfigsistema() {
        return configsistema;
    }

    /**
     * @param configsistema the configsistema to set
     */
    public void setConfigsistema(dbCONFIGSISTEMA configsistema) {
        this.configsistema = configsistema;
    }

  

}
