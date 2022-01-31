package CRUD;

import java.io.*;
import java.time.Year;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    
    // Code by Radja Aulia Al Ramdani a.k.a @HamsterKaget => github.com/hamsterkaget
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
                    readData();
                break;
                case "4":
                    System.out.println("\n=========================");
                    System.out.println("* CHANGE HANDPHONE DATA *");
                    System.out.println("=========================");
                    
                    // Ubah Data
                    updateData();
                break;
                    case "5":
                    System.out.println("\n=========================");
                    System.out.println("* DELETE HANDPHONE DATA *");
                    System.out.println("=========================");
                    
                    // Hapus Data
                    deleteData();
                break;
                    default:
                    System.err.println("\nInput anda tidak ditemukan, Silahkan masukan input dari 1 sampai 5");
                break;
                
            }
            
            loopContinue = getYesorNo("Apakah anda ingin melanjutkan program");
        }
        
        
    }
    
    // Method Untuk Membaca Data | 
    private static void readData() throws IOException {
        FileReader fileinput;
        BufferedReader bufferinput;
        
        try {
            fileinput = new FileReader("database.txt");
            bufferinput = new BufferedReader(fileinput);
        } catch ( Exception e ) {
            System.err.println("Database tidak ditemukan !");
            System.err.println("Silahkan tambah data terlebih dahulu");
            createData();
            return;
        }

        
        System.out.println("\n| No |\tTahun |\t  Merek   |\tLaunching Name        |\t\t Processor\t         |    Ram ( GB )     |\tStorage ( GB )    |\tBattery");
        System.out.println("===========================================================================================================================================");
        
        String data = bufferinput.readLine();
        int nomorData = 0;

        while(data != null) {
            nomorData++;
            StringTokenizer stringToken = new StringTokenizer(data, ",");
        
            // Skip identifier
            stringToken.nextToken();
            System.out.printf("| %2d ", nomorData); // Nomor
            System.out.printf("|\t%4s  ", stringToken.nextToken()); // Tahun
            System.out.printf("|\t%-10s", stringToken.nextToken()); // Merek
            System.out.printf("|\t%-20s  ", stringToken.nextToken()); // Launching Name
            System.out.printf("|\t%-30s   ", stringToken.nextToken()); // Processor
            System.out.printf("|\t%-10s   ", stringToken.nextToken()); // Ram
            System.out.printf("|\t%-15s   ", stringToken.nextToken()); // Storage
            System.out.printf("|\t%s  ", stringToken.nextToken()); // Baterai
            
            System.out.println();

            data = bufferinput.readLine();
        }

        System.out.println("===========================================================================================================================================");
        System.out.println("Akhir Dari Database !");

    }
    
    // Method Untuk Mencari Data | [ cekDataDatabase(); ]
    private static void searchData() throws IOException {

        // Membaca database ada atau tidak
        try {
            File file = new File("database.txt"); 
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
        cekDataDatabase(keyword,true);
        
        
    }
    
    // Method Untuk Menambah Data | [ getYear(), cekDataDatabase(), getEntryPerYear(), getYesorNo();  ]
    private static void createData() throws IOException {

        FileWriter fileOutput = new FileWriter("database.txt",true);
        BufferedWriter bufferOutput = new BufferedWriter(fileOutput);

        Scanner scanner = new Scanner(System.in);
        String merk, launchingName , processor , ram , storage , battery , year;

        System.out.print("Masukan Merk / Brand Handphone : ");
        merk = scanner.nextLine().toUpperCase();
        System.out.print("Masukan Full Nama Handphone : ");
        launchingName = scanner.nextLine().toUpperCase();
        System.out.print("Masukan Jenis Prosessor : ");
        processor = scanner.nextLine();
        System.out.print("Masukan Varian RAM : ");
        ram = scanner.nextLine();
        System.out.print("Masukan Varian Penyimpanan : ");
        storage = scanner.nextLine();
        System.out.print("Masukan Kapasitas Batterai : ");
        battery = scanner.nextLine();
        System.out.print("Masukan Tahun Rilis, Format=(YYYY) : ");
        year = getYear();
        
        // Cek Data di Database 
        String[] keywords = {year + "," + merk + "," + launchingName + "," + processor + "," + ram + "," + storage + "," + battery};        
        
        boolean isExist = cekDataDatabase(keywords, false);
        
        // Menulis Data Baru di database2.txt
        if( !isExist ) {

            long nomorEntry =  getEntryPerYear(merk, year) + 1;

            String withoutSpace = merk.replaceAll("\\s", "");
            String primaryKey = merk + "_" + year + "_" + nomorEntry;
            System.out.println("\nData yang akan anda masukan adalah");
            System.out.println("------------------------------------");
            System.out.println("primary key       : " + primaryKey);
            System.out.println("Tahun Rilis       : " + year);
            System.out.println("Merk / Brand      : " + merk);
            System.out.println("Full Hp Name      : " + launchingName);
            System.out.println("Jenis Prossesor   : " + processor);
            System.out.println("Kapasitas Ram     : " + ram);
            System.out.println("Total Penyimpanan : " + storage);
            System.out.println("Kapasitas Baterai : " + battery);

            boolean isTambah = getYesorNo("Apakah anda ingin menambah data tersebut ?");

            if (isTambah) {
                bufferOutput.write(primaryKey + "," + year +"," + merk + "," + launchingName + "," + processor + "," + ram + "," + storage + "," + battery);
                bufferOutput.newLine();
                bufferOutput.flush();
            
            }

        } else {
            System.out.println("Data dari handphone tersebut sudah ada di database kami !");
            cekDataDatabase(keywords, true);
        }

        bufferOutput.close();
    }

    // Method Untuk Mengupdate Data
    private static void updateData() throws IOException {

    }

    //Method Untuk Menghapus data
    private static void deleteData() throws IOException {
        // Ambil database original
        File database = new File("database.txt");
        FileReader fileinput = new FileReader(database);
        BufferedReader bufferinput = new BufferedReader(fileinput);

        // Buat database sementara
        File tempDB = new File("tempDB.txt");
        FileWriter fileOutput = new FileWriter(tempDB);
        BufferedWriter bufferOutput = new BufferedWriter(fileOutput);


        // Ambil UserInput untuk mendelete data

        // Looping untuk membaca tiap data baris dan skip data yang akan di delete


    }


    // Method khusus untuk menangani kebutuhan tertentu untuk menunjang 5 method di atas
    public static long getEntryPerYear(String merk, String year) throws IOException {
        FileReader fileinput = new FileReader("database.txt");
        BufferedReader bufferinput = new BufferedReader(fileinput);

        long entry = 0;
        String data = bufferinput.readLine();
        Scanner dataScanner;
        String primaryKey;

        while ( data != null ) {
            dataScanner = new Scanner(data);
            dataScanner.useDelimiter(",");
            primaryKey = dataScanner.next();
            dataScanner = new Scanner(primaryKey);
            dataScanner.useDelimiter("_");
            
            merk = merk.replaceAll("\\s+", "");

            if ( merk.equalsIgnoreCase(dataScanner.next()) && year.equalsIgnoreCase(dataScanner.next())) {
                entry = dataScanner.nextInt();
            }

            data = bufferinput.readLine();
        }
        return entry;
    }

    public static String getYear() throws IOException {

        Scanner scanner = new Scanner(System.in);
        String year = scanner.nextLine();
        boolean validationYear = false;

        while(!validationYear) {

            try {
                Year.parse(year);
                validationYear = true;
            } catch ( Exception e ) {
                System.err.println("Format Tahun Rilis Tidak Valid, Format=(YYYY)");
                System.out.print("\nMasukan Kembali Tahun Rilis :");
                validationYear = false;
                year = scanner.nextLine();
            }

        }

        return year;

    }

    public static boolean cekDataDatabase(String[] keywords, boolean isDisplay) throws IOException {

        FileReader fileinput = new FileReader("database.txt");
        BufferedReader bufferinput = new BufferedReader(fileinput);

        int totalData = 0;
        boolean isExist = false;
        String data = bufferinput.readLine();

        if(isDisplay) {
            System.out.println("\n| No |\tTahun |\t  Merek   |\tLaunching Name        |\t\t Processor\t         |    Ram ( GB )     |\tStorage ( GB )    |\tBattery");
            System.out.println("===========================================================================================================================================");
        }
        

        while(data != null) {
            
            // cek keyword dalam baris
            isExist = true;

            for(String keyword:keywords) {
                isExist = isExist && data.toLowerCase().contains(keyword.toLowerCase());
            }

            // Menampilkan data jika keyword exist
            if(isExist) {
                if(isDisplay) {
                    totalData++;
                    StringTokenizer stringToken = new StringTokenizer(data, ",");
    
                    // Skip identifier
                    stringToken.nextToken();
                    System.out.printf("| %2d ", totalData); // Nomor
                    System.out.printf("|\t%4s  ", stringToken.nextToken()); // Tahun
                    System.out.printf("|\t%-10s", stringToken.nextToken()); // Merek
                    System.out.printf("|\t%-20s  ", stringToken.nextToken()); // Launching Name
                    System.out.printf("|\t%-30s   ", stringToken.nextToken()); // Processor
                    System.out.printf("|\t%-10s   ", stringToken.nextToken()); // Ram
                    System.out.printf("|\t%-15s   ", stringToken.nextToken()); // Storage
                    System.out.printf("|\t%s  ", stringToken.nextToken()); // Baterai
                    
                    System.out.print("\n");        
                } else {
                    break;
                }
            }

            data = bufferinput.readLine();

        }

        if(isDisplay) {
            System.out.println("===========================================================================================================================================");
        }
        
        return isExist;
        
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
