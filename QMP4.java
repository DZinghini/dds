
public class Prenda {
	Color colorPrincipal;
	Color colorSecundario;
	int maxTemperatura;
	int minTemperatura;

}


// Adapter con la Api del clima
class Meteorologo {

	//Deberia ser consultado cada 12 horas y guardado el resultado hasta la siguiente consulta, 
	//para asi reducir gastos innecesarios

	public List<Clima> obtenerClimaDe(String ciudad) {
		AccuWeatherAPI apiClima = new AccuWeatherAPI();
		List<Map<String, Object>> condicionesClimaticas = apiClima.getWeather(“Buenos Aires, Argentina”);

		return condicionesClimaticas.stream().map(condicionClimatica -> Meteorologo::traducirAClima).collect(toList());
	}

	private Clima traducirAClima(Map<String, Object> condicionesClimaticas) {
		Clima clima = new Clima();
		clima.setDateTime(condicionesClimaticas.get("DateTime"));
		clima.setPrecipitationProbability(condicionesClimaticas.get("PrecipitationProbability"));
		clima.setTemperatura(condicionesClimaticas.get("Temperatura").get("Value"));
		return clima;
	}
}

class Clima {
    DateTime dateTime;
    BigDecimal precipitationProbability;
    int Temperatura;
}



class Atuendo {
    Prenda prendaSuperior;
    Prenda prendaInferior;
    Prenda calzado;
}


class Sugerenciador {

    public List<Atuendo> sugerenciarAtuendos(List<Prenda> partesSuperior,
     List<Prenda> partesInferior,
     List<Prenda> calzados,
     List<Prenda> accesorios,
     int temperatura) {
       
    List<Prenda> partesInferiorAcordeTemperatura = this.filtrarPrendaPorClima(partesInferior, temperatura);   
	List<Prenda> partesSuperiorAcordeTemperatura = this.filtrarPrendaPorClima(partesSuperior, temperatura);   
	List<Prenda> calzadosAcordeTemperatura = this.filtrarPrendaPorClima(calzados, temperatura);   
	List<Prenda> accesoriosAcordeTemperatura = this.filtrarPrendaPorClima(accesorios, temperatura);   

	//Aca realizar explosion combinatoria entre las 4 listas y devolver una unica lista donde esten todas la combinaciones
	return new ArrayList(new Atuendo());
    }

    private List<Atuendo> filtrarPrendaPorClima(List<Prenda> prendas, int temperatura){
    	prendas
    	.streaem()
    	.filter(prenda -> prenda.getMaxTemperatura <= temperatura && prenda.getMinTemperatura >= temperatura)
    	.collect(toList());
    }


}

    

