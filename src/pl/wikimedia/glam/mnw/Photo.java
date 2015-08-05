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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Photo {

  int id = 0;
  String path = "";

  String accession_number = "";
  String author = "";
  String comment = "";
  String date = "";
  String location = "";
  String title = "";

  ArrayList<String> tags = new ArrayList<>();

  Photo(int _id) {
    id = _id;
  }

  // sets
  //
  public void setAccNumber(String _accession_number) {
    accession_number = _accession_number;
  }

  public void setAuthor(String _author) {
    author = _author.startsWith("Autor: ") ? _author.substring(7) : _author;
  }

  public void setComment(String _comment) {
    if (!_comment.isEmpty()) {
      comment = "{{pl|" + _comment + "}}";
    }
  }

  public void setDate(String _date) {
    String d;

    if (_date.startsWith("Rok:")) {
      d = _date.substring(4);
    } else if (_date.startsWith("Zdjęcie wykonan")) {
      d = _date.substring(17);
    } else {
      d = _date;
    }

    if (d.endsWith("r.")) {
      d = d.replace("r.", "");
    }

    date = d.trim();
  }

  public void setLocation(String _location) {
    if (!_location.contains("nieznane")) {
      location = _location.startsWith("Lokacja:") ? _location.substring(8) : _location;
    }
  }

  public void setPath(String _path) {
    path = "http://cyfrowearchiwum.amu.edu.pl" + _path.replace("window.open('", "").replace("','_blank');", "");
  }

  public void setTags(ArrayList<String> taglist) {
    tags = taglist;
  }

  public void setTitle(ArrayList<String> titles) {
    title = "";
    for(String text : titles) {
      title += "{{pl|" + text + "}}\n";
    }
    if(!title.isEmpty()) {
      title = title.substring(0, title.length()-1);
    }
  }

  // gets  
  //
  public String getAccNumber() {
    return accession_number;
  }

  public String getAuthor() {
    return author;
  }

  public String getCategories() {
    Categories categories = new Categories();
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

  String getCity() {
    String[] loc = location.split(",");
    return loc[0];
  }

  String getDate() {
    return date;
  }

  String getTitle() {
    return title.isEmpty() ? "" : title;
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

  public String getName() {
    String text = "";

    if (!title.isEmpty()) {
      text += title + " - ";
    }
    if (!getCity().isEmpty()) {
      text += getCity() + " - ";
    }
    text += accession_number + ".jpg";

    return text;
  }

  public String getWikiText() {
    //{{Technique|lithograph|lithographic paper}}
    //{{Size|cm|height=33|width=18.5}}
    
    String text = "=={{int:filedesc}}==\n"
            + "{{Artwork\n"
            + " |artist             = " + getAuthor() + "\n"
            + " |author             = \n"
            + " |title              = " + getTitle() + "\n"
            + " |description        = \n"
            + " |date               = " + getDate() + "\n"
            + " |source             = \n"
            + " |medium             = \n"
            + " |dimensions         = \n"
            + " |institution        = {{Institution:National Museum in Warsaw}}\n"
            + " |department         = \n"
            + " |place of discovery = \n"
            + " |object history     = \n"
            + " |exhibition history = \n"
            + " |credit line        = \n"
            + " |notes              = \n"
            + " |permission         = \n"
            + " |accession number   = " + getAccNumber() + "\n"
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

  // other
  //
  String parseMonth(String month) {
    String m = "??";
    switch (month) {
      case "I":
        m = "01";
        break;
      case "II":
        m = "02";
        break;
      case "III":
        m = "03";
        break;
      case "IV":
        m = "04";
        break;
      case "V":
        m = "05";
        break;
      case "VI":
        m = "06";
        break;
      case "VII":
        m = "07";
        break;
      case "VIII":
        m = "08";
        break;
      case "IX":
        m = "09";
        break;
      case "X":
        m = "10";
        break;
      case "XI":
        m = "11";
        break;
      case "XII":
        m = "12";
        break;
    }
    return m;
  }
}

class Categories {

  Map<String, String> map = new HashMap<>();

  public Categories() {
    map.put("architektura sakralna", "Religious buildings in");
    map.put("budownictwo", "Buildings in");
    map.put("dzieci", "Children of");
    map.put("folklor", "Folklore of");
    map.put("kobiety", "Women of");
    map.put("narzędzia rolnicze", "Agricultural tools in");
    map.put("rękodzieło", "Folk art in");
    map.put("rolnictwo", "Agriculture in");
    map.put("strój ludowy", "Folk national costumes of");
    map.put("sztuka ludowa", "Folk art in");
  }

  public String get(String key) {
    String value = map.get(key);
    return value == null ? "" : value;
  }
}
