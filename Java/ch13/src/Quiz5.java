import java.util.Scanner;
public class Quiz5 {

		public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("단어를 입력해주세요");
		String word = s.next();

		String[] array_word = new String[word.length()]; // 스트링을 담을 배열

		for(int i=0;i<array_word.length;i++){ //스트링을 한글자씩 끊어 배열에 저장
			array_word[i] = Character.toString(word.charAt(i));
		}
		int leng = array_word.length;
		int n = 1;
		int count =0;
		for (int i=0;i<leng;i++) {
			if(array_word[i].equals(array_word[leng-n])) { 
				//ex)배열은 0~4, length는 5로 인해 -1를 시작으로 1개씩 깍아가면서 비교 
				System.out.println(array_word[i]+" / "+array_word[leng-n]);
				n++;
				count = count+1;
			}else {
				System.out.println("회문이 아닙니다.");
				break;
			}
		}
		if(leng==count) {
			System.out.println("회문입니다.");
		}
	}
}