package CRUD;

import java.io.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        boolean loopContinue = true;
        
        while (loopContinue) {
            clearScreen();
            System.out.println("* Database Toko HandPhone *\n");
            System.out.println("1.  Lihat Data Handphone");
            System.out.println("2.  Cari Data Handphone");
            System.out.println("3.  Tambah Data Handphone");
            System.out.println("4.  Ubah Data Handphone");
            System.out.println("5.  Hapus Data Handphone");
    
            System.out.print("\n\nMasukan Pilihan Anda [1 - 5] : ");
            String pilihanUser = scanner.nextLine();
    

            switch (pilihanUser) {
                case "1":
                    System.out.println("\n=======================");
                    System.out.println("* READ HANDPHONE DATA *");
                    System.out.println("=======================");

                    // Lihat Data
                    readData(); 
                break;
                case "2":
                    System.out.println("\n=========================");
                    System.out.println("* SEARCH HANDPHONE DATA *");
                    System.out.println("=========================");

                    // Cari Data
                    searchData();
                break;
                case "3":
                    System.out.println("\n=========================");
                    System.out.println("* CREATE HANDPHONE DATA *");
                    System.out.println("=========================");

                    // Tambah Data
                    createData();
                break;
                case "4":
                    System.out.println("\n=========================");
                    System.out.println("* CHANGE HANDPHONE DATA *");
                    System.out.println("=========================");
                    // Ubah Data
                    break;
                    case "5":
                    System.out.println("\n=========================");
                    System.out.println("* DELETE HANDPHONE DATA *");
                    System.out.println("=========================");
                    // Hapus Data
                    break;
                    default:
                    System.err.println("\nInput anda tidak ditemukan, Silahkan masukan input dari 1 sampai 5");
                break;
                
            }
            
            loopContinue = getYesorNo("Apakah anda ingin melanjutkan program");
        }
        
        
    }
    
    public static void createData() throws IOException {

        FileWriter fileOutput = new FileWriter("JAVA CRUD/src/CRUD/database2.txt");



    }
    
    public static void searchData() throws IOException {

        // Membaca database ada atau tidak
        try {
            File file = new File("JAVA CRUD/src/CRUD/database2.txt"); 
        } catch ( Exception e ) {
            System.err.println("Database tidak ditemuka1n !");
            System.err.println("Silahkan tambah data terlebih dahulu");
            return;
        }

        // Input Keyword dari user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukan Keyword HP yang ingin kamu cari : ");
        String cariString = scanner.nextLine();

        String keyword[] = cariString.split("\\s+");

        // Cek Keyword di Database
        cekDataDatabase(keyword);
        
        
    }


    public static void cekDataDatabase(String[] keywords) throws IOException {

        FileReader fileinput = new FileReader("JAVA CRUD/src/CRUD/database2.txt");
        BufferedReader bufferinput = new BufferedReader(fileinput);

        int totalData = 0;
        boolean isExist;
        String data = bufferinput.readLine();

        System.out.println("\n| No |\tTahun |\tLaunching Name        |\tProcessor              |     Ram ( GB )      |\tStorage ( GB )    |\tBattery");
        System.out.println("==========================================================================================================================");
        

        while(data != null) {
            
            // cek keyword dalam baris
            isExist = true;

            for(String keyword:keywords) {
                isExist = isExist && data.toLowerCase().contains(keyword.toLowerCase());
            }

            // Menampilkan data jika keyword exist
            if(isExist) {

                totalData++;
                StringTokenizer stringToken = new StringTokenizer(data, ",");

                stringToken.nextToken();
                System.out.printf("| %2d ", totalData);
                System.out.printf("|\t%4s  ", stringToken.nextToken());
                System.out.printf("|\t%-20s  ", stringToken.nextToken());
                System.out.printf("|\t%-20s   ", stringToken.nextToken());                    System.out.printf("|\t%-10s   ", stringToken.nextToken());
                System.out.printf("|\t%-15s   ", stringToken.nextToken());
                System.out.printf("|\t%s  ", stringToken.nextToken());
                
                System.out.print("\n");        
            }

            data = bufferinput.readLine();

        }

        System.out.println("==========================================================================================================================");


    }


    public static void readData() throws IOException {
        FileReader fileinput;
        BufferedReader bufferinput;

        try {
            fileinput = new FileReader("JAVA CRUD/src/CRUD/database2.txt");
            bufferinput = new BufferedReader(fileinput);
        } catch ( Exception e ) {
            System.err.println("Database tidak ditemuka1n !");
            System.err.println("Silahkan tambah data terlebih dahulu");
            return;
        }

        
        System.out.println("\n| No |\tTahun |\tLaunching Name        |\tProcessor              |     Ram ( GB )      |\tStorage ( GB )    |\tBattery");
        System.out.println("==========================================================================================================================");
        
        String data = bufferinput.readLine();
        int nomorData = 0;

        while(data != null) {
            nomorData++;
            StringTokenizer stringToken = new StringTokenizer(data, ",");
        
        
            stringToken.nextToken();
            System.out.printf("| %2d ", nomorData);
            System.out.printf("|\t%4s  ", stringToken.nextToken());
            System.out.printf("|\t%-20s  ", stringToken.nextToken());
            System.out.printf("|\t%-20s   ", stringToken.nextToken());
            System.out.printf("|\t%-10s   ", stringToken.nextToken());
            System.out.printf("|\t%-15s   ", stringToken.nextToken());
            System.out.printf("|\t%s  ", stringToken.nextToken());
            
            System.out.print("\n");

            data = bufferinput.readLine();
        }

        System.out.println("==========================================================================================================================");
        System.out.println("Akhir Dari Database !");

    }


    public static void readData2() throws IOException {
        FileReader fileinput;
        BufferedReader bufferinput;

        try {
            fileinput = new FileReader("JAVA CRUD/src/CRUD/database.txt");
            bufferinput = new BufferedReader(fileinput);
        } catch ( Exception e ) {
            System.err.println("Database tidak ditemukan !");
            System.err.println("Silahkan tambah data terlebih dahulu");
            return;
        }

        
        System.out.println("\n| No |\tTahun |\tMerek       |\tSeries       |\tLaunching Name");
        System.out.println("===========================================================");
        
        String data = bufferinput.readLine();
        int nomorData = 0;

        while(data != null) {
            nomorData++;
            StringTokenizer stringToken = new StringTokenizer(data, ",");
        
        
            stringToken.nextToken();
            System.out.printf("| %2d ", nomorData);
            System.out.printf("|\t%4s  ", stringToken.nextToken());
            System.out.printf("|\t%-10s  ", stringToken.nextToken());
            System.out.printf("|\t%-10s   ", stringToken.nextToken());
            System.out.printf("|\t%s  ", stringToken.nextToken());
            
            System.out.print("\n");

            data = bufferinput.readLine();
        }

        System.out.println("===========================================================");
        System.out.println("Akhir Dari Database !");

    }


    public static boolean getYesorNo(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\n" + message + " (y/n)? ");
        String pilihanUser  = scanner.nextLine();
        
        while (!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")) {
            System.err.println("Pilihan anda bukan y atau n");
            System.out.print("\n\n" + message + " (y/n)? ");
            pilihanUser  = scanner.nextLine();
        }
        
        return pilihanUser.equalsIgnoreCase("y");

    }


    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch ( Exception ex ) {
            System.err.println("Tidak Bisa Clear Screen");
        }
    }
}
