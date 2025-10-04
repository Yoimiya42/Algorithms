import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Ex1_WebTrafficTracking {

	private final Map<String, Map<String, Integer>> webSystem;

	public Ex1_WebTrafficTracking() {
		webSystem = new HashMap<>();
	}

	public void recordVisit(String user, String website) {
		webSystem.putIfAbsent(user, new HashMap<>());
		Map<String, Integer> userVisits = webSystem.get(user);

		userVisits.put(website, userVisits.getOrDefault(website, 0) + 1);
	}

	public boolean hasVisited(String user, String website) {
		if (! webSystem.containsKey(user))
			return false;

		return webSystem.get(user).containsKey(website);
	}

	public int getVisitCount(String user, String website) {
		if (! webSystem.containsKey(user))
			return 0;

		return webSystem.get(user).getOrDefault(website, 0);
	}

	public static void main(String[] args) {
		String filePath = "data/3-webtrack.txt";
		Ex1_WebTrafficTracking tracker = new Ex1_WebTrafficTracking();

		try{
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			while((line = br.readLine()) != null) {
				String[] userAndWebsite = line.split(",");
				tracker.recordVisit(userAndWebsite[0], userAndWebsite[1]);
			}
		}catch (IOException e){
			e.printStackTrace();
		}

		System.out.println(tracker.hasVisited("user 1", "url 1")); // True
		System.out.println(tracker.hasVisited("user 2", "url 2")); // True
		System.out.println(tracker.hasVisited("user 5", "url 2")); // True
		System.out.println(tracker.hasVisited("user 3", "url 7")); // False
		System.out.println(tracker.hasVisited("user 6", "url 4")); // False


		System.out.println(tracker.getVisitCount("user 1", "url 1"));  // 2
		System.out.println(tracker.getVisitCount("user 5", "url 3"));  // 2
		System.out.println(tracker.getVisitCount("user 4", "url 4"));  // 0
		System.out.println(tracker.getVisitCount("user 4", "url 2"));  // 4
	}
}