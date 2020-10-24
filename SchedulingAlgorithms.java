package fcfs;
import java.util.*;


 

class sjf1 {
	 double AT,BT,CT,RT,WT,TT;
	 int id1,f;
	 
	 sjf1(double at,double bt,int id,int f)
	 {
		 AT=at;
		 BT=bt;
		 id1=id;
		 f=f;
		
	 }
	
}

class sjf{
	Scanner sc= new Scanner(System.in);
	Queue q=new LinkedList();
	 int g[]=new int[20];
	 
	
	void acceptsjf()
	{
		int z=0;
		int pid,done = 0,st=0;
		double WT, TT, AWT, ATT,at,bt;
		int id,f;
		System.out.println("-------------SJF----------------");
		System.out.println("Enter Total Process: ");
		pid=sc.nextInt();
		sjf1 p2[]=new sjf1[pid];
		for(int j=0;j<pid;j++)
		{
			System.out.println("For process "+ (j+1));
			System.out.println("Enter Arrival time:");
			at=sc.nextDouble();
			System.out.println("Enter Burst time:");
			bt=sc.nextDouble();
			id=j+1;
			f=0;
		    p2[j]=new sjf1(at,bt,id,f);
		}
		
		boolean a= true;
		while(true)
		{
			double min=999;
			int c=pid;
			if(done==pid)
			{
				break;
			}
			for(int j=0;j<pid;j++)
			{
				if((p2[j].AT)<=st && (p2[j].f==0) && (p2[j].BT<min ))
                    {
	                  min=p2[j].BT;
	                  c=j;
                    }
			}
			
			if(c==pid)
			{
				st++;
			}
			else
			{
				p2[c].CT=st+p2[c].BT;
				st+=p2[c].BT;
				p2[c].TT=p2[c].CT-p2[c].AT;
				p2[c].WT=p2[c].TT-p2[c].BT;
				p2[c].f=1;
			
				//System.out.println(p2[c].id);
				g[z]=p2[c].id1;
				z++;
				done++;
				
			}
		}
		
		WT=0;
		TT=0;
		for(int l=0;l<pid;l++)
		{
			WT+=p2[l].WT;
			TT=TT+p2[l].TT;
		}
		AWT=WT/pid;
		ATT=TT/pid;
		System.out.println("--------------------------------------------------------------------");
		 System.out.println("ID\t"+"AT\t"+"BT\t"+"CT\t"+"TT\t"+"WT");
		 System.out.println("--------------------------------------------------------------------");
			for(int j=0;j<pid;j++)
			{
				
				System.out.println(j+"\t"+p2[j].AT+"\t"+p2[j].BT+"\t"+p2[j].CT+"\t"+p2[j].TT+"\t"+p2[j].WT);
			}
			System.out.println("\n--------------------------------------------------------------------");
			System.out.println("Average wating time is "+AWT);
			System.out.println("Average turnaround time is "+ATT);

			System.out.println("\nGANTT DIAGRAM:");
			for(int j=0;j<z;j++)
			{
				System.out.print("P"+g[j]+"| ");
			}
	}
	
	
}

class srtf1{
	double AT,BT,CT,TT,WT;
	int id1;
	
	srtf1(double at,double bt,int id1)
	{
		AT=at;
		BT=bt;
		id1=id1;
	}
	
}

class srtf
{
	Scanner sc= new Scanner(System.in);
	Queue q=new LinkedList();
	
