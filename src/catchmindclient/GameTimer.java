package catchmindclient;

import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class GameTimer extends TimerTask{
	int i;
	JLabel jlTimer;
	GameTimer(JLabel jlTimer){
		i=150;// ������� �����ֽ� �ð����� ����
		this.jlTimer=jlTimer;
		Font f = new Font("����",Font.BOLD,25);
		jlTimer.setFont(f);
		
	}
	@Override
	public void run() {
		this.i--;
		jlTimer.setText((i/60)+" : "+(i%60));
		if (i==0){
			cancel();
		}
		
	}



	
	public static void main(String[] args) {
		Timer time = new Timer();
		GameTimer timerTask = new GameTimer(null);

//		time.schedule(timerTask,0 ,1000);
	}

}
