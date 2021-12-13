package com.company.BackEndDevelopment.Login.DataBase;


import com.company.BackEndDevelopment.Hangman.ScoreManager.GameStatusChecker;
import com.company.BackEndDevelopment.Hangman.ScoreManager.ScoreManagement;

import java.io.*;
import java.util.*;

public class ManagingDataBase {

    private static void writeIntoDataBase(Map<String, String> usernameDataBase,
                                          ScoreManagement scoreManagement) throws IOException {
        List<List<String>> rows = new ArrayList<>();
        for(Map.Entry<String, String> entry: usernameDataBase.entrySet()){

            List <String> score = scoreManagement.getScores(entry.getKey());
            GameStatusChecker gameStatusChecker = scoreManagement.getGameStatus(entry.getKey());

            rows.add(Arrays.asList(entry.getKey(), entry.getValue(),
                    score.get(0), score.get(1), String.valueOf(gameStatusChecker)));
        }
        String path = "src/main/java/com/company/BackEndDevelopment/Login/DataBase/database.csv";
        createOrMaintainDirectory(path);
        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(path))) {
            csvWriter.append("Username");
            csvWriter.append(",");
            csvWriter.append("Password");
            csvWriter.append(",");
            csvWriter.append("Current Score");
            csvWriter.append(",");
            csvWriter.append("Best Score");
            csvWriter.append(",");
            csvWriter.append("Result");
            csvWriter.append("\n");
            for (List<String> rowData : rows) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
        }
    }

    public static List<String> deriveUserScoreSheet (String username) throws IOException {
        List<String> usersScores = new ArrayList();
        String currentScore = "0";
        String bestScore = "0";

        if (isUserExists(username)){
            currentScore = getUserAndScoresFromDataBase().get(username).get(0);
            bestScore = getUserAndScoresFromDataBase().get(username).get(1);
        }
        usersScores.add(currentScore);
        usersScores.add(bestScore);
        return usersScores;

    }
    private static Map<String , String> readLoginDetailsFromDataBase() throws IOException {
        Map<String, String> userDataBase = new HashMap<>();
        String path = "src/main/java/com/company/BackEndDevelopment/Login/DataBase/database.csv";
        createOrMaintainDirectory(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                counter++;
                if (counter > 1) {
                    String[] x = line.split(",");
                    userDataBase.put(x[0], x[1]);
                    }
                }
            }
            return userDataBase;
        }

    private static Map <String, List<String>> getUserAndScoresFromDataBase() throws IOException {
            Map <String, List<String>> userAndScores = new HashMap<>();
            String path = "src/main/java/com/company/BackEndDevelopment/Login/DataBase/database.csv";
            createOrMaintainDirectory(path);
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                int counter = 0;
                while ((line = reader.readLine()) != null) {
                    counter++;
                    if (counter > 1) {
                        String[] x = line.split(",");
                        userAndScores.put(x[0], List.of(x[2], x[3]));
                    }
                }
            }
            return userAndScores;
        }

    private static Map<String, String> getUserAndGradeScaleFromDatabase() throws IOException {
            Map <String, String> userAndGrade = new HashMap<>();
            String path = "src/main/java/com/company/BackEndDevelopment/Login/DataBase/database.csv";
            createOrMaintainDirectory(path);
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                int counter = 0;
                while ((line = reader.readLine()) != null) {
                    counter++;
                    if (counter > 1) {
                        String[] x = line.split(",");
                        userAndGrade.put(x[0], x[4]);
                    }
                }
            }
            return userAndGrade;
        }

    public static GameStatusChecker getUserGradeScale(String username) throws IOException {
        Map<String, String> x = getUserAndGradeScaleFromDatabase();
        if (x.containsKey(username)){
            return GameStatusChecker.valueOf(x.get(username));
        }
        return GameStatusChecker.Unknown;
    }

        public static void addUserAndScore(String name, String password, ScoreManagement scoreManagement)
                throws IOException {
           Map<String, String> x = readLoginDetailsFromDataBase();
           x.put(name, password);
           writeIntoDataBase(x, scoreManagement);
        }


        public static boolean isUserExists(String name) throws IOException {
            Map<String, String> x = readLoginDetailsFromDataBase();
            return x.containsKey(name.toLowerCase());
        }

        public static String getUsersPassword(String username) throws IOException {
            Map<String, String> x = readLoginDetailsFromDataBase();
            return x.get(username.toLowerCase());
        }
        private static void createOrMaintainDirectory(String path) throws IOException {
            File csvFile = new File(path);
            csvFile.getParentFile().mkdirs();
            csvFile.createNewFile();
        }

        public static int getDatabaseSize() throws IOException {
            return readLoginDetailsFromDataBase().size();
        }

        public static List<List<String>> makingDatabaseAvailable() throws IOException {
            List<List<String>> rows = new ArrayList<>();
            Map<String, List<String>> userAndScores = getUserAndScoresFromDataBase();
            Map<String, String> userAndScale = getUserAndGradeScaleFromDatabase();
            List<Map.Entry<String, String>> listArranged = arrangeBasedOnBestscore();
            List<String> headers = Arrays.asList("Username", "Current Score", "Best Score", "Result");
            rows.add(headers);

            for (Map.Entry<String, String> entry : listArranged){
                rows.add(Arrays.asList(entry.getKey(), userAndScores.get(entry.getKey()).get(0),
                        userAndScores.get(entry.getKey()).get(1), userAndScale.get(entry.getKey())));
            }

            return rows;
        }

        public static  List<Map.Entry<String, String>> arrangeBasedOnBestscore() throws IOException {
            Map<String, String> userAndBestscore = getUsernameAndBestscore();
            List<Map.Entry<String, String>> list = new ArrayList<>(userAndBestscore.entrySet());
            List<Map.Entry<String, String>> listReverse = new ArrayList<>();
            list.sort(Map.Entry.comparingByValue());

            for (int i = (list.size() -1); i >= 0; i-- ){
                listReverse.add(list.get(i));
            }
            return listReverse;
        }

        private static Map<String, String> getUsernameAndBestscore() throws IOException {
            Map <String, String> userAndBestscore = new HashMap<>();
            String path = "src/main/java/com/company/BackEndDevelopment/Login/DataBase/database.csv";
            createOrMaintainDirectory(path);
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                int counter = 0;
                while ((line = reader.readLine()) != null) {
                    counter++;
                    if (counter > 1) {
                        String[] x = line.split(",");
                        userAndBestscore.put(x[0], x[3]);
                    }
                }
            }
            return userAndBestscore;
        }



}
