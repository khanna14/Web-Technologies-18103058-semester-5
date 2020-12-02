import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

//BFS
public class  crawler {

    private static final HashSet<String> visitedLinks = new HashSet<>(); //Set of Visited Strings
    private static final Queue<Link> linkQueue = new LinkedList<>(); //Links Queue for BFS
    private static final List<String> facultyRelatedWordsList = new LinkedList<>(); //List of faculty related words
    private static int maxDepth = 1; //MAX DEPTH for traversing
    private static FileWriter textFile; //Writer for file storing extracted texts
    private static FileWriter linksFile; //Writer for file storing extracted links
    private static FileWriter objectFile; //Writer for file storing extracted links to object files like pdfs
    private static String urlContains = ""; //String which should be there in each url to prevent crawler from going outside the website
    private static int depth = 0; //Current Depth

    public static boolean isValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static int frequency(String paragraph, String substring) {
        if (paragraph.length() == 0 || substring.length() == 0) {
            return 0;
        }
        return paragraph.split(substring).length - 1;
    }

    public static boolean facultyRelated(final Document page) {
        if (page == null) {
            return false;
        }
        int frequencyOfFacultyRelatedWords = 0;
        String htmlPageString = page.toString().toLowerCase();
        for (String word :
                facultyRelatedWordsList) {
            frequencyOfFacultyRelatedWords += frequency(htmlPageString, word);
            if (frequencyOfFacultyRelatedWords >= 15) {
                return true;
            }
        }
        return false;

    }

    public static boolean facultyRelated(String str, int freqThreshold) {
        if (str.length() == 0) {
            return false;
        }
        str = str.toLowerCase();
        int frequencyOfFacultyRelatedWords;
        for (String word :
                facultyRelatedWordsList) {
            frequencyOfFacultyRelatedWords = frequency(str, word);
            if (frequencyOfFacultyRelatedWords > freqThreshold) {
                return true;
            }
        }
        return false;

    }

    public static void extractLinks(final Document page) {
        if (page == null) {
            return;
        }
        Elements links = page.getElementsByTag("a");
        for (Element link : links) {
            String newURL = link.attr("abs:href"); //"abs" will return only absolute links by converting relative links to absolute
            if (!newURL.contains("#") && !visitedLinks.contains(newURL) && newURL.contains(urlContains)) {
                Link temp = new Link();
                temp.url = newURL;
                temp.text = link.text();
                linkQueue.add(temp);
            }
        }
    }

    public static void extractText(final Document page) {
        if (page == null) {
            return;
        }
        for (String tag :
                ("p blockquote").split(" ")) {
            Elements tagElementsList = page.getElementsByTag(tag);
            for (Element tagElement : tagElementsList) {
                if (facultyRelated(tagElement.text(), 3)) {
                    try {
                        String row = "<" + tag + ">,\"" + tagElement.text() + "\"\r\n";
                        textFile.write(row);
                    } catch (IOException e) {
                        System.out.println("Error writing to text.csv!");
                    }
                }
            }

        }

    }

    public static void crawl(String url, String text) {
        if (url.length() == 0) {
            return;
        }
        if (visitedLinks.contains(url)) {
            return;
        }
        visitedLinks.add(url);
        try {
            if (depth <= maxDepth) {
                Document page = Jsoup.connect(url).get(); //send get request
                if (facultyRelated(url, 0) || facultyRelated(page)) { //if page/url is related to faculty
                    extractText(page);
                    try {
                        String row = text + "," + url + "\r\n";
                        linksFile.write(row);
                    } catch (IOException e) {
                        System.out.println("Error writing to links.csv!");
                    }
                }
                extractLinks(page);
            } else if (facultyRelated(url, 0)) { //if url is faculty related
                Jsoup.connect(url).execute(); //send get request to check for nonHTML links
                try {
                    String row = text + "," + url + "\r\n";
                    linksFile.write(row);
                } catch (IOException e) {
                    System.out.println("Error writing to links.csv!");
                }
            }

        } catch (UnsupportedMimeTypeException type) {//for objects (like pdf,jpg etc) links
            if (facultyRelated(url, 0)) {
                try {
                    objectFile.write(text + "," + url + "\r\n");
                } catch (IOException e) {
                    System.out.println("Error writing to object.csv!");
                }
            }
        } catch (IOException e) {
//            System.out.println("Unable to parse " + url);
        }

    }

    public static void main(String[] args) {
        try {
            if (!System.getProperty("user.dir").contains("Assignment_3")) {
                linksFile = new FileWriter("Assignment 3\\links.csv");
                textFile = new FileWriter("Assignment 3\\text.csv");
                objectFile = new FileWriter("Assignment 3\\object.csv");
            } else {
                linksFile = new FileWriter("links.csv");
                textFile = new FileWriter("text.csv");
                objectFile = new FileWriter("object.csv");
            }

            String row = "Tag ,Text\r\n";
            textFile.write(row);

            row = "Link Text,URL\r\n";
            linksFile.write(row);

            row = "Link Text,URL\r\n";
            objectFile.write(row);

        } catch (IOException e) {
            System.out.println("Unable To Create File(s)!");
            System.exit(1);
        }
        //Input
        String url = "";
        try {
            System.out.print("Input URL: ");
            Scanner input = new Scanner(System.in);
            url = input.nextLine(); //"https://pec.ac.in/";
            System.out.print("Enter String which should be in every url: ");
            urlContains = input.nextLine(); // "pec.ac.in/
            System.out.print("Enter MAX Depth(BFS): ");
            maxDepth = input.nextInt(); // 4
            input.nextLine();
            input.close();
        } catch (Exception e) {
            System.out.println("Invalid Input!");
            System.exit(1);
        }

        long startTime = System.nanoTime(); //start time

        //Check url validity
        if (!isValid(url)) {
            System.out.println("INVALID URL!");
            return;
        }

        //add words to list
        facultyRelatedWordsList.addAll(Arrays.asList(("dr faculty professor prof assistant publications qualification books published projects").split(" ")));

        //BFS
        Link temp = new Link();
        temp.url = url;
        temp.text = " ---ROOT--- ";
        linkQueue.add(temp);
        linkQueue.add(null);//For separating levels
        while (!linkQueue.isEmpty()) {
            temp = linkQueue.remove();
            if (temp == null) {
                depth++;
                if (!linkQueue.isEmpty()) {//For non leaf level
                    linkQueue.add(null);
                    System.out.println("Level " + (depth - 1) + " Complete");
                }
            } else {
                crawl(temp.url, temp.text);
            }
        }

        try {

            textFile.close();
            linksFile.close();
            objectFile.close();

        } catch (IOException e) {
            System.out.println("Unable to close files!");
        }

        long endTime = System.nanoTime();
        double timeElapsed = (endTime - startTime) / 1000000000.0;
        System.out.println("Time Elapsed: " + timeElapsed / 60.0 + " minutes");//Time in minutes

    }

    //Class for URLS
    private static class Link {
        public String url = ""; //url
        public String text = ""; //text in "<a>" tag when url is found
    }
}