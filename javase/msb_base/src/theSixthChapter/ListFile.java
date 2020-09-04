package theSixthChapter;

import java.io.File;

public class ListFile {
    public static void main(String[] args) {
        File f = new File("h:/demo");
        System.out.println(f.getName());
        tree(f, 1);
    }

    //level���������Ŀ¼�Ĳ�������Ҫ�����ĸ���
    private static void tree(File f, int level) {
        String preString = "";
        for (int i = 0; i < level; i++) {
            preString += "    ";
        }  //��Ϊ�ǵݹ飬����ÿ�ε���tree,��ôlevel�ͷ����˸ı䣬��ʱpreString�õ�����ѭ�����˵�һ��ֵ������level��Ҫ�����ĸ���

        File[] childFile = f.listFiles();  //listFiles()����һ������·�������飬��Щ·������ʾ�˳���·������ʾ��Ŀ¼�е��ļ�����ָ����������Ŀ¼
        for (int i = 0; i < childFile.length; i++) {
            System.out.println(preString + childFile[i].getName());  //getName()�����ɴ˳���·������ʾ���ļ���Ŀ¼�����ơ�
            if (childFile[i].isDirectory()) {
                tree(childFile[i], level + 1); //�ݹ�����ã���һ������������ô˺�����
            }  //isDirectory()���Դ˳���·������ʾ���ļ��Ƿ���һ��Ŀ¼��
        }
    }

}
