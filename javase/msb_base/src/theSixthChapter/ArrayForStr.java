package theSixthChapter;
//把字符串"1, 2; 3, 4, 5; 6, 7, 8"用分隔符分开，再放到一个二维的double型数组中
public class ArrayForStr {
	public static void main(String[] args){
		double[][] d;
		String s = "1, 2; 3, 4, 5; 6, 7, 8";
		String[] sFirst = s.split(";");  //用;分出第一维
		d = new double[sFirst.length][]; //将第一维的长度赋给double型数组的第一维，此时double后面有两个中括号
		
		for(int i=0; i<sFirst.length; i++){
			String[] sSecond = sFirst[i].split(",");  //用,分出第二维
		    d[i] = new double[sSecond.length]; //将第二维的长度赋给double型数组的第二维，此时double后面只有一个中括号
			for(int j=0; j<sSecond.length; j++){
				d[i][j] = Double.parseDouble(sSecond[j]); // 返回一个新的 double 值，该值被初始化为用指定 String 表示的值，这与 Double 类的 valueOf 方法一样。
			}
		}
		
		for(int i=0; i<d.length; i++){
			for(int j=0; j<d[i].length; j++){
				System.out.println(d[i][j] + " ");
			}
			System.out.println();
		}
		
	}

}
