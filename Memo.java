package com.ljw.memo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * Created by leeje on 2018-04-16.
 */
public class Memo {
    public final static String MEMO_DIR = "/Temp/memo/";
    public final static String EXIT = "/exit";

    public Memo() {
        File file = new File(MEMO_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public void showCommand() {
        System.out.println("1.쓰기 2.읽기 3.수정 4.삭제 0.종료");
        System.out.print("명령번호를 입력하세요 : ");
    }

    public void write(Scanner scan) {
        System.out.println("...쓰는중");

        String content = "";
        while (true) {
            String line = scan.nextLine();
            if (line.equals(EXIT))
                break;
            else
                content += line + "\r\n";
        }

        if (!content.equals("")) {
            long now = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
            String name = sdf.format(now);

            Path path = Paths.get(MEMO_DIR, name + ".txt");
            try {
                Files.write(path, content.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("메모를 등록하였습니다");
        } else
            System.out.println("내용이 입력되지 않았습니다");
    }

    public String read(Scanner scan) {
        String result = "";

        list();
        System.out.print("파일명을 확장자까지 입력하세요 : ");      //
        String name = scan.nextLine();                              // 파일명 전체를 입력받아, 읽어올 파일 경로를 선택함
        Path path = Paths.get(MEMO_DIR + name);                     //
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                result += line + "\r\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void list() {
        File file = new File(MEMO_DIR);
        String list[] = file.list();
        for (String name : list) {
            System.out.println(name);
        }
    }

    public void modify(Scanner scan) {
        list();
        System.out.print("파일명을 확장자까지 입력하세요 : ");
        String name = scan.nextLine();
        Path path = Paths.get(MEMO_DIR + name);
        System.out.println("...수정중");

        try {
            List<String> lines = Files.readAllLines(path);
            String content = "";                                                   // 전체 내용을 담을 String

            for (String line : lines) {
                System.out.println(line);
                System.out.print("해당 문장의 수정을 원하십니까?(y or anything) : ");
                String want = scan.nextLine();
                if (want.equals("y")) {
                    System.out.print("수정을 원하는 텍스트를 입력하세요 : ");
                    String original = scan.nextLine();
                    System.out.print("새로 작성될 텍스트를 입력하세요 : ");
                    String replace = scan.nextLine();

                    content += line.replaceAll(original, replace) + "\r\n";                 // String 교체 후 content에 복사
                } else {
                    content += line + "\r\n";                                               // 교체 않고, 그대로 복사
                    System.out.println("다음 문장을 출력합니다");
                }
            }

            try {
                Files.write(path, content.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("메모를 수정하였습니다");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(Scanner scan) {
        list();
        System.out.print("파일명을 확장자까지 입력하세요 : ");
        String name = scan.nextLine();
        File file = new File(MEMO_DIR + name);
        file.delete();
        System.out.println("메모를 삭제하였습니다");
    }
}
