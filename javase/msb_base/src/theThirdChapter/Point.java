package theThirdChapter;

/*
����һ�����㡱Point��������ʾ�ռ��һ���㣬Ҫ�����£�
1. �������ɾ����ض�����ĵ����
2. �ṩ����������������ķ���
3. �ṩ���Լ���õ㵽ԭ�����ƽ���ķ���
4.��д������֤��������
*/


class Point {
    double x, y, z;

    //���ɾ����ض�����ĵ����
    Point(double _x, double _y, double _z) {
        x = _x;
        y = _y;
        z = _z;
    }

    //������������ķ���
    void setX(double _x) {
        x = _x; //��ʱ��_x�������_x��Ϊͬһ��
    }

    void setY(double _y) {
        y = _y;
    }

    void setZ(double _z) {
        z = _z;
    }

    //����õ㵽p�����ƽ���ķ���
    double getDistance(Point p) {
        return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y) + (z - p.z) * (z - p.z);
    }
}


