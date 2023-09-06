package com.yaga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите номер вопроса (1, 2, 3 и т.д.) или введите 'выход' для завершения:");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("выход")) {
                System.out.println("Программа завершена.");
                break;
            }

            try {
                int questionNumber = Integer.parseInt(choice);
                String question = getQuestion(questionNumber);
                if (question == null) {
                    System.out.println("Вопрос с таким номером не найден.");
                    continue;
                }

                System.out.println("Ваш вопрос: " + question);
                System.out.println("Хотите увидеть ответ? (да/нет):");
                String answerChoice = scanner.nextLine();

                if (answerChoice.equalsIgnoreCase("да")) {
                    String answer = getAnswer(questionNumber);
                    if (answer == null) {
                        System.out.println("Ответ на вопрос не найден.");
                    } else {
                        answer = answer.replaceAll("\\.", ".\n");
                        answer = answer.replaceAll("\\:", ":\n");
                        answer = answer.replaceAll("\\{", "{\n");
                        answer = answer.replaceAll("\\}", "\n}");
                        System.out.println("Ответ: ");
                        System.out.println(answer);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите правильный номер вопроса или 'выход' для завершения.");
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
