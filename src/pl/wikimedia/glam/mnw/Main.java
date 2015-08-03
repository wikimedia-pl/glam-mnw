/*
 * The MIT License
 *
 * Copyright 2015 Paweł Marynowski.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package pl.wikimedia.glam.mnw;

import com.rdksys.oai.Harvester;
import com.rdksys.oai.data.Metadata;
import com.rdksys.oai.data.Record;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wikipedia.Wiki;

/**
 * Main class
 *
 */
public class Main {

  static boolean UPLOAD = false;
  static boolean TEST = false;

  static Wiki wiki = new Wiki("commons.wikimedia.org");
  static String user;
  static Harvester harvester;

  static String log = "";

  /**
   * Main program
   *
   * @param args args
   */
  public static void main(String[] args) {

    try {
      harvester = new Harvester("http://cyfrowe.mnw.art.pl/dmuseion/oai-pmh-repository.xml");
    } catch (Exception ex) {
      System.out.println("[!] Repository Fatal Error!");
    }

    for (int i = 0; i < args.length; ++i) {
      if (args[i].equals("upload")) {
        UPLOAD = true;
      }
      if (args[i].equals("test")) {
        TEST = true;
        wiki = new Wiki("test.wikipedia.org");
      }
    }

    System.out.println("\n*************************************************************************");
    System.out.println(" WMPL GLAM Upload Tool\n National Museum in Warsaw\n https://github.com/wikimedia-pl/glam-mnw ");
    System.out.println("*************************************************************************");

    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

    if (UPLOAD) {
      for (;;) {
        try {
          System.out.print("\n > User: ");
          user = bufferRead.readLine();
          System.out.print(" > Password: ");
          String password = bufferRead.readLine();

          System.out.print("\n[.] Logging in...");
          wiki.login(user, password);
          wiki.setUserAgent("WMPL GLAM Upload Tool/1.0 (https://github.com/wikimedia-pl/glam-mnw)");
          wiki.setMarkBot(true);
          System.out.print(" OK!\n");
          break;
        } catch (IOException ex) {
          System.out.println("[!] Error!");
        } catch (FailedLoginException ex) {
          System.out.println("\n[!] Could not login. Wrong password?");
        }
      }
    }

    System.out.println("\n Enter single photo ID (eg. 24326), range (eg. 24326-24330) to process files.");
    System.out.println(" Enter 0 for exit.");

    for (;;) {
      System.out.print("\n > ");

      try {
        String text = bufferRead.readLine();

        if (text.equals("0")) {
          System.exit(0);
        } else if (text.matches("[0-9]* ?- ?[0-9]*")) {
          getMultiplePhotos(text);
        } else {
          getSinglePhoto(text);
        }

        if (UPLOAD) {
          System.out.print("[.] Saving log...");
          wiki.newSection("User:" + user + "/Muzeum Narodowe Warszawa", "", log, false, true);
          System.out.print(" OK!\n");
        }

      } catch (NumberFormatException ex) {
        System.out.println("[!] Could not parse as a number.");
      } catch (IOException ex) {
        System.out.println("[!] Womething went wrong!");
      } catch (LoginException ex) {
        System.out.println("[!] Not logged in");
      }
    }
  }

  private static void getSinglePhoto(String text) {
    int number = Integer.parseInt(text);
    getPhoto(number);
  }

  private static void getMultiplePhotos(String text) {
    String[] range = text.split("-");
    int min = Integer.parseInt(range[0]);
    int max = Integer.parseInt(range[1]);

    for (; min < max + 1; ++min) {
      getPhoto(min);
    }
  }

