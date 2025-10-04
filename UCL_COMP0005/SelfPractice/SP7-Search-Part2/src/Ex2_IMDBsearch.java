import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Ex2_IMDBsearch {

	private Map<String, List<String>> movies;
	private Map<String, List<String>> actors;

	public Ex2_IMDBsearch() {
		movies = new HashMap<>();
		actors = new HashMap<>();
	}

	public List<String> listActorsInMovies(String movie) {
		return movies.containsKey(movie) ? movies.get(movie) : new ArrayList<>();
	}

	public List<String> listActorsInMoviesAlphabetically(String movie) {
		List<String> actors = movies.get(movie);
		if (actors == null) {
			return new ArrayList<>();
		}

		List<String> listActors = new ArrayList<>(actors);
		Collections.sort(listActors);
		return listActors;
	}

	public boolean didActorPerformMovie(String movie, String actor) {
		List<String> actors = movies.get(movie);
		return actors != null && actors.contains(actor);
	}

	public int moviesCountOfActor(String actor) {
		List<String> movies = actors.get(actor);
		return movies == null? 0 : movies.size();
	}

	public List<String> moviesOfActor(String actor) {
		return actors.get(actor);
	}

	public void recordMovie(String movie, List<String> actors) {
		movies.put(movie, actors);
	}

	public void recordActor(String actor, String movie) {
		actors.computeIfAbsent(actor, k -> new ArrayList<>()).add(movie);
	}


	public static void main(String[] args) {
		String fileName = "data/3-imdbtest.txt";
		Ex2_IMDBsearch imdb = new Ex2_IMDBsearch();

		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			while( (line = br.readLine()) != null){
				String[] data = line.split("/");
				List<String> actors = new ArrayList<>();
				for(int i = 1; i < data.length; i++){
					String actor = data[i];
					actor = actor.replace(", ", " ");
					actors.add(actor);
					imdb.recordActor(actor, data[0]);
				}
				imdb.recordMovie(data[0], actors);
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		String movie1 = "'Breaker' Morant (1980)";
		List<String> actors1 = imdb.listActorsInMovies(movie1);
		System.out.println("\n测试: listActorsInMovies for '" + movie1 + "'");
		System.out.println("预期结果: 非空列表, 包含电影的所有演员。");
		System.out.println("实际结果: " + (actors1.isEmpty() ? "列表为空" : actors1));

		// 测试 listActorsInMovies - 找不到电影
		String movie2 = "Non-Existent Movie (2024)";
		List<String> actors2 = imdb.listActorsInMovies(movie2);
		System.out.println("\n测试: listActorsInMovies for '" + movie2 + "'");
		System.out.println("预期结果: 空列表。");
		System.out.println("实际结果: " + (actors2.isEmpty() ? "列表为空" : actors2));

		// 测试 listActorsInMoviesAlphabetically
		String movie3 = "10 Things I Hate About You (1999)";
		List<String> sortedActors = imdb.listActorsInMoviesAlphabetically(movie3);
		System.out.println("\n测试: listActorsInMoviesAlphabetically for '" + movie3 + "'");
		System.out.println("预期结果: 包含演员的排序列表，例如: [Alisa Mackay, Allison Janney, Amber Matthews, Andrew Keegan, Ari Karczag, ...]");
		System.out.println("实际结果: " + sortedActors);

		// 测试 didActorPerformMovie - 演员参演了电影
		String actor1 = "Hanks, Tom";
		String movie4 = "'burbs, The (1989)";
		boolean result1 = imdb.didActorPerformMovie(movie4, actor1);
		System.out.println("\n测试: didActorPerformMovie for '" + actor1 + "' in '" + movie4 + "'");
		System.out.println("预期结果: true");
		System.out.println("实际结果: " + result1);

		// 测试 didActorPerformMovie - 演员未参演电影
		String actor2 = "Pacino, Al";
		String movie5 = "'burbs, The (1989)";
		boolean result2 = imdb.didActorPerformMovie(movie5, actor2);
		System.out.println("\n测试: didActorPerformMovie for '" + actor2 + "' in '" + movie5 + "'");
		System.out.println("预期结果: false");
		System.out.println("实际结果: " + result2);

		// 测试 moviesCountOfActor
		String actor3 = "Close, Glenn";
		int count = imdb.moviesCountOfActor(actor3);
		System.out.println("\n测试: moviesCountOfActor for '" + actor3 + "'");
		System.out.println("预期结果: 2");
		System.out.println("实际结果: " + count);

		// 测试 moviesOfActor - 找到演员
		String actor4 = "Close, Glenn";
		List<String> moviesForActor = imdb.moviesOfActor(actor4);
		System.out.println("\n测试: moviesOfActor for '" + actor4 + "'");
		System.out.println("预期结果: 非空列表，例如: [101 Dalmatians (1996), 102 Dalmatians (2000)]");
		System.out.println("实际结果: " + moviesForActor);

		// 测试 moviesOfActor - 找不到演员
		String actor5 = "Chris Evans";
		List<String> moviesForNonExistentActor = imdb.moviesOfActor(actor5);
		System.out.println("\n测试: moviesOfActor for '" + actor5 + "'");
		System.out.println("预期结果: null");
		System.out.println("实际结果: " + moviesForNonExistentActor);

		System.out.println("\n--- 测试用例结束 ---");
	}
}
