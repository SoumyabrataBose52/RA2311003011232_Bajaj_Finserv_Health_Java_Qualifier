import java.net.*;
import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws Exception{
        String regNo="RA2311003011232";
        Set<String> seen=new HashSet<>();
        Map<String,Integer> scoreMap = new HashMap<>();
        for(int poll=0;poll<10;poll++){
            String urlStr ="https://devapigw.vidalhealthtpa.com/srm-quiz-task/quiz/messages?regNo="+regNo+"&poll="+poll;
            URL url =new URL(urlStr);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response=new StringBuilder();
            String line;
            while((line=br.readLine())!=null){response.append(line);}
            br.close();
            String json = response.toString();
            String[] events=json.split("\\{");
            for(String e:events){if(e.contains("roundId") && e.contains("participant")){String roundId=extract(e,"\"roundId\":\"","\"");
            String participant=extract(e,"\"participant\":\"", "\"");
            String scoreStr = extract(e,"\"score\":","}");
            int score = Integer.parseInt(scoreStr.trim());
            String key=roundId+"_"+participant;
            if(!seen.contains(key)){seen.add(key);scoreMap.put(participant,scoreMap.getOrDefault(participant,0)+score);}}}
            System.out.println("Poll "+poll+" processed.");
            Thread.sleep(5000);}
            List<Map.Entry<String, Integer>> list=new ArrayList<>(scoreMap.entrySet());
            list.sort((a,b)->b.getValue()-a.getValue());
            StringBuilder leaderboardJson=new StringBuilder();
            leaderboardJson.append("[");
            int total=0;
            for(int i=0;i<list.size();i++){Map.Entry<String,Integer> entry=list.get(i);
            leaderboardJson.append("{\"participant\":\"") .append(entry.getKey()) .append("\",\"totalScore\":") .append(entry.getValue()) .append("}");
            total+=entry.getValue();
            if(i!=list.size()-1) leaderboardJson.append(",");}
            leaderboardJson.append("]");
            String finalJson ="{"+"\"regNo\":\""+regNo+"\","+"\"leaderboard\":"+leaderboardJson.toString()+"}";
            System.out.println("\nSubmitting:");
            System.out.println(finalJson);
            URL postUrl=new URL("https://devapigw.vidalhealthtpa.com/srm-quiz-task/quiz/submit");
            HttpURLConnection postConn=(HttpURLConnection) postUrl.openConnection();
            postConn.setRequestMethod("POST");
            postConn.setDoOutput(true);
            postConn.setRequestProperty("Content-Type","application/json");
            OutputStream os = postConn.getOutputStream();
            os.write(finalJson.getBytes());
            os.flush();
            os.close();
            BufferedReader postBr=new BufferedReader(new InputStreamReader(postConn.getInputStream()));
            StringBuilder postResponse=new StringBuilder();
            String line2;
            while((line2=postBr.readLine())!=null){postResponse.append(line2);}
            postBr.close();
            System.out.println("\nResponse:");
            System.out.println(postResponse.toString());
            System.out.println("Total Score: "+total);}
    public static String extract(String src, String start, String end){
        int i=src.indexOf(start);
        if(i==-1) return "";
        i+=start.length();
        int j=src.indexOf(end, i);
        if(j==-1) return "";
        return src.substring(i, j);}}