  /**
   * Returns photo description ready for upload
   *
   * @param id photo ID
   * @return photo desc in wikicode
   */
  static void getPhoto(int id) {
    try {
      System.out.println("[" + id + "] Getting data...");
      Record record = harvester.getRecord("oai:cyfrowe.mnw.art.pl:" + id);
      Metadata metadata = record.getMetadata();

      showEntries(metadata.getContributorList(), "ContributorList");
      showEntries(metadata.getCoverageList(), "CoverageList");
      showEntries(metadata.getCreatorList(), "CreatorList");
      showEntries(metadata.getDateList(), "DateList");
      showEntries(metadata.getDescriptionList(), "DescriptionList");
      showEntries(metadata.getFormatList(), "FormatList");
      showEntries(metadata.getIdentifierList(), "IdentifierList");
      showEntries(metadata.getLanguageList(), "LanguageList");
      showEntries(metadata.getPublisher(), "PublisherList");
      showEntries(metadata.getRelationList(), "RelationList");
      showEntries(metadata.getRightsList(), "RightsList");
      showEntries(metadata.getSourceList(), "SourceList");
      showEntries(metadata.getSubjectList(), "SubjectList");
      showEntries(metadata.getTitleList(), "TitleList");
      showEntries(metadata.getTypeList(), "TypeList");
    } catch (Exception ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  static void showEntries(ArrayList<String> entries, String name) {
    System.out.println("__ " + name + "() __");
    for (String entry : entries) {
      System.out.println(entry);
    }
    System.out.println("");
  }

  /**
   * Returns photo description ready for upload
   *
   * @param id photo ID
   * @return photo desc in wikicode
   */
  static void getPhoto_(int id) {
    Document doc;
    try {
      System.out.println("[" + id + "] Getting data...");
      doc = Jsoup.connect("http://cyfrowearchiwum.amu.edu.pl/archive_ajax/" + id).get();

      Photo photo = new Photo(id);
      Element elem;
      Element elem_;
      Elements elems;

      elems = doc.select(".file a");
      if (elems.isEmpty()) {
        throw new IOException();
      }
      elem = elems.get(0);

      photo.setPath(elem.attr("onclick"));

      // data
      elem = doc.select(".metadata .nr_invent").get(0);
      photo.setAccNumber(elem.text());

      elem = doc.select(".metadata .author").get(0);
      photo.setAuthor(elem.text());

      elems = doc.select(".metadata .Rok");
      elem = elems.isEmpty() ? null : elems.get(0);
      elem_ = doc.select(".metadata .description").get(0);
      if (elem_.text().matches("Zdjęcie wykonan[eo] .* r\\.")) {
        photo.setDate(elem_.text());
      } else {
        photo.setDate(elem == null ? "" : elem.text());
        photo.setComment(elem_.text());
      }

      elems = doc.select(".metadata .Lokacja");
      if (!elems.isEmpty()) {
        photo.setLocation(elems.get(0).text());
      }

      elems = doc.select(".metadata .Tagi a");
      photo.setTags(elems);

      elem = doc.select(".metadata .title").get(0);
      photo.setTitle(elem.text());

      // result
      System.out.println("[" + id + "] Parsed as File:" + photo.getName());

      if (UPLOAD) {
        File f = photo.getFile();
        if (f == null) {
          throw new IOException();
        }

        System.out.println("[" + id + "] File downloaded. Uploading...");
        wiki.upload(f, photo.getName(), photo.getWikiText(), "import");
        System.out.println("[" + id + "] OK!\n");

        log += "# [" + id + "] [[:File:" + photo.getName() + "]]\n";
        log += "#: Categories: ";

        if (photo.getCategories().contains("subst:unc")) {
          log += "'''no categories'''";
        } else {
          log += photo.getCategories().replaceAll("\\[\\[Category:", "\\[\\[:Category:").replaceAll("]]", "|]]").replaceAll("\n", ", ");
        }

        log += "\n";
      } else {
        System.out.println("\n" + photo.getWikiText());
      }

    } catch (IOException ex) {
      System.out.println("[!] Could not get data. Probably this ID is not used or not image.\n");
    } catch (LoginException ex) {
      System.out.println("[!] Could not upload file!\n");
    }
  }
}
