package �����;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Weekly {
	Time today;
	String mon,tue,wen,thu,fri,sat,sun;
	JLabel mon_name,tue_name,wen_name,thu_name,fri_name,sat_name,sun_name,title,memolabel;
	static JTextArea mon_text,tue_text,wen_text,thu_text,fri_text,sat_text,sun_text;
	Memo memo;
	JButton plus,addmemo,deletememo,editmemo;
	String strToday;
	JPanel weeklymemo; 
	
	public Weekly() {
	}
	
	public JPanel weeklytab() {
		JPanel weekly = new JPanel();
		weekly.setLayout(new BorderLayout());
		
		JPanel weeklyCal = new JPanel();
		weeklyCal.setLayout(new BorderLayout());
		
		today = new Time();
		strToday=today.Today();
		
		String week[]=weekCalendar(strToday);
		
		JPanel weekday = new JPanel();
		weekday.setLayout(new GridLayout(1,7));
		mon=week[0]; mon_name = new JLabel("  "+mon+"������"); weekday.add(mon_name);
		tue=week[1]; tue_name = new JLabel("  "+tue+"ȭ����"); weekday.add(tue_name);
		wen=week[2]; wen_name = new JLabel("  "+wen+"������"); weekday.add(wen_name);
		thu=week[3]; thu_name = new JLabel("  "+thu+"�����"); weekday.add(thu_name);
		fri=week[4]; fri_name = new JLabel("  "+fri+"�ݿ���"); weekday.add(fri_name);
		sat=week[5]; sat_name = new JLabel("  "+sat+"�����"); weekday.add(sat_name);
		sun=week[6]; sun_name = new JLabel("  "+sun+"�Ͽ���"); weekday.add(sun_name);
		weeklyCal.add(weekday,BorderLayout.NORTH);
		
		JPanel weektext = new JPanel();
		weektext.setLayout(new GridLayout(1,7));
		mon_text= new JTextArea(30,20); mon_text.setLineWrap(true);
		mon_text.setBorder(BorderFactory.createLineBorder(Color.black));
		weektext.add(mon_text);
		tue_text= new JTextArea(30,20); tue_text.setLineWrap(true);
		tue_text.setBorder(BorderFactory.createLineBorder(Color.black));
		weektext.add(tue_text);
		wen_text= new JTextArea(30,20); wen_text.setLineWrap(true);
		wen_text.setBorder(BorderFactory.createLineBorder(Color.black));
		weektext.add(wen_text);
		thu_text= new JTextArea(30,20); thu_text.setLineWrap(true);
		thu_text.setBorder(BorderFactory.createLineBorder(Color.black));
		weektext.add(thu_text);
		fri_text= new JTextArea(30,20); fri_text.setLineWrap(true);
		fri_text.setBorder(BorderFactory.createLineBorder(Color.black));
		weektext.add(fri_text);
		sat_text= new JTextArea(30,20); sat_text.setLineWrap(true);
		sat_text.setBorder(BorderFactory.createLineBorder(Color.black));
		weektext.add(sat_text);
		sun_text= new JTextArea(30,20); sun_text.setLineWrap(true);
		sun_text.setBorder(BorderFactory.createLineBorder(Color.black));
		weektext.add(sun_text);
		weeklyCal.add(weektext,BorderLayout.CENTER);
		
		JPanel weeklytitle = new JPanel();
		weeklytitle.setLayout(new BorderLayout());
		title = new JLabel("Weekly("+mon+"~"+sun+")");
		title.setHorizontalAlignment(JLabel.CENTER);

		
		//PlusŬ���� �޼��� �������̵� -> �̺�Ʈ ����
		weeklytitle.add(title, BorderLayout.CENTER);
		weekly.add(weeklytitle,BorderLayout.NORTH);

		memo = new Memo();
		weeklymemo = new JPanel();
		weeklymemo = memo.memo(); //Memo()�� memo()�� ������
		
		JPanel mainact = new JPanel();
		mainact.setLayout(new GridLayout(2,1));
		mainact.add(weeklyCal); mainact.add(weeklymemo);
		weekly.add(mainact,BorderLayout.CENTER);
		
		return weekly;
	}
	
	public String[] weekCalendar(String yyyymmdd){
		  
		  Calendar cal = Calendar.getInstance();
		  int toYear = 0;
		  int toMonth = 0;
		  int toDay = 0;
		  if(yyyymmdd == null || yyyymmdd.equals("")){   //�Ķ��Ÿ���� ������� ���ó�¥
		   toYear = cal.get(Calendar.YEAR); 
		   toMonth = cal.get(Calendar.MONTH)+1;
		   toDay = cal.get(Calendar.DAY_OF_MONTH);
		   
		   int yoil = cal.get(Calendar.DAY_OF_WEEK); //���ϳ������ϱ�(���ڷ�)

		   if(yoil != 1){   //�ش������ �Ͽ����� �ƴѰ��
		    yoil = yoil-2;
		   }else{           //�ش������ �Ͽ����ΰ��
		    yoil = 7;
		   }
		   cal.set(toYear, toMonth-1, toDay-yoil);  //�ش��ֿ����Ϸ� ����
		  }else{
		   int yy =Integer.parseInt(yyyymmdd.substring(0, 4));
		   int mm =Integer.parseInt(yyyymmdd.substring(4, 6))-1;
		   int dd =Integer.parseInt(yyyymmdd.substring(6, 8));
		   cal.set(yy, mm,dd);
		  }
		  String[] arrYMD = new String[7];
		  
		  int inYear = cal.get(Calendar.YEAR); 
		  int inMonth = cal.get(Calendar.MONTH);
		  int inDay = cal.get(Calendar.DAY_OF_MONTH);
		  int yoil = cal.get(Calendar.DAY_OF_WEEK); //���ϳ������ϱ�(���ڷ�)
		  if(yoil != 1){   //�ش������ �Ͽ����� �ƴѰ��
		      yoil = yoil-2;
		   }else{           //�ش������ �Ͽ����ΰ��
		      yoil = 7;
		   }
		  inDay = inDay-yoil;
		  for(int i = 0; i < 7;i++){
		   cal.set(inYear, inMonth, inDay+i);  //
		   String y = Integer.toString(cal.get(Calendar.YEAR)); 
		   String m = Integer.toString(cal.get(Calendar.MONTH)+1);
		   String d = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		   if(m.length() == 1) m = "0" + m;
		   if(d.length() == 1) d = "0" + d;
		   
		   arrYMD[i] =m+d;
		  }
		  
		  return arrYMD;
		 }
	}


