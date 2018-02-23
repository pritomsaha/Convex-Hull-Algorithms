import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class GrahamScan {
	
		public static Point rootPoint;
				
		public static int checkAngle(Point a, Point b, Point c){
			
			int vectorProduct = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
			
			if(vectorProduct>0) return -1;
			else if(vectorProduct<0) return 1;
		
			return 0;
		}
		
		public static int pointDistance(Point a, Point b){
			
			int X = a.x - b.x, Y = a.y - b.y;
			return X * X + Y * Y;
		}

		public static Stack<Point> getConvexHull(ArrayList<Point> Points){
			
			Stack<Point> convexHull = new Stack<>();
			int numOfpoints = Points.size();
			if(numOfpoints<4){
				for(Point p: Points)
					convexHull.push(p);
				return convexHull;
			}
			
			rootPoint= new Point(Integer.MAX_VALUE,Integer.MAX_VALUE);
			for(Point p: Points){
				
				if(p.y<rootPoint.y){
					rootPoint=p;
				}
				else if(p.y==rootPoint.y&&p.x<rootPoint.x)
					rootPoint=p;
			}
			
			Collections.sort(Points, new Comparator<Point>() {
				@Override
				public int compare(Point a, Point b) {
					int temp=checkAngle(rootPoint, a, b);
					if(temp==0)
						return (pointDistance(rootPoint, a)-pointDistance(rootPoint, b));				
					else  return temp; 
				}

			});		
			convexHull.push(Points.get(0));
			convexHull.push(Points.get(1));
			convexHull.push(Points.get(2));
			
			for(int i=3;i<numOfpoints;i++){
				
				Point top = convexHull.pop();
				
				while(checkAngle(convexHull.peek(), top, Points.get(i))!=-1){
					
					top = convexHull.pop();
				}
				convexHull.push(top);
				convexHull.push(Points.get(i));
			}		
			
			return convexHull;
		}
		
		public static void main(String[] args){
			
			ArrayList<Point> points = new ArrayList<Point>();
			
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
			
			Stack<Point> convexHull = new Stack<Point>();
			
			convexHull=getConvexHull(points);
			
			while(!convexHull.isEmpty()){
				System.out.println(convexHull.peek().x+" "+convexHull.peek().y);
				convexHull.pop();
			}
		}

}


