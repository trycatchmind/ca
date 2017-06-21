package catchmindclient;

import java.util.HashSet;

public class Word {
	boolean correct;
	HashSet<String> word = new HashSet<String>();
	String [] strWord = { "은행나무", "공중전화", "핫도그", "양팔", "일간신문", "삼국시대", "카레이서", "자녀", "새우젓", "게놈", "금고", "열매", "레이싱",
			"삽살개", "퇴학", "야자수", "해우소", "피사체", "두개골", "옹고집", "방콕", "셔틀콕", "수영", "브라우니", "가지", "파워숄더", "감자탕", "순댓국",
			"부대찌개", "전사자", "사수", "귓속말", "타임머신", "신용카드", "장갑차", "주먹밥", "조선소", "코너킥", "하키", "트럼프", "오바마", "개인기",
			"장기자랑", "슈퍼맨", "타이거우즈", "연장전", "위계질서", "니모", "주동자", "오존", "방구들", "양배추", "솔로몬", "강변", "피멍", "세차장", "짜임새",
			"원시시대", "엿장수", "포크레인", "키위새", "콩고물", "일식", "이미자", "쇼미더머니", "태양은없다", "족집게", "맛소금", "가요계", "자개상", "이어달리기",
			"가다랑어", "양미경", "백미", "오지명", "가라오케", "애호박", "조예", "사시나무", "오동나무", "카카오나무", "기아타이거즈", "우거지", "가격표", "가로수",
			"공중전화", "김경호", "백수", "산모", "샴푸", "린스", "진심", "초등학교", "티눈", "귀천도", "노래", "창조물", "자녀", "위기", "게릴라", "소방관",
			"포크레인", "무지개", "크라잉넛", "퇴학", "프라이드치킨", "칠종경기", "창조물", "일간신문", "유인원", "운전기사", "돈다발", "도시", "네트워크",
			"견인자동차", "연장전", "라라랜드", "태양의후예", "베테랑", "돈다발", "엿장수", "비서", "판도라의상자", "양반", "작은북", "새벽의저주", "말괄량이삐삐",
			"신데렐라", "아내의유혹", "나문희" };
	String output;
	int myCorrectCount =0;
	int otherCorrectCount =0;
//	GameClient game = new GameClient(); // 게임 클라이언트 인스턴스
	GameChat chat;
	
	public Word(){
		correct = false; // 정답을 true/fase로 리턴
		output = null;
		// HashSet을 for문으로 돌려서 add하기
		wordReset();
	}
	
	public void wordReset(){
		for(int i =0; i<strWord.length; i++){
			word.add(strWord[i]);
		}
	}
		
		public void wordOutput(){
			// if(게임이 시작하는 카운트가 들어오거나 타이머가 작동하면)
				output = strWord[(int)(Math.random()*strWord.length)];
						//0<Math.random()<1*strWord.length
				//else if (게임이 시작하지 않으면) output = null;
				ClientMain.getInstance().getGameWindow().jlWord.setText(output);
				//게임 클라이언트 워드레이블에 랜덤으로 배정한 단어를 집어넣는다.
		
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
