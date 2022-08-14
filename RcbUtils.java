package Sdet;

import java.util.Map;

import org.testng.Reporter;

import io.restassured.path.json.JsonPath;

public class RcbUtils {
	static String country = "country";
	static String india = "India";
	static String role = "role";
	static String wicketKeeper = "Wicket-keeper";
	static String player = "player";

	// get the total foriegn players from team
	@SuppressWarnings("unused")
	public int getTotalForiegnPlayers(String body, int totalPlayers) {
		JsonPath path = JsonPath.given(body);
		int totalForiegnPlayers = 0;
		for (int i = 0; i < totalPlayers; i++) {
			Map<String, String> players = path.getMap(RcbUtils.player + "[" + i + "]");
			if (!players.get(RcbUtils.country).toString().equals(RcbUtils.india)) {
				totalForiegnPlayers++;
				
			}
			Reporter.log("Total foriegn players found are "+totalForiegnPlayers);
		}
		return totalForiegnPlayers;
	}

	// get the total wicket keepers from team
	public int getTotalWicketKeepers(String body, int totalPlayers) {
		JsonPath path = JsonPath.given(body);
		int totalKeepers = 0;
		for (int i = 0; i < totalPlayers; i++) {
			Map<String, String> players = path.getMap(RcbUtils.player + "[" + i + "]");
			if (players.get(RcbUtils.role).toString().equals(RcbUtils.wicketKeeper)) {
				totalKeepers++;
			}
			Reporter.log("Total foriegn players found are "+totalKeepers);
		}
		return totalKeepers;
	}
	// ---added verify in a extra method for code re-usiblity in future if there is
	// a case to get total wicket keepercounts

	// verify keepers are atleast one
	public boolean verifyKeepersArePresent(String body, int totalPlayers) {
		int totalKeepers = getTotalWicketKeepers(body, totalPlayers);
		boolean flag = false;
		if (totalKeepers > 0) {
			flag = true;
		}
		return flag;
	}
	
}
