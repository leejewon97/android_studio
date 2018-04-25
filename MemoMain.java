package com.ljw.memo;

import java.util.Scanner;

/**
 * Created by leeje on 2018-04-16.
 */
public class MemoMain {
    public static void main(String[] args) {
        Memo memo = new Memo();
        Scanner scan = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            memo.showCommand();
            String cmd = scan.nextLine();
            switch (cmd) {
                case "1":
                    memo.write(scan);
                    break;
                case "2":
                    System.out.println(memo.read(scan));
                    break;
                case "3":
                    memo.modify(scan);
                    break;
                case "4":
                    memo.delete(scan);
                    break;
                case "0":
                    flag = false;
                    System.out.println("프로그램이 종료되었습니다");
                    break;
                default:
                    System.out.println("적절한 번호를 입력하세요.");
                    break;
            }
        }

    }
}
