package theThirdChapter;

public class TestOverLoad {
	//根据传的参数来确定要调用的方法
	
	void max(int a, int b){
		System.out.println(a > b ? a : b);
	}
	
	void max(float a, float b){
		System.out.println(a > b ? a : b);
	}

}
