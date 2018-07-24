package smashbros;

import java.util.ArrayList;

public class Spielergebnisse {
	 static String s1,s2,ergebnis;
	static int lsc1,lsc2,spiel_ID;
	static String date;
	static ArrayList<Spielergebnisse> spErList=new ArrayList<>();
	public Spielergebnisse(String s1, String s2, String ergebnis, int lsc1, int lsc2, int spiel_ID, String date) {
		super();
		Spielergebnisse.s1 = s1;
		Spielergebnisse.s2 = s2;
		Spielergebnisse.ergebnis = ergebnis;
		Spielergebnisse.lsc1 = lsc1;
		Spielergebnisse.lsc2 = lsc2;
		Spielergebnisse.spiel_ID = spiel_ID;
		Spielergebnisse.date = date;
	}
	public static String getS1() {
		return s1;
	}
	public static String getS2() {
		return s2;
	}
	public static String getErgebnis1() {
		String[] s=ergebnis.split(":");
		String r1=s[0];
		return r1;
	}
	public static String getErgebnis2() {
		String[] s=ergebnis.split(":");
		String r2=s[1];
		return r2;
	}
	public static int getLsc1() {
		return lsc1;
	}
	public static int getLsc2() {
		return lsc2;
	}
	public static int getSpiel_ID() {
		return spiel_ID;
	}
	public static String getDate() {
		return date;
	}
	
}
