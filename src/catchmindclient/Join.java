package catchmindclient;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

//회원가입하는 window
//id,pw,pw확인,캐릭터선택
//가입버튼,종료버튼

public class Join extends JFrame implements MouseListener{
	final static int NUM_CHAR=6;					//선택가능한 캐릭터수
	JLabel[] character=new JLabel[NUM_CHAR];		//캐릭터 선택 레이블 배열
	ImageIcon[] imgChar = new ImageIcon[NUM_CHAR];	//캐릭터 이미지 배열
	String[] charName = {"추욱이","새드","프랑켄슈타인2세","설리번","맹이","붕붕이"};//캐릭터이름배열	
	JLabel jlJoinTitle, jlId, jlPw, jlPwConfirm, jlChar, jlCharacterName,jlJoinLabelId,jlJoinLabelPw,jlJoinLabelPwConfirm,jlJoinLabelChar,jlJoinLabelCharacterName;	//회원가입 기입항목 레이블
	JTextField jtfId, jtfCharacterName;			//회원가입 기입항목 입력 텍스트필드
	JPasswordField jtfPw, jtfPwConfirm;
	JLabel jlJoin, jlExit;							//가입/나가기 레이블
	int characterType = -1;                     //캐릭터의 인덱스 값
	JPanel jpBackground;
	JScrollPane jspBackground;
	ImageIcon bgColor,iJoinLabel,iJlExit,iJlJoin,iTitle;
	
