package theFifthChapter;

//有500个人站成一圈，依次数数（1,2,3），数到3的那个人退出，后面接着数，求最后一个人原来站的位置
public class Count3Quite {
	public static void main(String[] args){
		boolean[] arr = new boolean[500];
		for(int i=0; i<arr.length; i++){
			//先令所有的人都在圈内（true)
			arr[i] = true;
		}
		//剩下的人数leftCount
		int leftCount = arr.length;
		//计数器countNum
		int countNum = 0; 
		//地址从0开始
		int index = 0; 
		//如果剩下的人数大于1，那么一直计数下去
		while(leftCount > 1){
			//运算地址为index这个人
			if(arr[index] ==true){
				countNum++; //当此人在圈内时，计数器加1
				if(countNum == 3){
					countNum = 0; //当计到3时就归零
					arr[index] = false; //此人退出圈内（false）,剩下的人减1
					leftCount--;
				}
			}
			//再执行下一个人
			index++;
			//如果运算的那个人是最后一个，即运算了一圈，然后将此地址归零
			//arr.length永远不变且为500，退出的那个人只是赋值为false，在判断过程中还是判断了原来退出的人
			if(index == arr.length){
				index = 0;
			}
		}
		
		for(int i=0; i<arr.length; i++){
			if(arr[i] == true){
				System.out.println("最后一个人原来的位置是"+(i+1));
			}
		}
	
	}	
}
