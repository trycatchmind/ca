package catchmindclient;

import java.util.HashSet;

public class Word {
	boolean correct;
	HashSet<String> word = new HashSet<String>();
	String [] strWord = { "���೪��", "������ȭ", "�ֵ���", "����", "�ϰ��Ź�", "�ﱹ�ô�", "ī���̼�", "�ڳ�", "������", "�Գ�", "�ݰ�", "����", "���̽�",
			"��찳", "����", "���ڼ�", "�ؿ��", "�ǻ�ü", "�ΰ���", "�˰���", "����", "��Ʋ��", "����", "�����", "����", "�Ŀ����", "������", "����",
			"�δ��", "������", "���", "�ӼӸ�", "Ÿ�Ӹӽ�", "�ſ�ī��", "�尩��", "�ָԹ�", "������", "�ڳ�ű", "��Ű", "Ʈ����", "���ٸ�", "���α�",
			"����ڶ�", "���۸�", "Ÿ�̰ſ���", "������", "��������", "�ϸ�", "�ֵ���", "����", "�汸��", "�����", "�ַθ�", "����", "�Ǹ�", "������", "¥�ӻ�",
			"���ýô�", "�����", "��ũ����", "Ű����", "���", "�Ͻ�", "�̹���", "��̴��Ӵ�", "�¾�������", "������", "���ұ�", "�����", "�ڰ���", "�̾�޸���",
			"���ٶ���", "��̰�", "���", "������", "�������", "��ȣ��", "����", "��ó���", "��������", "īī������", "���Ÿ�̰���", "�����", "����ǥ", "���μ�",
			"������ȭ", "���ȣ", "���", "���", "��Ǫ", "����", "����", "�ʵ��б�", "Ƽ��", "��õ��", "�뷡", "â����", "�ڳ�", "����", "�Ը���", "�ҹ��",
			"��ũ����", "������", "ũ���׳�", "����", "�����̵�ġŲ", "ĥ�����", "â����", "�ϰ��Ź�", "���ο�", "�������", "���ٹ�", "����", "��Ʈ��ũ",
			"�����ڵ���", "������", "��󷣵�", "�¾����Ŀ�", "���׶�", "���ٹ�", "�����", "��", "�ǵ����ǻ���", "���", "������", "����������", "�������̻߻�",
			"�ŵ�����", "�Ƴ�����Ȥ", "������" };
	String output;
	int myCorrectCount =0;
	int otherCorrectCount =0;
//	GameClient game = new GameClient(); // ���� Ŭ���̾�Ʈ �ν��Ͻ�
	GameChat chat;
	
	public Word(){
		correct = false; // ������ true/fase�� ����
		output = null;
		// HashSet�� for������ ������ add�ϱ�
		wordReset();
	}
	
	public void wordReset(){
		for(int i =0; i<strWord.length; i++){
			word.add(strWord[i]);
		}
	}
		
		public void wordOutput(){
			// if(������ �����ϴ� ī��Ʈ�� �����ų� Ÿ�̸Ӱ� �۵��ϸ�)
				output = strWord[(int)(Math.random()*strWord.length)];
						//0<Math.random()<1*strWord.length
				//else if (������ �������� ������) output = null;
				ClientMain.getInstance().getGameWindow().jlWord.setText(output);
				//���� Ŭ���̾�Ʈ ���巹�̺� �������� ������ �ܾ ����ִ´�.
		
		}
		/*
		public boolean correctMethod(int CharacterNumber){
			
			
				if (ClientMain.getInstance().getGameWindow().jlWord.getText()
						) {
						correct = true;
						
				}else{
						correct= false;
				}
				
			
			return correct;
		}*/
	
	

}
