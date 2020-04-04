package com.example.mrd.DataModel;

//Modelo de datos completo para subir informacion al servidor en formato json
public class ReportModel {
    public ReportModel() {
    }


    //Copia datos de prueba
    public void setDataExample(){
        this.Fecha = "2020-03-04 17:00:00";
        this.Direccion = "Juan de la barrera #12";
        this.MotivoInventario = "Mal Estacionado";
        this.VahiculoMarca = "Ford";
        this.Tipo = "Sedan";
        this.Modelo = "2000";
        this.Color = "Azul";
        this.Placas = "AWD123";
        this.NoSerie = "ZS1425263415";
        this.NombreConductor = "Isabelo Mendoza";
        this.Llaves = "Si";
        this.Telefono = "2281415263";
        this.Grua = "XAL002";
        this.ClaveOperador = "G002";
        this.Solicitante = "Transito del estado";
        this.DefensaDelantera = "Bien";
        this.CarroceriaSinGolpes = "Bien";
        this.Parrilla = "Bien";
        this.Faros = "Mal";
        this.Cofre = "Mal";
        this.Parabrisas = "No Trae";
        this.Limpiadores = "Bien";
        this.Emblemas = "Bien";
        this.PortezuelaIzq = "Bien";
        this.CristalLatIzq = "Bien";
        this.Medallon = "Bien";
        this.Cajuela = "Bien";
        this.DefensaTrasera = "Bien";
        this.PortezuelaDer = "Bien";
        this.CristalLatDer = "Bien";
        this.Antenas = "Bien";
        this.Espejos = "Bien";
        this.TaponesRuedas = "Bien";
        this.TaponGasolina = "Bien";
        this.SalpicaderaDer = "Bien";
        this.SalpicaderaIzq = "Bien";
        this.Tablero = "Bien";
        this.Volante = "Bien";
        this.Radio = "Bien";
        this.EquipoSonido = "Bien";
        this.Encendedor = "Bien";
        this.Espejo = "Bien";
        this.Asientos = "Bien";
        this.TapetesAlfombra = "Bien";
        this.TapetesHule = "Bien";
        this.Extintor = "Bien";
        this.GatoYManeral = "Bien";
        this.TrianguloDeSeguridad = "Bien";
        this.Bocinas = "Bien";
        this.Luces = "Bien";
        this.Tag = "Bien";
        this.VialPass = "Bien";
        this.SimCard = "Bien";
        this.Radiador = "Bien";
        this.Motoventilador = "Bien";
        this.Alternador = "Bien";
        this.CableDeBujias = "Bien";
        this.Depurador = "Bien";
        this.Carburador = "Bien";
        this.FiltroAire = "Bien";
        this.Inyectores = "Bien";
        this.Compresor = "Bien";
        this.Computadora = "Bien";
        this.Bateria = "Bien";
        this.MarcaMotor = "Goner";
        this.MarcaLlantas = "Tornel";
        this.MedidaLlantas = "R17";
        this.CantidadLlantas = 5;
        this.TanqueGasolina = 4.0;
        this.CargaConsistente = "Carga de Vehiculo";
        this.Observaciones = "Arrastre OK";
        this.NombreEntrega = "Pedro Perez";
        this.NombreDeOficial = "Pablo Perez";
        this.NombreOperador = "Juan Pablo Perez";
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getFolio() {
        return Folio;
    }

    public void setFolio(String folio) {
        Folio = folio;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getMotivoInventario() {
        return MotivoInventario;
    }

    public void setMotivoInventario(String motivoInventario) {
        MotivoInventario = motivoInventario;
    }

    public String getVahiculoMarca() {
        return VahiculoMarca;
    }

    public void setVahiculoMarca(String vahiculoMarca) {
        VahiculoMarca = vahiculoMarca;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getPlacas() {
        return Placas;
    }

    public void setPlacas(String placas) {
        Placas = placas;
    }

    public String getNoSerie() {
        return NoSerie;
    }

    public void setNoSerie(String noSerie) {
        NoSerie = noSerie;
    }

    public String getNombreConductor() {
        return NombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        NombreConductor = nombreConductor;
    }

    public String getLlaves() {
        return Llaves;
    }

    public void setLlaves(String llaves) {
        Llaves = llaves;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getGrua() {
        return Grua;
    }

    public void setGrua(String grua) {
        Grua = grua;
    }

    public String getClaveOperador() {
        return ClaveOperador;
    }

    public void setClaveOperador(String claveOperador) {
        ClaveOperador = claveOperador;
    }

    public String getSolicitante() {
        return Solicitante;
    }

    public void setSolicitante(String solicitante) {
        Solicitante = solicitante;
    }

    public String getDefensaDelantera() {
        return DefensaDelantera;
    }

    public void setDefensaDelantera(String defensaDelantera) {
        DefensaDelantera = defensaDelantera;
    }

    public String getCarroceriaSinGolpes() {
        return CarroceriaSinGolpes;
    }

    public void setCarroceriaSinGolpes(String carroceriaSinGolpes) {
        CarroceriaSinGolpes = carroceriaSinGolpes;
    }

    public String getParrilla() {
        return Parrilla;
    }

    public void setParrilla(String parrilla) {
        Parrilla = parrilla;
    }

    public String getFaros() {
        return Faros;
    }

    public void setFaros(String faros) {
        Faros = faros;
    }

    public String getCofre() {
        return Cofre;
    }

    public void setCofre(String cofre) {
        Cofre = cofre;
    }

    public String getParabrisas() {
        return Parabrisas;
    }

    public void setParabrisas(String parabrisas) {
        Parabrisas = parabrisas;
    }

    public String getLimpiadores() {
        return Limpiadores;
    }

    public void setLimpiadores(String limpiadores) {
        Limpiadores = limpiadores;
    }

    public String getEmblemas() {
        return Emblemas;
    }

    public void setEmblemas(String emblemas) {
        Emblemas = emblemas;
    }

    public String getPortezuelaIzq() {
        return PortezuelaIzq;
    }

    public void setPortezuelaIzq(String portezuelaIzq) {
        PortezuelaIzq = portezuelaIzq;
    }

    public String getCristalLatIzq() {
        return CristalLatIzq;
    }

    public void setCristalLatIzq(String cristalLatIzq) {
        CristalLatIzq = cristalLatIzq;
    }

    public String getMedallon() {
        return Medallon;
    }

    public void setMedallon(String medallon) {
        Medallon = medallon;
    }

    public String getCajuela() {
        return Cajuela;
    }

    public void setCajuela(String cajuela) {
        Cajuela = cajuela;
    }

    public String getDefensaTrasera() {
        return DefensaTrasera;
    }

    public void setDefensaTrasera(String defensaTrasera) {
        DefensaTrasera = defensaTrasera;
    }

    public String getPortezuelaDer() {
        return PortezuelaDer;
    }

    public void setPortezuelaDer(String portezuelaDer) {
        PortezuelaDer = portezuelaDer;
    }

    public String getCristalLatDer() {
        return CristalLatDer;
    }

    public void setCristalLatDer(String cristalLatDer) {
        CristalLatDer = cristalLatDer;
    }

    public String getAntenas() {
        return Antenas;
    }

    public void setAntenas(String antenas) {
        Antenas = antenas;
    }

    public String getEspejos() {
        return Espejos;
    }

    public void setEspejos(String espejos) {
        Espejos = espejos;
    }

    public String getTaponesRuedas() {
        return TaponesRuedas;
    }

    public void setTaponesRuedas(String taponesRuedas) {
        TaponesRuedas = taponesRuedas;
    }

    public String getTaponGasolina() {
        return TaponGasolina;
    }

    public void setTaponGasolina(String taponGasolina) {
        TaponGasolina = taponGasolina;
    }

    public String getSalpicaderaDer() {
        return SalpicaderaDer;
    }

    public void setSalpicaderaDer(String salpicaderaDer) {
        SalpicaderaDer = salpicaderaDer;
    }

    public String getSalpicaderaIzq() {
        return SalpicaderaIzq;
    }

    public void setSalpicaderaIzq(String salpicaderaIzq) {
        SalpicaderaIzq = salpicaderaIzq;
    }

    public String getTablero() {
        return Tablero;
    }

    public void setTablero(String tablero) {
        Tablero = tablero;
    }

    public String getVolante() {
        return Volante;
    }

    public void setVolante(String volante) {
        Volante = volante;
    }

    public String getRadio() {
        return Radio;
    }

    public void setRadio(String radio) {
        Radio = radio;
    }

    public String getEquipoSonido() {
        return EquipoSonido;
    }

    public void setEquipoSonido(String equipoSonido) {
        EquipoSonido = equipoSonido;
    }

    public String getEncendedor() {
        return Encendedor;
    }

    public void setEncendedor(String encendedor) {
        Encendedor = encendedor;
    }

    public String getEspejo() {
        return Espejo;
    }

    public void setEspejo(String espejo) {
        Espejo = espejo;
    }

    public String getAsientos() {
        return Asientos;
    }

    public void setAsientos(String asientos) {
        Asientos = asientos;
    }

    public String getTapetesAlfombra() {
        return TapetesAlfombra;
    }

    public void setTapetesAlfombra(String tapetesAlfombra) {
        TapetesAlfombra = tapetesAlfombra;
    }

    public String getTapetesHule() {
        return TapetesHule;
    }

    public void setTapetesHule(String tapetesHule) {
        TapetesHule = tapetesHule;
    }

    public String getExtintor() {
        return Extintor;
    }

    public void setExtintor(String extintor) {
        Extintor = extintor;
    }

    public String getGatoYManeral() {
        return GatoYManeral;
    }

    public void setGatoYManeral(String gatoYManeral) {
        GatoYManeral = gatoYManeral;
    }

    public String getTrianguloDeSeguridad() {
        return TrianguloDeSeguridad;
    }

    public void setTrianguloDeSeguridad(String trianguloDeSeguridad) {
        TrianguloDeSeguridad = trianguloDeSeguridad;
    }

    public String getBocinas() {
        return Bocinas;
    }

    public void setBocinas(String bocinas) {
        Bocinas = bocinas;
    }

    public String getLuces() {
        return Luces;
    }

    public void setLuces(String luces) {
        Luces = luces;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getVialPass() {
        return VialPass;
    }

    public void setVialPass(String vialPass) {
        VialPass = vialPass;
    }

    public String getSimCard() {
        return SimCard;
    }

    public void setSimCard(String simCard) {
        SimCard = simCard;
    }

    public String getRadiador() {
        return Radiador;
    }

    public void setRadiador(String radiador) {
        Radiador = radiador;
    }

    public String getMotoventilador() {
        return Motoventilador;
    }

    public void setMotoventilador(String motoventilador) {
        Motoventilador = motoventilador;
    }

    public String getAlternador() {
        return Alternador;
    }

    public void setAlternador(String alternador) {
        Alternador = alternador;
    }

    public String getCableDeBujias() {
        return CableDeBujias;
    }

    public void setCableDeBujias(String cableDeBujias) {
        CableDeBujias = cableDeBujias;
    }

    public String getDepurador() {
        return Depurador;
    }

    public void setDepurador(String depurador) {
        Depurador = depurador;
    }

    public String getCarburador() {
        return Carburador;
    }

    public void setCarburador(String carburador) {
        Carburador = carburador;
    }

    public String getFiltroAire() {
        return FiltroAire;
    }

    public void setFiltroAire(String filtroAire) {
        FiltroAire = filtroAire;
    }

    public String getInyectores() {
        return Inyectores;
    }

    public void setInyectores(String inyectores) {
        Inyectores = inyectores;
    }

    public String getCompresor() {
        return Compresor;
    }

    public void setCompresor(String compresor) {
        Compresor = compresor;
    }

    public String getComputadora() {
        return Computadora;
    }

    public void setComputadora(String computadora) {
        Computadora = computadora;
    }

    public String getBateria() {
        return Bateria;
    }

    public void setBateria(String bateria) {
        Bateria = bateria;
    }

    public String getMarcaMotor() {
        return MarcaMotor;
    }

    public void setMarcaMotor(String marcaMotor) {
        MarcaMotor = marcaMotor;
    }

    public String getMarcaLlantas() {
        return MarcaLlantas;
    }

    public void setMarcaLlantas(String marcaLlantas) {
        MarcaLlantas = marcaLlantas;
    }

    public String getMedidaLlantas() {
        return MedidaLlantas;
    }

    public void setMedidaLlantas(String medidaLlantas) {
        MedidaLlantas = medidaLlantas;
    }

    public int getCantidadLlantas() {
        return CantidadLlantas;
    }

    public void setCantidadLlantas(int cantidadLlantas) {
        CantidadLlantas = cantidadLlantas;
    }

    public double getTanqueGasolina() {
        return TanqueGasolina;
    }

    public void setTanqueGasolina(double tanqueGasolina) {
        TanqueGasolina = tanqueGasolina;
    }

    public String getCargaConsistente() {
        return CargaConsistente;
    }

    public void setCargaConsistente(String cargaConsistente) {
        CargaConsistente = cargaConsistente;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }

    public String getNombreEntrega() {
        return NombreEntrega;
    }

    public void setNombreEntrega(String nombreEntrega) {
        NombreEntrega = nombreEntrega;
    }

    public String getNombreDeOficial() {
        return NombreDeOficial;
    }

    public void setNombreDeOficial(String nombreDeOficial) {
        NombreDeOficial = nombreDeOficial;
    }

    public String getNombreOperador() {
        return NombreOperador;
    }

    public void setNombreOperador(String nombreOperador) {
        NombreOperador = nombreOperador;
    }

    private String Region;
    private String Folio;
    private String Fecha;
    private String Direccion;
    private String MotivoInventario;
    private String VahiculoMarca;
    private String Tipo;
    private String Modelo;
    private String Color;
    private String Placas;
    private String NoSerie;
    private String NombreConductor;
    private String Llaves;
    private String Telefono;
    private String Grua;
    private String ClaveOperador;
    private String Solicitante;
    private String DefensaDelantera;
    private String CarroceriaSinGolpes;
    private String Parrilla;
    private String Faros;
    private String Cofre;
    private String Parabrisas;
    private String Limpiadores;
    private String Emblemas;
    private String PortezuelaIzq;
    private String CristalLatIzq;
    private String Medallon;
    private String Cajuela;
    private String DefensaTrasera;
    private String PortezuelaDer;
    private String CristalLatDer;
    private String Antenas;
    private String Espejos;
    private String TaponesRuedas;
    private String TaponGasolina;
    private String SalpicaderaDer;
    private String SalpicaderaIzq;
    private String Tablero;
    private String Volante;
    private String Radio;
    private String EquipoSonido;
    private String Encendedor;
    private String Espejo;
    private String Asientos;
    private String TapetesAlfombra;
    private String TapetesHule;
    private String Extintor;
    private String GatoYManeral;
    private String TrianguloDeSeguridad;
    private String Bocinas;
    private String Luces;
    private String Tag;
    private String VialPass;
    private String SimCard;
    private String Radiador;
    private String Motoventilador;
    private String Alternador;
    private String CableDeBujias;
    private String Depurador;
    private String Carburador;
    private String FiltroAire;
    private String Inyectores;
    private String Compresor;
    private String Computadora;
    private String Bateria;
    private String MarcaMotor;
    private String MarcaLlantas;
    private String MedidaLlantas;
    private int CantidadLlantas;
    private double TanqueGasolina;
    private String CargaConsistente;
    private String Observaciones;
    private String NombreEntrega;
    private String NombreDeOficial;
    private String NombreOperador;

}
