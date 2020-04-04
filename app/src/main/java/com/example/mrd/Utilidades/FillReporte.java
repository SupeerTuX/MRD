package com.example.mrd.Utilidades;

import com.example.mrd.DataModel.ClienteData;
import com.example.mrd.DataModel.ExteriorData;
import com.example.mrd.DataModel.FormularioModel;
import com.example.mrd.DataModel.InteriorData;
import com.example.mrd.DataModel.MotorData;
import com.example.mrd.DataModel.ReportModel;

public class FillReporte {

    private ClienteData clienteData;
    private ReportModel reportModel;
    private ExteriorData exteriorData;
    private InteriorData interiorData;
    private MotorData motorData;

    public FillReporte(ClienteData clienteData, ReportModel reportModel, ExteriorData exteriorData, InteriorData interiorData, MotorData motorData) {
        this.clienteData = clienteData;
        this.reportModel = reportModel;
        this.exteriorData = exteriorData;
        this.interiorData = interiorData;
        this.motorData = motorData;
    }

    public ReportModel fill(){
        //Cliente Data
        this.reportModel.setRegion(this.clienteData.getRegion().toLowerCase());
        this.reportModel.setFolio(this.clienteData.getFolio());
        this.reportModel.setFecha(this.clienteData.getDateDB());
        this.reportModel.setDireccion(this.clienteData.getDireccion());
        this.reportModel.setMotivoInventario(this.clienteData.getMotivoInventario());
        this.reportModel.setVahiculoMarca(this.clienteData.getVehiculoMarca());
        this.reportModel.setTipo(this.clienteData.getVehiculoTipo());
        this.reportModel.setModelo(this.clienteData.getVehiculoModelo());
        this.reportModel.setColor(this.clienteData.getVehiculoColor());
        this.reportModel.setPlacas(this.clienteData.getVehiculoPlacas());
        this.reportModel.setNoSerie(this.clienteData.getVehiculoSerie());
        this.reportModel.setNombreConductor(this.clienteData.getVehiculoPropietario());
        this.reportModel.setLlaves(this.clienteData.getVehiculoLlaves());
        this.reportModel.setTelefono(this.clienteData.getTelefono());
        this.reportModel.setGrua(this.clienteData.getOperadorGrua());
        this.reportModel.setClaveOperador(this.clienteData.getOperadorClave());
        this.reportModel.setSolicitante(this.clienteData.getAutoridad());

        //Exterior Data
        String[] exData = exteriorData.getData();
        this.reportModel.setDefensaDelantera(exData[0]);
        this.reportModel.setCarroceriaSinGolpes(exData[1]);
        this.reportModel.setParrilla(exData[2]);
        this.reportModel.setFaros(exData[3]);
        this.reportModel.setCofre(exData[4]);
        this.reportModel.setParabrisas(exData[5]);
        this.reportModel.setLimpiadores(exData[6]);
        this.reportModel.setEmblemas(exData[7]);
        this.reportModel.setPortezuelaIzq(exData[8]);
        this.reportModel.setCristalLatIzq(exData[9]);
        this.reportModel.setMedallon(exData[10]);
        this.reportModel.setCajuela(exData[11]);
        this.reportModel.setDefensaTrasera(exData[12]);
        this.reportModel.setPortezuelaDer(exData[13]);
        this.reportModel.setCristalLatDer(exData[14]);
        this.reportModel.setAntenas(exData[15]);
        this.reportModel.setEspejos(exData[16]);
        this.reportModel.setTaponesRuedas(exData[17]);
        this.reportModel.setTaponGasolina(exData[18]);
        this.reportModel.setSalpicaderaDer(exData[19]);
        this.reportModel.setSalpicaderaIzq(exData[20]);

        //Interior Data
        String[] inData = interiorData.getData();
        this.reportModel.setTablero(inData[0]);
        this.reportModel.setVolante(inData[1]);
        this.reportModel.setRadio(inData[2]);
        this.reportModel.setEquipoSonido(inData[3]);
        this.reportModel.setEncendedor(inData[4]);
        this.reportModel.setEspejo(inData[5]);
        this.reportModel.setAsientos(inData[6]);
        this.reportModel.setTapetesAlfombra(inData[7]);
        this.reportModel.setTapetesHule(inData[8]);
        this.reportModel.setExtintor(inData[9]);
        this.reportModel.setGatoYManeral(inData[10]);
        this.reportModel.setTrianguloDeSeguridad(inData[11]);
        this.reportModel.setBocinas(inData[12]);
        this.reportModel.setLuces(inData[13]);
        this.reportModel.setTag(inData[14]);
        this.reportModel.setVialPass(inData[15]);
        this.reportModel.setSimCard(inData[16]);

        //Motor Data
        String[] mData = motorData.getData();
        this.reportModel.setRadiador(mData[0]);
        this.reportModel.setMotoventilador(mData[1]);
        this.reportModel.setAlternador(mData[2]);
        this.reportModel.setCableDeBujias(mData[3]);
        this.reportModel.setDepurador(mData[4]);
        this.reportModel.setCarburador(mData[5]);
        this.reportModel.setFiltroAire(mData[6]);
        this.reportModel.setInyectores(mData[7]);
        this.reportModel.setCompresor(mData[8]);
        this.reportModel.setComputadora(mData[9]);
        this.reportModel.setBateria(mData[10]);

        //Footer
        this.reportModel.setMarcaMotor(motorData.getMarcaBateria());
        this.reportModel.setMarcaLlantas(interiorData.getLlantasMarca());
        this.reportModel.setMedidaLlantas(interiorData.getLlantasMedida());
        this.reportModel.setCantidadLlantas(Integer.parseInt(interiorData.getLlantasCantidad()));
        this.reportModel.setTanqueGasolina(Integer.parseInt(motorData.getMedidorGasolina()));
        this.reportModel.setCargaConsistente(motorData.getMotorCarga());
        this.reportModel.setObservaciones(motorData.getMotorObservaciones());
        this.reportModel.setNombreEntrega("No Aplica");
        this.reportModel.setNombreDeOficial("No Aplica");
        this.reportModel.setNombreOperador("No Aplica");







        return reportModel;
    }


}
