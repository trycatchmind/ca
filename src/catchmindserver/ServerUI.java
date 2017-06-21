package catchmindserver;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// 서버 UI 클래스
public class ServerUI extends JFrame implements ActionListener {
	private static ServerUI instance;
	
	JTextArea jtaChat = new JTextArea();
	JScrollPane jsp = new JScrollPane(jtaChat);
	JButton jbtExit = new JButton("Exit");		 
	
	private ServerUI() {
		super("CatchMind 서버");
		setBounds(200, 100, 400, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		jtaChat.setFont(new Font("돋움체", Font.BOLD, 14));
		jbtExit.addActionListener(this);
		
		add(jsp, "Center");
		add(jbtExit, "South");				
		
		setVisible(true);
	}	

	@Override
	public void actionPerformed(ActionEvent arg) {
		Object cmd = arg.getSource();
		if (cmd.equals(jbtExit)) {
			System.exit(0);
		}		
	}
	
	// UI에 문자열 출력
	public void msgAppend(String str) {
		jtaChat.append(str + "\n");
	}
	
	// 싱글톤
	public synchronized static ServerUI getInstance(){
		if(instance == null){
			instance = new ServerUI();
		}
		return instance;
	}	
}
