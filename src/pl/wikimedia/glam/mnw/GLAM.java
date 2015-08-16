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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import org.wikipedia.Wiki;

public class GLAM {

  Log log;
  Wiki wiki;
  
  int id;
  Metadata metadata;
  Photo photo;

  public GLAM(Wiki wiki, Log log, int id) {
    this.id = id;
    this.wiki = wiki;
    this.log = log;

    try {
      Harvester harvester = new Harvester("http://cyfrowe.mnw.art.pl/dmuseion/oai-pmh-repository.xml");
      Record record = harvester.getRecord("oai:cyfrowe.mnw.art.pl:" + id);
      
      metadata = record.getMetadata();
      photo = new Photo(id);
      log.log("Data downloaded.\n");

    } catch (Exception ex) {
      Logger.getLogger(GLAM.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void saveLog(String log) {
    try {
      wiki.newSection("User:" + wiki.getLoggedInUser() + "/log", "", log, false, true);
    } catch (IOException | LoginException ex) {
      Logger.getLogger(GLAM.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Returns photo description ready for upload
   * @return photo desc in wikicode
   */
  public String getPhotoWikiText() {
    try {
      photo.setAccNumber(metadata.getIdentifierList().get(2));
      photo.setAuthor(metadata.getCreatorList());
      photo.setDate(metadata.getDateList());
      photo.setDimensions(metadata.getFormatList());
      photo.setTitle(metadata.getTitleList());
      photo.setTags(metadata.getSubjectList());
      photo.setMedium(metadata.getDescriptionList());
      photo.setObjectType(metadata.getTypeList());

      return photo.getWikiText();
    } catch (Exception ex) {
      Logger.getLogger(GLAM.class.getName()).log(Level.SEVERE, null, ex);
    }
    return "";
  }
  
  public String getFileName() {
    return photo.getFileName();
  }

  public ArrayList<File> getFiles() {
    return photo.getFiles();
  }
}
