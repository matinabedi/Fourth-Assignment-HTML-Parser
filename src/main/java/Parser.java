import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    static public List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        Collections.sort(sortedByName, new Comparator<Country>() {
            @Override
            public int compare(Country p1, Country p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
        return  sortedByName;
    }


      static public List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        int n = sortedByPopulation.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (sortedByPopulation.get(j).getPopulation() < sortedByPopulation.get(j+1).getPopulation()) {
                    Country temp = sortedByPopulation.get(j);
                    sortedByPopulation.set(j, sortedByPopulation.get(j+1));
                    sortedByPopulation.set(j+1, temp);
                }
        return sortedByPopulation;
    }

         static public List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        int n = sortedByArea.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (sortedByArea.get(j).getArea() < sortedByArea.get(j+1).getArea()) {
                    Country temp = sortedByArea.get(j);
                    sortedByArea.set(j, sortedByArea.get(j+1));
                    sortedByArea.set(j+1, temp);
                }
        return sortedByArea;
    }

    public static void setUp() throws IOException {

        File input = new File("src/Resources/country-list.html");
        Document doc = Jsoup.parse(input, "UTF-8");
        Elements extractedData = doc.select("div.country");
        String countryName;
        String countryCapital;
        int countryPopulation;
        double countryArea;
        for (Element element:extractedData){
            countryName=element.getElementsByClass("country-name").first().text();
            countryCapital=element.getElementsByClass("country-capital").first().text();
            countryPopulation=Integer.parseInt(element.getElementsByClass("country-population").first().text());
            countryArea=Double.parseDouble(element.getElementsByClass("country-area").first().text());
            Country country = new Country(countryName,countryCapital,countryPopulation,countryArea);
            countries.add(country);
        }

        // Extract data from the HTML
        //TODO

        // Iterate through each country div to extract country data
        //TODO
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner =new Scanner(System.in);
        setUp();
        ArrayList<Country> list = new ArrayList<>();
        int input;
        do {
        System.out.println("1-sortByName\n2-sortByPopulation\n3-sortByArea\n4-exit\nwhich?");
        input= scanner.nextInt();
        switch (input) {
            case 1:
                int i = 1;
                for (Country country : sortByName()) {
                    System.out.println(i + "- " + country.getName());
                    i++;
                }
                break;
            case 2:
                i = 1;
                for (Country country : sortByPopulation()) {
                    System.out.println(i + "- " + country.getName() + "   Population: " + country.getPopulation());
                    i++;
                }
                break;
            case 3:
                i = 1;
                for (Country country : sortByArea()) {
                    System.out.println(i + "- " + country.getName() + "   Area: " + country.getArea());
                    i++;
                }
                break;
        }
        }while (input!=4);
    }
}
