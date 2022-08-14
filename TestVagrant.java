package Sdet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class TestVagrant extends jsonReader {

	@Test
	public void verifyOnlyFour() throws FileNotFoundException {

		FileInputStream fileinput = new FileInputStream(new File("\\RestAPI\\Jir\\jsonRcb.json"));

		JsonReader reader = Json.createReader(fileinput);
		JsonObject rcb = reader.readObject();
		int totalPlayers = 11;
		int foriegnPlayersAllowed = 4;
		// creating instance for utils
		RcbUtils utils = new RcbUtils();

		int totalForiegnPlayers = utils.getTotalForiegnPlayers(rcb.toString(), totalPlayers);
		// verify the foreign players are allowed of only 4
		System.out.println(totalForiegnPlayers);
		Assert.assertEquals(foriegnPlayersAllowed, totalForiegnPlayers);
		Reporter.log("The team is good to play ");

		boolean isWicketKeeperThere = utils.verifyKeepersArePresent(rcb.toString(), totalPlayers);
		Assert.assertEquals(true, isWicketKeeperThere);
		Reporter.log("The team is having a wicket keeper");

	}

}
