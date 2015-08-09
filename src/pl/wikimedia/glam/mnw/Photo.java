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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

class Photo {

  int id = 0;
  String path = "";

  String accession_number = "";
  String author = "";
  String comment = "";
  String date = "";
  String dimensions = "";
  String location = "";
  String medium = "";
  String objectType = "";
  String title = "";

  ArrayList<String> tags = new ArrayList<>();

  Photo(int _id) {
    id = _id;
  }

  public void setAccNumber(String accession_number) {
    this.accession_number = accession_number;
  }

  public void setAuthor(ArrayList<String> authors) {
    Pattern r = Pattern.compile("(.*), (.*) \\(.*");
    this.author = "";

    for (String entry : authors) {
      Matcher m = r.matcher(entry);

      if (m.find()) {
        this.author += "{{Creator:" + m.group(2) + " " + m.group(1) + "}}\n";
      } else {
        this.author += entry + "\n";
      }
    }
  }

  public void setDate(ArrayList<String> dates) {
    this.date = "";
    for (String entry : dates) {
      this.date += entry + "\n";
    }
  }

  public void setDimensions(ArrayList<String> dimensions) {
    this.dimensions = "";
    Pattern r = Pattern.compile("([0-9,\\.]*) x ([0-9,\\.]*)(.*)");

    for (String dimension : dimensions) {
      Matcher m = r.matcher(dimension);
      this.dimensions += m.find()
              ? "{{Size |unit=cm |height=" + m.group(1) + " |width=" + m.group(2) + "}} " + m.group(3) + "\n"
              : dimension + "\n";
    }
  }

  public void setMedium(ArrayList<String> mediums) {
    HashMap<String, String> map = new HashMap<>();
    map.put("olej", "oil");
    map.put("papier", "paper");
    map.put("płótno", "canvas");

    if (mediums.size() > 2) {
      medium = "{{technique";
      for (int i = 1, max = mediums.size(); i < max; i++) {
        medium += "|";
        medium += map.get(mediums.get(i)) == null
                ? mediums.get(i)
                : map.get(mediums.get(i));
      }
      medium += "}}";
    }
  }

  public void setObjectType(ArrayList<String> types) {
    if (types.size() > 0) {
      objectType = types.get(0);
    }
  }

  public void setTags(ArrayList<String> tags) {
    this.tags = tags;
  }

  public void setTitle(ArrayList<String> titles) {
    title = "";
    for (String text : titles) {
      title += "{{pl|" + text + "}}\n";
    }
  }

  public String getCategories() {
    String text = "";

    HashSet hs = new HashSet();
    hs.addAll(tags);
    tags.clear();
    tags.addAll(hs);
    Collections.sort(tags);

    for (String tag : tags) {
      if (!tag.trim().isEmpty()) {
        text += "[[Category:" + tag + "]]\n";
      }
    }

    if (text.isEmpty()) {
      text += "{{subst:unc}}";
    }

    return text;
  }

  File getFile() {
    File f = null;
    try {
      URL url = new URL(path);
      BufferedImage bi = ImageIO.read(url);
      f = new File("temp.jpg");
      if (bi == null) {
        return null;
      }

      ImageIO.write(bi, "jpg", f);

    } catch (MalformedURLException ex) {
      return null;
    } catch (IOException ex) {
      return null;
    }
    return f;
  }

  public String getWikiText() {
    String text = "=={{int:filedesc}}==\n"
            + "{{Artwork\n"
            + " |artist             = " + author
            + " |author             = \n"
            + " |title              = " + title
            + " |object type        = " + objectType + "\n"
            + " |description        = \n"
            + " |date               = " + date
            + " |source             = \n"
            + " |medium             = " + medium + "\n"
            + " |dimensions         = " + dimensions
            + " |institution        = {{Institution:National Museum in Warsaw}}\n"
            + " |department         = \n"
            + " |place of discovery = \n"
            + " |object history     = \n"
            + " |exhibition history = \n"
            + " |credit line        = \n"
            + " |notes              = \n"
            + " |permission         = \n"
            + " |accession number   = " + accession_number + "\n"
            + " |references         = \n"
            + " |other_versions     = \n"
            + " |wikidata           = \n"
            + "}}\n\n";

    text += "=={{int:license-header}}==\n"
            + "{{PD-old-auto}}\n"
            + "{{National Museum in Warsaw partnership}}\n"
            + "{{subst:chc}}\n\n";

    text += getCategories();
    text += "[[Category:Images from National Museum in Warsaw – needing category checks]]\n";

    text = text.replaceAll(" +", " ");
    return text;
  }

  public ArrayList<File> getFiles() {
    ArrayList<String> paths = new ArrayList<>();
    ArrayList<File> files = new ArrayList<>();

    try {
      String xml = getTextFromUrl("http://cyfrowe.mnw.art.pl/Content/" + id + "/PresentationData.xml");
      Document doc = loadXMLFromString(xml);
      NodeList nodes = doc.getElementsByTagName("full-image");

      for (int i = 0, max = nodes.getLength(); i < max; i++) {
        Element element = (Element) nodes.item(i);
        paths.add("http://cyfrowe.mnw.art.pl/Content/" + id + "/" + getCharacterDataFromElement(element));
      }

      for (String path : paths) {
        try {
          URL url = new URL(path);
          BufferedImage bi = ImageIO.read(url);
          File f = new File(paths.indexOf(path) + ".jpg");
          
          if (bi != null) {
            ImageIO.write(bi, "jpg", f);
            files.add(f);
          }
          
        } catch (MalformedURLException ex) {
          return null;
        } catch (IOException ex) {
          return null;
        }
      }

    } catch (IOException ex) {
      Logger.getLogger(Photo.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(Photo.class.getName()).log(Level.SEVERE, null, ex);
    }

    return files;
  }

  private String getCharacterDataFromElement(Element e) {
    Node child = e.getFirstChild();
    if (child instanceof CharacterData) {
      CharacterData cd = (CharacterData) child;
      return cd.getData();
    }
    return "";
  }

  private String getTextFromUrl(String url) throws MalformedURLException, IOException {
    return new Scanner(new URL(url).openStream(), "UTF-8").useDelimiter("\\A").next();
  }

  private Document loadXMLFromString(String xml) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    InputSource is = new InputSource(new StringReader(xml));
    return builder.parse(is);
  }
}
