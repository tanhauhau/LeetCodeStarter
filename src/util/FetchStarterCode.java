package util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lhtan on 17/6/17.
 */
public class FetchStarterCode {

    public static void main(String[] args) throws Exception {
        String url = "https://leetcode.com/problems/evaluate-division/#/description";
        String starterCode = getStarterCode(url);
        System.out.println("Getting starter code...");
        if (starterCode != null) {
            System.out.println("Found starter code!");
            String filename = getFileName();
            System.out.println("Writing into " + filename);
            writeStarterCode(starterCode, filename);
        }
    }

    private static String getStarterCode (String url) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print result
            String html = response.toString();
            Pattern regex = Pattern.compile(".*codeDefinition.*'text'\\s*:\\s*'Java'\\s*,\\s*'defaultCode'\\s*:\\s*'([^']*).*enableTestMode.*", Pattern.CASE_INSENSITIVE);
            Matcher matcher = regex.matcher(html);
            if (matcher.matches()) {
                return "import ds.ListNode;" + System.lineSeparator() +
                        "import ds.TreeNode;" + System.lineSeparator() +
                        System.lineSeparator() +
                        "import java.util.*;" + System.lineSeparator() +
                        System.lineSeparator() +
                        matcher.group(1).replace("\\u000D\\u000A", System.lineSeparator());
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private static String getFileName() {
        return Paths.get("./src/Solution.java").toAbsolutePath().normalize().toString();
    }

    private static void writeStarterCode(String code, String filename) {
        PrintWriter writer = null;
        try {
            File file = new File(filename);
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(code);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
