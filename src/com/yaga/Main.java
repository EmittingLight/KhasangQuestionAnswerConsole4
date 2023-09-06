package com.yaga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("�������� ����� ������� (1, 2, 3 � �.�.) ��� ������� '�����' ��� ����������:");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("�����")) {
                System.out.println("��������� ���������.");
                break;
            }

            try {
                int questionNumber = Integer.parseInt(choice);
                String question = getQuestion(questionNumber);
                if (question == null) {
                    System.out.println("������ � ����� ������� �� ������.");
                    continue;
                }

                System.out.println("��� ������: " + question);
                System.out.println("������ ������� �����? (��/���):");
                String answerChoice = scanner.nextLine();

                if (answerChoice.equalsIgnoreCase("��")) {
                    String answer = getAnswer(questionNumber);
                    if (answer == null) {
                        System.out.println("����� �� ������ �� ������.");
                    } else {
                        answer = answer.replaceAll("\\.", ".\n");
                        answer = answer.replaceAll("\\:", ":\n");
                        answer = answer.replaceAll("\\{", "{\n");
                        answer = answer.replaceAll("\\}", "\n}");
                        System.out.println("�����: ");
                        System.out.println(answer);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("����������, ������� ���������� ����� ������� ��� '�����' ��� ����������.");
            }
        }
    }

    private static String getQuestion(int questionNumber) {
        return readLineFromFile("questions.txt", questionNumber);
    }

    private static String getAnswer(int questionNumber) {
        return readLineFromFile("answers.txt", questionNumber);
    }

    private static String readLineFromFile(String fileName, int lineNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int currentLine = 0;

            while ((line = reader.readLine()) != null) {
                currentLine++;
                if (currentLine == lineNumber) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
