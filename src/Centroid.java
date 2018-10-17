import java.awt.Color;

public class Centroid {

	double x;
	double y;
	Color color;
	
	public Centroid(){
		x = Math.random() * 100;
		y = Math.random() * 100;
		color = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
	}
	
	public static Centroid[] createCentroid(int n){
		Centroid arr[] = new Centroid[n];
		for(int i = 0; i < n; i++) arr[i] = new Centroid();
		return arr;
	}
}
