
public class Punkt {
	double x;
	double y;
	private int centroid;
	
	public Punkt(){
		x = Math.random()*100;
		y = Math.random()*100;
		centroid = -1;
	}
	
	public static Punkt[] createPunkt(int n){
		Punkt arr[] = new Punkt[n];
		for(int i = 0; i < n; i++) arr[i] = new Punkt();
		return arr;
	}

	public int getClasster() {
		return centroid;
	}

	public void setClasster(int n) {
		centroid = n;
	}
}

