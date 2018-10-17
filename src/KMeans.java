
public class KMeans {
	public Punkt[] punkty;
	public Centroid[] centroidy;
	
	public KMeans(int iloscPunktow, int iloscCentroidow){
		punkty = Punkt.createPunkt(iloscPunktow);
		centroidy = Centroid.createCentroid(iloscCentroidow);
	}

	public boolean calculate() {
		boolean key = true;
		int minIndex;
		double minOdleglosc;
		double temp;
		int ilosc[] = new int[centroidy.length];
		double x[] = new double[centroidy.length];
		double y[] = new double[centroidy.length];
		for(int i = 0; i < centroidy.length; i++){
			x[i] = 0;
			y[i] = 0;
			ilosc[i] = 0;
		}
		
		for(int i = 0; i < punkty.length; i++){
			minIndex = Integer.MAX_VALUE;
			minOdleglosc = Integer.MAX_VALUE;
			for(int j = 0; j < centroidy.length; j++){
				temp = Math.sqrt(Math.pow(punkty[i].x-centroidy[j].x,2) + Math.pow(punkty[i].y-centroidy[j].y,2));
				if(temp < minOdleglosc){
					minOdleglosc = temp;
					minIndex = j;
				}
			}
			x[minIndex] += punkty[i].x;
			y[minIndex] += punkty[i].y;
			ilosc[minIndex]++;
			if(punkty[i].getClasster() != minIndex){
				punkty[i].setClasster(minIndex);
				key = false;
			}
		}
		for(int i = 0; i < centroidy.length; i++){
			centroidy[i].x = x[i]/ilosc[i];
			centroidy[i].y = y[i]/ilosc[i];
		}
		return key;
	}

}
