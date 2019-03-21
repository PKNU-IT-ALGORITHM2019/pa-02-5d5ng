package pa2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pa2 {

	private double min=0;
	private int N=3; //���� ��
	private Point [] city=new Point[1];
	private int [] route;
	public static void main(String[] args) {

		long start=System.currentTimeMillis();
		Pa2 app=new Pa2();
		app.process_command();
		long end=System.currentTimeMillis();
		System.out.println("\n����ð�:"+(end-start)/1000.0+"��");

	}
	void process_command() {
		
		try {
			Scanner Fin=new Scanner (new File("input5.txt"));
			N=Fin.nextInt();
			city=new Point [N];
			route= new int[N];

			for(int i=0;i<N;i++) {
				city[i]=new Point(i,Fin.nextInt(),Fin.nextInt());
				route[i]=i;

			}


			Fin.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("No data file.");
			System.exit(1);
		}

		tourD(1,0); //0�� ���ô� ������Ű�� ����

		System.out.print("answer:\n"+min+"\n[");
		for(int i=0;i<N;i++) {
			if(i!=N-1)
				System.out.print(route[i]+",");
			else
				System.out.print(route[i]+"]");
		}

	}



	private void swap(int k, int i) {
		Point temp=city[k];
		city[k]=city[i];
		city[i]=temp;

		return;

	}

	public  double distance(int p1,int p2) { //�� ���û����� �Ÿ��� ���ϴ� �Լ�
		if(p1<0 || p2<0) return 0;
		double disx=city[p2].x-city[p1].x;
		double disy=city[p2].y-city[p1].y;

		return Math.sqrt(disx*disx+disy*disy);

	}


	private  void tourD(int k,double d) {

		if( min!=0 && d>min) {
			return;
		}
		if(k==N) {

			d+=distance(k-1,0);
			if(min==0)  //����ó�� ������ ��Ʈ
				min=d;



			if(min!=0 && min>d) { //��� ������ �� ����Ǿ����� �ּұ��̺��� �۴ٸ�
				for(int i=0;i<N;i++)
					route[i]=city[i].num;

				min=d;

			}

			return;

		}
		else {

			for(int i=k;i<N;i++) {
				swap(k,i);
				tourD(k+1,  d+distance(k-1,k) );
				swap(k,i);
			}
		}
	}
}