	Join(){
		//window의 위치와 사이즈(임의설정)
		setBounds(500,300,500,800);
		
		//기본 레이아웃 삭제
		setLayout(null);
		
		//x버튼 누르면 종료
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//component instance
		//타이틀과 폰트설정
		iTitle = new ImageIcon("src/catchmindimg/join.png");
		Font f = new Font("나눔바른펜",Font.BOLD,30);
		jlJoinTitle = new JLabel(iTitle);
		jlJoinTitle.setFont(f);
		
		//배경
		iJoinLabel = new ImageIcon("src/catchmindimg/joinLable.png");
		iJlExit = new ImageIcon("src/catchmindimg/jlExit.png");
		iJlJoin = new ImageIcon("src/catchmindimg/jlJoin.png");
		bgColor = new ImageIcon("src/catchmindimg/bjColor.jpg");
		jpBackground = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				g.drawImage(bgColor.getImage(), 0, 0, null);
				setOpaque(false);
			}
		};
		
		jpBackground.setLayout(null);
		
		//아이디
		jlId = new JLabel("       아이디");
		jlJoinLabelId = new JLabel(iJoinLabel);
		jtfId = new JTextField();
		
		//비밀번호와 확인
		jlPw = new JLabel("     비밀번호");
		jlJoinLabelPw = new JLabel(iJoinLabel);
		jtfPw = new JPasswordField();
		jlPwConfirm = new JLabel("비밀번호 확인");
		jlJoinLabelPwConfirm = new JLabel(iJoinLabel);
		jtfPwConfirm = new JPasswordField();
		
		
		
		
		//캐릭터
		jlChar = new JLabel("  캐릭터 선택");
		jlJoinLabelChar = new JLabel(iJoinLabel);
		for(int i =0; i<character.length;i++){
			imgChar[i] = new ImageIcon("src/catchmindimg/halfrobot" + i + ".PNG");
			character[i] = new JLabel(charName[i]);
			character[i].setIcon(imgChar[i]);
			
		}
		jlCharacterName = new JLabel("   캐릭터명");
		jlJoinLabelCharacterName = new JLabel(iJoinLabel);
		jtfCharacterName = new JTextField();
		
		//가입과 취소버튼	
		jlJoin = new JLabel(iJlJoin);
		jlExit = new JLabel(iJlExit);
		
		
		//component의 위치와 사이즈 조절
		int x=100, y=200;
		jlJoinTitle.setBounds(150,50,200,100);
		jlId.setBounds(x,y,100,30);
		jlPw.setBounds(x,y+50,100,30);
		jlPwConfirm.setBounds(x,y+100,100,30);
		jlChar.setBounds(x,y+150,100,30);
		
		//Label배경 위치
		jlJoinLabelId.setBounds(x-10,y,100,30);
		jlJoinLabelPw.setBounds(x-10,y+50,100,30);
		jlJoinLabelPwConfirm.setBounds(x-10,y+100,100,30);
		jlJoinLabelChar.setBounds(x-10,y+150,100,30);
		
		
		
		jtfId.setBounds(x+150, y, 150, 30);
		jtfPw.setBounds(x+150, y+50, 150, 30);
		jtfPwConfirm.setBounds(x+150, y+100, 150, 30);
		
		for(int i=0; i<character.length/3;i++)
			for(int j=0; j<3;j++)
				character[3*i+j].setBounds(x+(76*j)+70, y+(88*i)+180, 76, 88);
		jlJoin.setBounds(x, 650, 100, 40);
		jlExit.setBounds(x+150, 650, 100, 40);
		jlCharacterName.setBounds(x, y+380, 150, 30);
		jlJoinLabelCharacterName.setBounds(x-40, y+380, 150, 30);
		jtfCharacterName.setBounds(x+150, y +380, 150, 30);
		
		
		//character image들어간 레이블 배경색을 쓸수있도록
		for(int i =0; i<NUM_CHAR; i++)
			character[i].setOpaque(true);
			
			
		//window에 추가
		jpBackground.add(jlJoinTitle);
		jpBackground.add(jlId);
		jpBackground.add(jlPw);
		jpBackground.add(jlPwConfirm);
		jpBackground.add(jlChar);
		
		
		
		
		jpBackground.add(jtfId);
		jpBackground.add(jtfPw);
		jpBackground.add(jtfPwConfirm);
		for(int i=0; i<character.length;i++){
			character[i].setBackground(Color.white);
			jpBackground.add(character[i]);
		}
		jpBackground.add(jlCharacterName);
		jpBackground.add(jtfCharacterName);
		jpBackground.add(jlJoin);
		jpBackground.add(jlExit);
				
		//레이블배경
		jpBackground.add(jlJoinLabelId);
		jpBackground.add(jlJoinLabelPw);
		jpBackground.add(jlJoinLabelPwConfirm);
		jpBackground.add(jlJoinLabelChar);
		jpBackground.add(jlJoinLabelCharacterName);
		
		//리스너 부착
		for(int i=0; i<NUM_CHAR; i++)				//캐릭터에 리스너 부착
			character[i].addMouseListener(this);
		jlJoin.addMouseListener(this);				//회원가입
		jlExit.addMouseListener(this);				//취소
		//클릭하면 테두리쳐지게(구현해야함)
		
		//jscroll
		jspBackground = new JScrollPane(jpBackground); 
		setContentPane(jspBackground);
		
		//프레임 기본기능
		setResizable(false);	//사이즈조절안되게
		setVisible(true);		//보이게
	}//constructor end
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}//mouseClicked method end


	@Override
	public void mousePressed(MouseEvent e) {
		JLabel obj = (JLabel)e.getSource();
		
		boolean judge = true;
		
		if(obj.equals(jlJoin)){
			//가입클릭 ->서버로 정보를 보냄
			
			//안에 내용이 없으면 alert 메시지를 띄움.
			if(jtfId.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "아이디를 입력해주세요");
			}
			else if(String.valueOf(jtfPw.getPassword()).isEmpty()){
				JOptionPane.showMessageDialog(this, "패스워드를 입력해주세요");
			}
			else if(String.valueOf(jtfPwConfirm.getPassword()).isEmpty()){
				JOptionPane.showMessageDialog(this, "패스워드 확인값을 입력해주세요");
			}
			else if(!String.valueOf(jtfPw.getPassword()).equals(String.valueOf(jtfPwConfirm.getPassword()))){
				JOptionPane.showMessageDialog(this, "패스워드가 다릅니다.");
				jtfPw.setText("");
				jtfPwConfirm.setText("");
			}
			else if(characterType < 0){
				JOptionPane.showMessageDialog(this, "캐릭터를 선택해주세요");
			}
			else if(jtfCharacterName.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "닉네임을 입력해주세요");
				System.out.println("선택된캐릭터:" + characterType);
			}
			else{
				ClientMain.getInstance().getClientSession().sendMemberCreate(jtfId.getText(), String.valueOf(jtfPw.getPassword()), characterType, jtfCharacterName.getText());
				//가입진행
				System.out.println("가입하기");
			}
			
			
		}else if(obj.equals(jlExit)){
			//취소클릭
			int select = JOptionPane.showConfirmDialog(this, "가입을 취소하시겠습니까?", "확인", 0);
			System.out.println(select);
			if(select==0){
				this.setVisible(false);
				ClientMain cm = ClientMain.getInstance();
				cm.getLoginWindow().setVisible(true);
			}
		}else{
			//캐릭터의 인덱스값을 각 캐릭터레이블배열에 대입해줌.
			if(obj.equals(character[0])) characterType = 0;
			else if(obj.equals(character[1])) characterType = 1;
			else if(obj.equals(character[2])) characterType = 2;
			else if(obj.equals(character[3])) characterType = 3;
			else if(obj.equals(character[4])) characterType = 4;
			else if(obj.equals(character[5])) characterType = 5;
								
			obj.setBackground(Color.DARK_GRAY);
						
			//캐릭터명에 디폴트 이름 세팅하기
			jtfCharacterName.setText(obj.getText());
			
			//선택되지 않은 캐릭터 배경색 없애기
			for(int i=0; i<NUM_CHAR; i++)
			{
				if(character[i] != obj)
					character[i].setBackground(Color.white);
			}
		}
		
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}


	public static void main(String[] args) {
		new Join();
	}//main method end
}
