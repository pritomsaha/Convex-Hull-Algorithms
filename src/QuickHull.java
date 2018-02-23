import java.util.ArrayList;

public class QuickHull {
	
	public static ArrayList<Point> convexHull;
	
	public static int pointLocation(Point A, Point B, Point P){
		
		int vecProduct = (B.x-A.x)*(P.y-A.y) - (B.y-A.y)*(P.x-A.x);
	    
		if(vecProduct==0)return 0;
	    else if(vecProduct>0) return 1;	    
	    return -1;
	}
	
	public static int distance(Point A, Point B,Point C){
		
		int dis=(A.y-B.y)*(C.x-A.x)-(A.x-B.x)*(C.y-A.y);				
		return Math.abs(dis);
	}
	
	public static ArrayList<Point> getConvexHull(ArrayList<Point> points){
		
		convexHull = new ArrayList<>();
		
		if(points.size()<3)
			return points;
		
		Point A = null,B = null;
		
		int MaxX=Integer.MIN_VALUE;
		int MinX=Integer.MAX_VALUE;
		
		for(Point p: points){
			if(p.x<MinX){
				MinX=p.x;
				A=p;
			}
			if(p.x>MaxX){
				MaxX=p.x;
				B=p;
			}	
		}
		
		convexHull.add(A);
		convexHull.add(B);
		
		points.remove(A);
		points.remove(B);
		
		ArrayList<Point> RightSet = new ArrayList<>();
		ArrayList<Point> LeftSet = new ArrayList<>();
		
		for(Point P: points){
			
			if(pointLocation(A, B, P)==-1)	
				LeftSet.add(P);
			else	
				RightSet.add(P);			 
		}
		
		FindHull(A, B, RightSet);
		FindHull(B, A, LeftSet);
		
		return convexHull;
	}
	
	public static void FindHull(Point A, Point B, ArrayList<Point> Set){
		
		if(Set.size()==0) return;
		
		else if(Set.size()==1){
			
			convexHull.add(Set.get(0));
			Set.remove(0);
			return;
		}
		
		Point C=null;
		int MaxDis=Integer.MIN_VALUE;
		for(Point P: Set ){
			
			int distance=distance(A, B, P);
			
			if(distance>MaxDis){
				
				MaxDis=distance;
				C=P;
			}	
		}
		convexHull.add(C);
		Set.remove(C);
		
		ArrayList<Point> LeftSetOfAC=new ArrayList<Point>();
		ArrayList<Point> LeftSetOfCB=new ArrayList<Point>();
		
		for(Point P: Set){
			
			if(pointLocation(A, C, P)==1)
				LeftSetOfAC.add(P);
			if(pointLocation(C, B, P)==1)
				LeftSetOfCB.add(P);
		}
		
		FindHull(A, C, LeftSetOfAC);
		FindHull(C, B, LeftSetOfCB);
		
	}
	
	public static void main(String[] args){
		
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Point> convex = new ArrayList<>();
		
		points.add(new Point(1, 2));
		points.add(new Point(3, -1));
		points.add(new Point(-4, 19));
		points.add(new Point(12, 2));
		points.add(new Point(9, 10));
		points.add(new Point(7, 27));
		points.add(new Point(22, 9));
		points.add(new Point(5, 11));
		points.add(new Point(17, 23));
		points.add(new Point(-3, -2));
		points.add(new Point(-2, 7));
		points.add(new Point(3, -2));
		points.add(new Point(-3, 0));
		points.add(new Point(3, 0));
		points.add(new Point(0, 3));
		points.add(new Point(-4, -3));
		points.add(new Point(3, 3));
		points.add(new Point(3, -3));
		points.add(new Point(-3, 3));
		points.add(new Point(-5, 0));
		points.add(new Point(-3, -1));
		points.add(new Point(-2, 2));
		
		convex = getConvexHull(points);
		
		for(Point P: convex)
			System.out.println("("+P.x+","+P.y+")");		
	}
}
