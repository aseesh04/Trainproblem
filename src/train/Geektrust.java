package train;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import static java.util.Collections.reverseOrder;

public class  Geektrust{
	public static List<String> departHYB(List<String> TrainA,List<String> TrainB,Map<String,Integer> station_dict) {
	Map<Integer,String>bogiesThatDepart_HYB=new HashMap<>();
	List<String>bogiesThatDepart=new ArrayList<>();
	for(String i:TrainA) {
		if(!i.equals("HYB")) {
			bogiesThatDepart_HYB.put(station_dict.get(i),i);
		}
	}
	for(String i:TrainB) {
		if(!i.equals("HYB")) {
			
			if(station_dict.get(i).equals(null))
				bogiesThatDepart_HYB.put(station_dict.get(i),i);
			else
				bogiesThatDepart_HYB.put(station_dict.get(i)+1,i);
		}
	}

	List<Entry<Integer, String>> res=bogiesThatDepart_HYB.entrySet().stream().sorted(reverseOrder(Map.Entry.comparingByKey())).collect(Collectors.toList());
	for(Entry<Integer, String> i : res) {
		bogiesThatDepart.add(i.getValue());
	}
    return bogiesThatDepart; 
	}
	public static List<String> arriving(List<String> Train,Map<String,Integer> station_dictA) {
		List<String> arrival=new ArrayList<>();
		
		arrival.addAll(Train.subList(2, Train.size()));

		List<String> res=new ArrayList<>();
			int  HYB = station_dictA.get("HYB");
			for(String i:arrival) {
				if(station_dictA.get(i)==null) {
					res.add(i);
				}
				else if(station_dictA.get(i)>=HYB) {
					res.add(i);	
				}
			}
			return res;
	}
	public static void main(String[] args) throws IOException {
		

String file=args[0];
		
		

		List<String> lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
List<String> TrainA=new ArrayList<>();
String A=lines.get(0);
String arrayA[]=A.split(" ");
TrainA=Arrays.asList(arrayA);

List<String> TrainB=new ArrayList<>();
String B=lines.get(1);
String arrayB[]=B.split(" ");
TrainB=Arrays.asList(arrayB);
Map<String,Integer> station_dictA=new HashMap<>();
station_dictA.put("CHN", 0);
station_dictA.put("SLM", 350);
station_dictA.put("BLR", 550);
station_dictA.put("KRN", 900);
station_dictA.put("HYB", 1200);
station_dictA.put("NGP", 1600);
station_dictA.put("ITJ", 1900);
station_dictA.put("BPL", 2000);
station_dictA.put("AGA", 2500);
station_dictA.put("NDL", 2700);
Map<String,Integer> station_dictB=new HashMap<>();
station_dictB.put("TVC", 0);
station_dictB.put("SRR", 300);
station_dictB.put("MAQ", 600);
station_dictB.put("MAO", 1000);
station_dictB.put("PNE", 1400);
station_dictB.put("HYB", 2000);
station_dictB.put("NGP", 2400);
station_dictB.put("ITJ", 2700);
station_dictB.put("BPL", 2800);
station_dictB.put("PTA", 3800);
station_dictB.put("NJP", 4200);
station_dictB.put("GHY", 4700);
Map<String,Integer> station_after_HYB=new HashMap<>();
station_after_HYB.put("HYB", 0);
station_after_HYB.put("NGP", 400);
station_after_HYB.put("ITJ", 700);
station_after_HYB.put("BPL", 800);
station_after_HYB.put("AGA", 2500);
station_after_HYB.put("NDL", 2700);
station_after_HYB.put("PTA", 3800);
station_after_HYB.put("NJP", 4200);
station_after_HYB.put("GHY", 4700);
List<String> bogiesThatDepart=new ArrayList<>();

TrainA=arriving(TrainA,station_dictA);
TrainB=arriving(TrainB,station_dictB);

bogiesThatDepart=departHYB(TrainA,TrainB,station_after_HYB);
TrainA.add(0,"ARRIVAL");
TrainA.add(1,"TrainA");
TrainA.add(2,"ENGINE");
TrainB.add(0,"ARRIVAL");
TrainB.add(1,"TrainB");
TrainB.add(2,"ENGINE");

bogiesThatDepart.add(0,"DEPARTURE");
bogiesThatDepart.add(1,"TrainAB");
bogiesThatDepart.add(2,"ENGINE");
bogiesThatDepart.add(3,"ENGINE");

String TrainAArriving = TrainA.stream().map(Object::toString)
.collect(Collectors.joining(" "));

String TrainBArriving = TrainB.stream().map(Object::toString)
.collect(Collectors.joining(" "));

String listString = bogiesThatDepart.stream().map(Object::toString)
.collect(Collectors.joining(" "));

System.out.println(TrainAArriving);
System.out.println(TrainBArriving);
System.out.print(listString);
	}

}
