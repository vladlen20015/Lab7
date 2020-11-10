package com.company;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            File folder = new File("C:\\Users\\Ученик.DESKTOP-9UTQSM7\\IdeaProjects\\Lab_7_var4\\My");
            if (!folder.exists())
                folder.mkdir();

            File f1 = new File("C:\\Users\\Ученик.DESKTOP-9UTQSM7\\IdeaProjects\\Lab_7_var4\\My\\rec_RAF.txt");
            if (!f1.exists())
                f1.createNewFile();
            

            RandomAccessFile rf = new RandomAccessFile(f1,"rw"); // чтение и запись
            long fSize = rf.length(); // размер файла
            Scanner sc = new Scanner(System.in, "cp1251");
            System.out.print("Введите количество товара для записи в файл\n"
                    + "=> ");
            int kol = sc.nextInt();
            sc.nextLine(); // очистка буфера после ввода числа

            String naimenovanie, proizvoditel;
            int kolichestvo, cena;
//----ЗАПИСЬ ИНФОРМАЦИИ О СОТРУДНИКАХ В ФАЙЛ----
            for (int i = 0; i < kol; i++) {
                System.out.print("Введите наименование " + (i + 1) + " товара => ");
                naimenovanie = sc.next();
                rf.seek(rf.length()); // поиск конца файла
                rf.writeUTF(naimenovanie); // запись фамилии
                for (int j = 0; j < 20 - naimenovanie.length(); j++)
                    rf.writeByte(1); // добавление байтов до 20-ти любой цифрой (=1)
                System.out.print("Введите его производитель => ");
                proizvoditel = sc.next();
                rf.writeUTF(proizvoditel); // запись должности
                for (int j = 0; j < 20 - proizvoditel.length(); j++)
                    rf.writeByte(1); // добавление байтов до кол=20 любым числом
                System.out.print("Введите его количество => ");
                kolichestvo = sc.nextInt();
                sc.nextLine(); // очистка буфера
                rf.writeInt(kolichestvo); // запись оклада
                System.out.print("Введите его цену => ");
                cena = sc.nextInt();
                sc.nextLine(); // очистка буфера
                rf.writeInt(cena); // запись оклада
                for (int h= 0; h < kol; h++){
                    if (cena[h]>1000)
                        System.out.println(cena[h]);
                }

            }
            rf.close();
//----ЧТЕНИЕ ИНФОРМАЦИИ О СОТРУДНИКАХ ИЗ ФАЙЛА----
            rf = new RandomAccessFile(f1, "r");
            rf.seek(0); // перемещение в начало файла
            System.out.println("Информация о товаре");
            System.out.println("Наименование \t\t Производитель \t\t Количество \t\t Цена");
            for (int i = 0; i < kol; i++) {
                naimenovanie = rf.readUTF();
                for (int j = 0; j < 20 - naimenovanie.length(); j++)
                    rf.readByte();
                proizvoditel = rf.readUTF();
                for (int j = 0; j < 20 - proizvoditel.length(); j++)
                    rf.readByte();
                kolichestvo = rf.readInt();
                cena=rf.readInt();
                System.out.println(naimenovanie + "\t\t\t" + proizvoditel + "\t\t\t" + kolichestvo +"\t\t\t" + cena);
            }
            rf.close();
        } catch (IOException e) {
            System.out.println("End of file " + e);
        } }}