	public void acceptsrtf()
	{
		System.out.println("\n-------------SRTF----------------");
		int pid=0,id,done=0,f=0;
		int flag=0,shortprocess=0;
		double at,bt,t=0,done_time=0;
		double AWT,WT,TT,ATT,min=32000;
		int z=0,z1=0;
		int g[]=new int[90];
		int g1[]=new int[90];
		
		System.out.println("Enter Total processes:");
		pid=sc.nextInt();
		srtf1 p3[] =new srtf1[pid];
		double rt[]=new double[pid];
		for(int j=0;j<pid;j++) 
		{
		System.out.println("Enter Arrival Time:");
		at=sc.nextDouble();
		System.out.println("Enter Burst Time:");
		bt=sc.nextDouble();
		id=j+1;
		p3[j]=new srtf1(at,bt,id);
		}
		for(int k=0;k<pid;k++)
		{
			rt[k]=p3[k].BT;
		}
					
		while(done!=pid)
		{
			 for( f=0;f<pid;f++)
			 {
				if((p3[f].AT<=t)&&(rt[f]<min)&&rt[f]>0)
                    {
                    	min=rt[f];
                    	shortprocess=f;
                    	flag=1;
                    	
                    	
	
                    }
				 
			 }
			 
			 if(flag==0)
			 {
				 t++;
				 continue;
			 }
			 
			 rt[shortprocess]--;
			 
		     if(z1!=0 && g1[z1-1]!=shortprocess+1)
		     {
			 g1[z1]=shortprocess+1;
			 z1++;
		     }
		     else if(z1==0)
		     {
		    	 g1[z1]=shortprocess+1;
				 z1++;
		     }
			 
			 min=rt[shortprocess];
			 
			 if(min==0)
			 {
				 min=32000;
				 
			 }
			 
			 if(rt[shortprocess]==0)
			 {
				 g[z]=shortprocess+1;
				 z++;
				 p3[shortprocess].CT=t+1;
				 done++;
				 flag=0;
				 
				 done_time=t+1;
				 p3[shortprocess].WT=done_time-p3[shortprocess].BT-p3[shortprocess].AT;
				 
				 if(p3[shortprocess].WT<0)
				 {
					 p3[shortprocess].WT=0;
				 }
				 
			 }
			 t++;
			 
			 for(int h=0;h<pid;h++)
			 {
				 p3[h].TT=p3[h].BT+p3[h].WT;
			 }
			 
		}
		

		WT=0;
		TT=0;
		for(int l=0;l<pid;l++)
		{
			WT+=p3[l].WT;
			TT=TT+p3[l].TT;
		}
		AWT=WT/pid;
		ATT=TT/pid;
		System.out.println("--------------------------------------------------------------------");
		 System.out.println("ID\t"+"AT\t"+"BT\t"+"CT\t"+"TT\t"+"WT");
		 System.out.println("--------------------------------------------------------------------");
			for(int j=0;j<pid;j++)
			{
				
				System.out.println(j+"\t"+p3[j].AT+"\t"+p3[j].BT+"\t"+p3[j].CT+"\t"+p3[j].TT+"\t"+p3[j].WT);
			}
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Average waiting time is "+AWT);
			System.out.println("Average turnaround time is "+ATT);
			System.out.println("--------------------------------------------------------------------");
			System.out.println("\nCOMPLETION ORDER:");
			for(int j=0;j<z;j++)
			{
				System.out.print("P"+g[j]+"| ");
			
			}
            System.out.println("");
			System.out.println("\nGANTT DIAGRAM:");
			for(int j=0;j<z1;j++)
			{
				System.out.print("P"+g1[j]+"| ");
			
			}
			
	
	
	}
	}
	
	

	
public class rr{

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
	
		int ch=0;
		sjf obj1= new sjf();
		srtf obj2=new srtf();
	
		do {
		System.out.println("\n-----------MENU-----------");
		System.out.println("\n1.SJF");
		System.out.println("2.SRTF");
		ch=sc.nextInt();
		switch(ch) {
		case 1:
	obj1.acceptsjf();
	break;
		case 2:
    obj2.acceptsrtf();
    break;
		}
		}while(ch!=0);
		

	}

}



/*
 * -----------MENU-----------

1.SJF
2.SRTF
1
-------------SJF----------------
Enter Total Process: 
4
For process 1
Enter Arrival time:
0
Enter Burst time:
6
For process 2
Enter Arrival time:
2
Enter Burst time:
2
For process 3
Enter Arrival time:
4
Enter Burst time:
5
For process 4
Enter Arrival time:
6
Enter Burst time:
3
--------------------------------------------------------------------
ID	AT	BT	CT	TT	WT
--------------------------------------------------------------------
0	0.0	6.0	6.0	6.0	0.0
1	2.0	2.0	8.0	6.0	4.0
2	4.0	5.0	16.0	12.0	7.0
3	6.0	3.0	11.0	5.0	2.0

--------------------------------------------------------------------
Average wating time is 3.25
Average turnaround time is 7.25

GANTT DIAGRAM:
P1| P2| P4| P3| 
-----------MENU-----------

1.SJF
2.SRTF
2

-------------SRTF----------------
Enter Total processes:
4
Enter Arrival Time:
0
Enter Burst Time:
6
Enter Arrival Time:
2
Enter Burst Time:
2
Enter Arrival Time:
4
Enter Burst Time:
5
Enter Arrival Time:
6
Enter Burst Time:
3
--------------------------------------------------------------------
ID	AT	BT	CT	TT	WT
--------------------------------------------------------------------
0	0.0	6.0	8.0	8.0	2.0
1	2.0	2.0	4.0	2.0	0.0
2	4.0	5.0	16.0	12.0	7.0
3	6.0	3.0	11.0	5.0	2.0
--------------------------------------------------------------------
Average waiting time is 2.75
Average turnaround time is 6.75
--------------------------------------------------------------------

COMPLETION ORDER:
P2| P1| P4| P3| 

GANTT DIAGRAM:
P1| P2| P1| P4| P3| 
*/
