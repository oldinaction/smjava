package theThirdChapter;

/*
定义一个“点”Point类用来表示空间的一个点，要求如下：
1. 可以生成具有特定坐标的点对象
2. 提供可以设置三个坐标的方法
3. 提供可以计算该点到原点距离平方的方法
4.编写程序验证上述三条
*/


class Point {
	double x, y, z;
	
	//生成具有特定坐标的点对象
	Point(double _x, double _y, double _z){
		x = _x;
		y = _y;
		z = _z;
	}
	
	//设置三个坐标的方法
	void setX(double _x){
		x = _x; //此时的_x与上面的_x不为同一个	
	}
	
	void setY(double _y){
		y = _y;	
	}
	
	void setZ(double _z){
		z = _z;	
	}
	
	//计算该点到p点距离平方的方法
	double getDistance(Point p){
		return (x - p.x)*(x - p.x) + (y - p.y)*(y - p.y) + (z - p.z)*(z - p.z);
	}
}


